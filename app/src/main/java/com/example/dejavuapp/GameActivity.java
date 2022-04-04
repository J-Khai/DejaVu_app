package com.example.dejavuapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class GameActivity extends AppCompatActivity {
    Button button;
    EditText inputNumber;
    TextView randomNumberDisplay, currentScoreDisplay, currentRoundDisplay;
    int max = 1000;
    int min = 1;
    int round = 1;
    int MaxRound = 100;
    String currentPlayer;
    private int currentScore = 0;
    boolean running = true;

    private final GamePlayUpdate gamePlay = new GamePlayUpdate();
    private final Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        button =  findViewById(R.id.gamebutton);
        inputNumber =  findViewById(R.id.textInput);
        randomNumberDisplay =  findViewById(R.id.randomNumberDisplay);
        currentScoreDisplay =  findViewById(R.id.currentScoreDisplay);
        currentRoundDisplay =  findViewById(R.id.currentRoundDisplay);
        Game();








    }




    public void Game(){

            int generatedNumber = (int) (Math.random() * (max - min + 1) + min);
            player.setDisplayNumber(generatedNumber);
            player.setPlayerScore(currentScore);
            int displayNum = player.getDisplayNumber();
            int displayScore = player.getPlayerScore();



            System.out.println(displayNum);
            System.out.println(displayScore);




            randomNumberDisplay.setText(String.valueOf(displayNum));
            currentRoundDisplay.setText(String.valueOf(round));
            currentScoreDisplay.setText(String.valueOf(displayScore));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String inputNum = inputNumber.getText().toString();
                    int num = Integer.parseInt(inputNum);
                    boolean ans = player.checkAnswer(num);
                    currentScore = gamePlay.updateScore(ans);
                    currentScoreDisplay.setText(String.valueOf(currentScore));
                    System.out.println("current score when clicked " + currentScore);
                    if(ans){
                        round++;
                        Game();
                    }
                    if (player.getPlayerScore() == 30){
                        Toast toast = Toast.makeText(getApplicationContext(), "GameOver", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }
            });




    }









}