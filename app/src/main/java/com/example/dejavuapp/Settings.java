package com.example.dejavuapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Settings extends Fragment implements View.OnClickListener {
    Activity context;

    ImageButton toHelp, backHomeBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        context = getActivity();

//        backHomeBtn = context.findViewById(R.id.backHomebutton);
//        toHelp = view.findViewById(R.id.helpBtn);


//        backHomeBtn.setOnClickListener(this);
//        toHelp.setOnClickListener(this);


        return view;
    }

    public void onStart(){
        super.onStart();

        backHomeBtn = (ImageButton) context.findViewById(R.id.backHomebutton);


        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeScreenActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {

//        if (view.getId() == R.id.helpBtn){
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.HomeScreen,new LeaderBoard()).commit();
//        }


    }



}