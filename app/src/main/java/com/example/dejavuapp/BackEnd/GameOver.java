package com.example.dejavuapp.BackEnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dejavuapp.GameActivity;
import com.example.dejavuapp.HomeScreenActivity;
import com.example.dejavuapp.R;

import org.w3c.dom.Text;


public class GameOver extends Fragment implements View.OnClickListener{ // {

    private TextView clickYes, clockNo, finalScore;
     Activity context;
     static int FinalScore;
     private int score;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();

        View view = inflater.inflate(R.layout.fragment_game_over, container, false);
        clickYes = view.findViewById(R.id.click_yes);
        clockNo = view.findViewById(R.id.click_no);
        finalScore = view.findViewById(R.id.finalScore);

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

    @Override
    public void onStart() {
        super.onStart();
        String string = String.format("Your Score %s", getFinalScore());
        finalScore.setText(string);


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