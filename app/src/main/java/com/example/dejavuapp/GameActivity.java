package com.example.dejavuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
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

import java.util.Locale;


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


        if ((difficultyTime <= 10) && (difficultyTime >= 7)) {
            countdown = 5000;
        } else if ((difficultyTime <= 6) && (difficultyTime >= 4)) {
            countdown = 10000;
        } else {
            countdown = 30000;
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        Game();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backHomebutton) {
            Intent intent = new Intent(this, HomeScreenActivity.class);
            startActivity(intent);
        }

    }

    public void Game() {
        if (currentScore > HighestScore){
            HighestScore = currentScore;
        }
        timeleft = countdown;
        startCountDown();

        randomNumberDisplay.setVisibility(View.VISIBLE);


        int generatedNumber = (int) (Math.random() * (max - min + 1) + min);
        player.setDisplayNumber(generatedNumber);
        player.setPlayerScore(currentScore);
        int displayNum = player.getDisplayNumber();
        int displayScore = player.getPlayerScore();

        currentPlayer = HomeScreenActivity.userName;
        player.setPlayerName(currentPlayer);


        randomNumberDisplay.setText(String.valueOf(displayNum));
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
                    Game();
                }


            }
        });

    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long l) {
                timeleft = l;
                updateCoundown();
                if (player.getPlayerScore() <= 0 || currentScore <= 0) {
                    game_over();
                }
            }

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
                    Game();
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


    public void game_over() {

        countDownTimer.cancel();
        button.setVisibility(View.GONE);
        inputNumber.setVisibility(View.GONE);
        GameOver.FinalScore = HighestScore;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Gamescreen, new GameOver()).commit();
    }


}





