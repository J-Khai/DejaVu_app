package com.example.dejavuapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dejavuapp.BackEnd.LeaderBoardUpdate;
import com.example.dejavuapp.GameActivity;
import com.example.dejavuapp.HomeScreenActivity;
import com.example.dejavuapp.R;

import java.io.IOException;
import java.util.Map;


public class LeaderBoard extends Fragment {


    public LeaderBoard() {
        // Required empty public constructor
    }
    private TextView lboard;




    Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();

        View view=  inflater.inflate(R.layout.fragment_leader_board, container, false);

        lboard =view.findViewById(R.id.lBoard);
        return view;
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

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public void LUPDATE(String name, int score) throws IOException {
//        GameActivity leaderBoardUpdate = new GameActivity();
//        leaderBoardUpdate.LeaderBoardUpdate(name, score);
//        leaderBoardUpdate.postLeaderBoard();
//
//
//    }
//    public <K, V> void send(Map<K, V> map){
//        int r = 1;
//        for (Map.Entry<K, V> entry : map.entrySet()) {
//            String b = String.format("%s. %-4s  %-7s\n%n", r, entry.getKey(), entry.getValue());
//            lboard.setText(b);
//            r++;
//        }
//    }


}