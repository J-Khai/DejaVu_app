package com.example.dejavuapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class GameActivity extends AppCompatActivity {
    Button button;
    EditText inputNumber;
    int max = 1000;
    int min = 1;
    int numberGen;
    int MaxRound = 100;
    String currentPlayer;
    int currentScore;
    View view;
    private GamePlay gamePlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        button = (Button) findViewById(R.id.gamebutton);
        inputNumber = (EditText) findViewById(R.id.textInput);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                gamePlay.isRunning();

            }
        };
        runnable.run();




    }






    public void gameActivity() {

        // within progress bar
//        String inputNum = inputNumber.getText().toString();
//        int num = Integer.parseInt(inputNum);
//        new Player().setcheck(num);
//        int score = new Player(currentPlayer, currentScore).getPlayerScore();
//
//        TextView totalScore = (TextView) findViewById(R.id.textScore);
//        totalScore.setText(score);
//        setGameInfo();


    }



//    public void setGameInfo(){
//        numberGen = (int) (Math.random() * (max - min + 1) + min); //gen random number
//        System.out.println("num" + numberGen);
//        currentPlayer = new Player().getPlayerName(); //get player name
//        currentScore = new Player().getPlayerScore();// get player score
//        new Player().setDisplayNumber(numberGen);// display the random num
//
//        int displayNumGenerated = new Player().getDisplayNumber(); // get the random num
//        TextView scoreDisplay = (TextView) findViewById(R.id.ScoreDisplay);
//        scoreDisplay.setText(String.valueOf(displayNumGenerated)); // send the random num to the app
//
//
//
//    }


}