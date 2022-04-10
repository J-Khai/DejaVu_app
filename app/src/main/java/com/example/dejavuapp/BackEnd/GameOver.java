package com.example.dejavuapp.BackEnd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dejavuapp.GameActivity;
import com.example.dejavuapp.HomeScreenActivity;
import com.example.dejavuapp.LeaderBoard_PopUp;
import com.example.dejavuapp.R;

import org.w3c.dom.Text;


public class GameOver extends Fragment implements View.OnClickListener{
    public static String player; // {

    private TextView clickYes, clockNo, finalScore, winorLose;
     Activity context;
     public static int FinalScore;
     public static boolean win = false;
     private int score;
     private AlertDialog.Builder dialogBuilder;
     private AlertDialog dialog;
     ConstraintLayout popup;
     Button closePopup;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();

        View view = inflater.inflate(R.layout.fragment_game_over, container, false);
        clickYes = view.findViewById(R.id.click_yes);
        clockNo = view.findViewById(R.id.click_no);
        finalScore = view.findViewById(R.id.finalScore);
        //this.popup = view.findViewById(R.id.popup);
        closePopup = view.findViewById(R.id.closePop);
        winorLose = view.findViewById(R.id.winorLose);




        clickYes.setOnClickListener(this);

        clockNo.setOnClickListener(this);
        displayFinalScore(FinalScore);


        return view;
    }

    public void displayFinalScore(int Score){
        this.score = Score;

    }
    public int getFinalScore(){
        return this.score;
    }

    public void popup(){
        dialogBuilder = new AlertDialog.Builder(this.context);
        final View popup = getLayoutInflater().inflate(R.layout.fragment_game_over,null);
        dialogBuilder.setView(popup);
        dialog=dialogBuilder.create();
        dialog.show();
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        String string = String.format("%s, Your Score %s",player, getFinalScore());
        finalScore.setText(string);
        if (win){
            string = "You Won";
            winorLose.setText(string);
            winorLose.setTextColor(Color.GREEN);
        }
        else {
            string = "Game Over";
            winorLose.setText(string);
            winorLose.setTextColor(Color.RED);
        }


        //popup();


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.click_yes){

            Intent intent = new Intent(context, GameActivity.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.click_no){
            Intent intent = new Intent(context, HomeScreenActivity.class);
            startActivity(intent);
        }
    }
}