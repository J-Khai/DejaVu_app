package com.example.dejavuapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;
    EditText inputNumber;
    static boolean CORRECT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        button = (Button) findViewById(R.id.gamebutton);
        inputNumber = (EditText) findViewById(R.id.textInput);


        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.gamebutton){
            gameRunning();
        }

    }

    public void gameRunning(){
        int inputNum_value = new Score().getDisplayNumber();
        TextView scoreDisplay = (TextView) findViewById(R.id.ScoreDisplay);
        scoreDisplay.setText(inputNum_value);

        String inputNum = inputNumber.getText().toString();
        int num = Integer.parseInt(inputNum);
        new Score().setcheck(num);
        int score = new Score().getPlayerScore();




    }
}