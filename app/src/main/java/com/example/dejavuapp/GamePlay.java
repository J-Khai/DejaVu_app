package com.example.dejavuapp;

import android.content.Context;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;


public class GamePlay {
    Player player = new Player();
    private final int max = 1000;
    private final int min = 1;
    int numberGen;
    private final int MaxRound = 100;
    String currentPlayer;
    private int currentScore;
    boolean running;

    public void isRunning(){

        while(running){
            Game();
        }

    }

    public void Game() {
        int generatedNumber = (int) (Math.random() * (max - min + 1) + min);
        player.setDisplayNumber( generatedNumber);
        player.setPlayerScore(currentScore);

    }


}
