package com.example.dejavuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dejavuapp.Fragments.HelpScreen;
import com.example.dejavuapp.Fragments.LeaderBoard;
import com.example.dejavuapp.Fragments.Settings;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton settingsBtn, leaderBoardBtn, helpBtn;
    private Button startGame;
    //private EditText username;
    private TextInputEditText INputuserName;
    static String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);


        settingsBtn = findViewById(R.id.settingsBtn); //gets the id
        helpBtn = findViewById(R.id.helpBtn);
        //leaderBoardBtn = findViewById(R.id.leaderboardBtn);
        startGame = findViewById(R.id.startgameBtn);
        INputuserName =  findViewById(R.id.userName);

        settingsBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);
        //leaderBoardBtn.setOnClickListener(this);
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
//        if (view.getId() == R.id.leaderboardBtn){
//            FromHomeToLeaderBoard();
        //}
        if (view.getId() == R.id.startgameBtn){
            userName = Objects.requireNonNull(INputuserName.getText()).toString().trim();

            FromHomeToGame();

        }
    }

    public void FromHomeToGame() {
        startGame.setVisibility(View.GONE);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);


    }

    private void FromHomeToLeaderBoard() {
        startGame.setVisibility(View.GONE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.HomeScreen,new LeaderBoard()).commit();
    }

    public void FromHomeToHelp() {
        startGame.setVisibility(View.GONE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.HomeScreen,new HelpScreen()).commit();
    }

    public void FromHomeToSettings() {
        startGame.setVisibility(View.GONE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.HomeScreen,new Settings()).commit();
    }



}