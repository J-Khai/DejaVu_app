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


public class GamePlayUpdate {
    Player player = new Player();

    public void updateScore(boolean update){
        int tempScore = 0;
        if (update) {
            tempScore+= 10;
        }
        else {
            tempScore-= 10;
        }
        player.setPlayerScore(tempScore);
    }






}
