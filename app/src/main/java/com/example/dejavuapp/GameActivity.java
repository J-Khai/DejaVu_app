package com.example.dejavuapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dejavuapp.BackEnd.GameOver;
import com.example.dejavuapp.BackEnd.GamePlayUpdate;
import com.example.dejavuapp.BackEnd.Player;
import com.example.dejavuapp.Fragments.LeaderBoard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private ImageButton ToHome, ToSettings;
    private EditText inputNumber;
    private TextView randomNumberDisplay, currentScoreDisplay, currentRoundDisplay, textViewCountDown;

    private String inputNum;
    private int max = 1000;
    private int min = 1;
    private int round = 1;
    int MaxRound = 100;
    private String currentPlayer;
    private int currentScore = 100, HighestScore = 0;
    private static long countdown;




    private final Player player = new Player();
    private CountDownTimer countDownTimer;
    private long timeleft;

    private final GamePlayUpdate gamePlay = new GamePlayUpdate();
    public static int difficultyTime;

    private AlertDialog dialog;


    Button closePopup;

//    private final Player player = new Player(homeScreenActivity.playerScore);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        button = findViewById(R.id.gamebutton);
        inputNumber = findViewById(R.id.textInput);
        randomNumberDisplay = findViewById(R.id.randomNumberDisplay);
        currentScoreDisplay = findViewById(R.id.currentScoreDisplay);
        currentRoundDisplay = findViewById(R.id.currentRoundDisplay);
        textViewCountDown = findViewById(R.id.textViewCountDown);
        ToHome = findViewById(R.id.backHomebutton);
        ToHome.setOnClickListener(this);
        closePopup = findViewById(R.id.closePop);


        if ((difficultyTime <= 10) && (difficultyTime >= 7)) {
            countdown = 5000;
        } else if ((difficultyTime <= 6) && (difficultyTime >= 4)) {
            countdown = 10000;
        } else {
            countdown = 30000;

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onStart() {
        super.onStart();
        try {
            Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backHomebutton) {
            Intent intent = new Intent(this, HomeScreenActivity.class);
            startActivity(intent);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Game() throws IOException {
        if (currentScore > HighestScore){
            HighestScore = currentScore;
        }
        timeleft = countdown;
        startCountDown();

        randomNumberDisplay.setVisibility(View.VISIBLE);


        int generatedNumber = (int) (Math.random() * (max - min + 1) + min);
        int n = (int) (Math.random() * (10 - 1 + 1) + 1);
        int r = (int) (Math.random() * (10000 - 1 + 1) + 1);
        player.setDisplayNumber(generatedNumber);
        player.setPlayerScore(currentScore);
        int displayNum = player.getDisplayNumber();
        int displayScore = player.getPlayerScore();

        currentPlayer = HomeScreenActivity.userName;
        player.setPlayerName(currentPlayer);

        if ((difficultyTime <= 10) && (difficultyTime >= 7) && round >= 7) { displayNum = displayNum*n;
            randomNumberDisplay.setText(String.valueOf(displayNum));}
        else if ((difficultyTime <= 6) && (difficultyTime >= 4) && round > 3 && round <7) { displayNum = displayNum*n;
            randomNumberDisplay.setText(String.valueOf(displayNum + r));}
        else {
            randomNumberDisplay.setText(String.valueOf(displayNum));
        }



        currentRoundDisplay.setText(String.valueOf(round));
        currentScoreDisplay.setText(String.valueOf(displayScore));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    if ((difficultyTime <= 10) && (difficultyTime >= 7)) {
                        Thread.sleep(1500);

                        randomNumberDisplay.setVisibility(View.INVISIBLE);
                    } else if ((difficultyTime <= 6) && (difficultyTime >= 4)) {
                        Thread.sleep(3000);
                        randomNumberDisplay.setVisibility(View.INVISIBLE);
                    } else {
                        Thread.sleep(5000);

                        randomNumberDisplay.setVisibility(View.INVISIBLE);
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        if (player.getPlayerScore() <= 0) {
            game_over();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                inputNum = inputNumber.getText().toString();
                if(inputNum.equals("")){
                    inputNum = "-1";
                }
                inputNumber.setText("");
                int num = Integer.parseInt(inputNum);
                boolean ans = player.checkAnswer(num);
                currentScore = gamePlay.updateScore(ans);
                currentScoreDisplay.setText(String.valueOf(currentScore));
                System.out.println("current score when clicked " + currentScore);
                if (ans) {
                    round++;
                    inputNum = null;
                    //Game();
                    countDownTimer.cancel();
                    if (currentScore > HighestScore){
                        HighestScore = currentScore;
                    }
                    if(round ==10){
                        try {
                            GameOver.win = true;
                            GameOver.player = currentPlayer;
                            game_over();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Game();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        });

    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeleft, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTick(long l) {
                timeleft = l;
                updateCoundown();
                if (player.getPlayerScore() <= 0 || currentScore <= 0) {
                    try {
                        game_over();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFinish() {

                timeleft = 0;
                updateCoundown();
                if ((timeleft == 0)) {
                    countDownTimer.cancel();
                    currentScore = gamePlay.updateScore(false);
                    currentScoreDisplay.setText(String.valueOf(currentScore));
                    if (currentScore > HighestScore){
                        HighestScore = currentScore;
                    }
                    try {
                        Game();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    private void updateCoundown() {
        int minutes = (int) (timeleft) / 1000 / 60;
        int seconds = (int) (timeleft / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void game_over() throws IOException {

        countDownTimer.cancel();
        button.setVisibility(View.GONE);
        inputNumber.setVisibility(View.GONE);
        GameOver.FinalScore = HighestScore;
        GameOver.player = currentPlayer;
//        LeaderBoard leaderBoard = new LeaderBoard();
//        leaderBoard.LUPDATE(currentPlayer,HighestScore);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Gamescreen, new GameOver()).commit();
    }


    Map<String, Integer> unsortedMap = new HashMap<>();
    private static Map<String, Integer> sortedMap = new LinkedHashMap<>();
    String playerName;
    int playerScore;
    //InputStream inputStream = GameActivity.

    public void LeaderBoardUpdate(String playerName, int Scores) throws IOException { // player data when game is Over
        this.playerName = playerName;
        System.out.println(this.playerName);
        this.playerScore = Scores;
        //unsortedMap = test.stringIntegerHashMap;
        //unsortedMap.put(playerName,playerScore);

    }



    public <K, V> void CheckifNameExist(Map<K, V> map) throws IOException {
//       // if()
//        //InputStream inputStream = this.getResources().openRawResource(R.raw.leader_board_unsorted);
//
//        OutputStreamWriter writer1 = new OutputStreamWriter(this.openFileOutput("leader_board_sorted.txt", Context.MODE_PRIVATE));
//        //BufferedWriter writer1 = new BufferedWriter(new FileWriter("raw/leader_board_unsorted.txt", true));
//       // BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream));
//
//
//        String line;
//        while ((line = reader1.readLine())!= null){
//            String[] b = line.split(" ");
//            unsortedMap.put(b[0], Integer.parseInt(b[1]));
//        }
//        unsortedMap.put(this.playerName,this.playerScore);
//
//        if ((reader1.readLine()) == null) {
//            for (Map.Entry<K, V> entry : map.entrySet()) {
//                String a = String.format("%s %s\n", entry.getKey(), entry.getValue());
//                writer1.write(a);
//            }
//        }
//        writer1.close();
//        reader1.close();

    }


//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public void postLeaderBoard() throws IOException {
//        CheckifNameExist(unsortedMap);
//        InputStream inputStream = this.getResources().openRawResource(R.raw.leader_board_sorted);
//        OutputStreamWriter outputStream = new OutputStreamWriter(this.openFileOutput("leader_board_sorted.txt", Context.MODE_PRIVATE));
//       // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//        Map<String, Integer> sortedMap = sortLeaderBaord(unsortedMap);
//
//        //String name = "dog";
//        boolean exist = false;
//
//        String line;
//        while((line = reader.readLine()) != null){
//            String [] b  = line.split(" ");
//            for (String s : b) {
//                if (s.equals(this.playerName)) {
//                    System.out.println(this.playerName+" " + b[1]);
//                    exist = true;
//                    break;
//                }
//
//                //System.out.println(a);
//            }
//            //System.out.println(line);
//        }
//        if (!exist){
//            System.out.println((String.format("%s does not exist",this.playerName)));
//        }
//        printLeaderBoard(sortedMap);
//        reader.close();
//        outputStream.close();
//
//
//    }
//
//
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private static Map<String, Integer> sortLeaderBaord(Map<String, Integer> unsortMap) throws IOException {
//
//        // 1. Convert Map to List of Map
//        List<Map.Entry<String, Integer>> list =
//                new LinkedList<>(unsortMap.entrySet());
//
//
//        list.sort((o2, o1) -> (o1.getValue()).compareTo(o2.getValue()));
//
//        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
//
//        for (Map.Entry<String, Integer> entry : list) {
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }
//
//        return sortedMap;
//    }
//
//    public  <K, V> void printLeaderBoard(Map<K, V> map) throws IOException {
//        OutputStreamWriter outputStream = new OutputStreamWriter(this.openFileOutput("leader_board_sorted.txt", Context.MODE_PRIVATE));
//        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
//
//        int r = 1;
//        for (Map.Entry<K, V> entry : map.entrySet()) {
//            System.out.printf("%s. %-4s  %-7s\n%n",r,entry.getKey(),entry.getValue());
//            String a = String.format("%s %s\n",entry.getKey(),entry.getValue());
//            outputStream.write(a);
//            r++;
//
//        }
//        outputStream.close();
//        //new LeaderBoard().send(sortedMap);


//    }



}





