package com.example.dejavuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton settingsBtn, leaderBoardBtn, helpBtn;
    Button startGame;
    EditText username;
    int playerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        settingsBtn = findViewById(R.id.settingsBtn); //gets the id
        helpBtn = findViewById(R.id.helpBtn);
        leaderBoardBtn = findViewById(R.id.leaderboardBtn);
        startGame = findViewById(R.id.startgameBtn);
        username = (TextInputEditText) findViewById(R.id.userName);

        settingsBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);
        leaderBoardBtn.setOnClickListener(this);
        startGame.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.settingsBtn){
            FromHomeToSettings();
        }
        if (view.getId() == R.id.helpBtn){
            FromHomeToHelp();
        }
        if (view.getId() == R.id.leaderboardBtn){
            FromHomeToLeaderBoard();
        }
        if (view.getId() == R.id.startgameBtn){
            FromHomeToGame();

        }
    }

    private void FromHomeToGame() {
        String userName = (username.getText().toString());
        new Player(userName, playerScore);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void FromHomeToLeaderBoard() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.HomeScreen,new LeaderBoard()).commit();
    }

    public void FromHomeToHelp() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.HomeScreen,new HelpScreen()).commit();
    }

    public void FromHomeToSettings() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.HomeScreen,new Settings()).commit();
    }



}