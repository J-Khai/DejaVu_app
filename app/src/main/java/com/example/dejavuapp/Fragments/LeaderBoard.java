package com.example.dejavuapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.dejavuapp.HomeScreenActivity;
import com.example.dejavuapp.R;


public class LeaderBoard extends Fragment {


    public LeaderBoard() {
        // Required empty public constructor
    }




    Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        return inflater.inflate(R.layout.fragment_leader_board, container, false);
    }
    public void onStart(){
        super.onStart();
        ImageButton leaderboard_homebtn = (ImageButton) context.findViewById(R.id.leaderboard_homebtn);
        leaderboard_homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}