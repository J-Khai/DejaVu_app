package com.example.dejavuapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;


public class gamePlay {
    private static int numberGen;
    Button button;
    int max = 1000;
    int min = 1;
    int MaxRround = 100;




    public void gameActivity() {



        for (int i = 0; i < MaxRround; i++) {
            if (i > 3){
                numberGen = randomNumber();
                new Score().setDisplayNumber(numberGen);

            }

        }


    }

    public int randomNumber(){
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static boolean check(int inputNum){
        return inputNum == numberGen;
    }
}
