package com.example.dejavuapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dejavuapp.GameActivity;
import com.example.dejavuapp.HomeScreenActivity;
import com.example.dejavuapp.R;

public class Settings extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    Activity context;

    ImageButton toHelp, backHomeBtn;
    SeekBar display_seekbar, time_seekbar;
    TextView displayNumTime, counterTime;
    int display_seekbar_progress, time_seekbar_progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        context = getActivity();
        display_seekbar = view.findViewById(R.id.display_seekbar);
        time_seekbar = view.findViewById(R.id.Time_seekBar);
        displayNumTime= view.findViewById(R.id.display_seekbar_text);
        counterTime = view.findViewById(R.id.time_seekbar_text);

        display_seekbar.setOnSeekBarChangeListener(this);
        time_seekbar.setOnSeekBarChangeListener(this);



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

    String string;
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar.getId() == R.id.Time_seekBar){
            setTime_seekbar_progress(seekBar.getProgress());
            string = String.format("Input Time %s",i);
            counterTime.setText(string);
            GameActivity.difficultyTime = i;
        }
        if (seekBar.getId() == R.id.display_seekbar){
            setDisplay_seekbar_progress(seekBar.getProgress());
            string = String.format("Display Delay %s",i);
            displayNumTime.setText(string);
        }


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        seekBar.setProgress(seekBar.getProgress());
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.Time_seekBar){
            seekBar.setProgress(seekBar.getProgress());

            GameActivity.difficultyTime = seekBar.getProgress();
        }

    }

    public void setDisplay_seekbar_progress(int num){
        this.display_seekbar_progress = num;
    }
    public void setTime_seekbar_progress(int num){
        this.time_seekbar_progress = num;
    }
    public int getDisplay_seekbar_progress(){
        return this.display_seekbar_progress;
    }

    public int getTime_seekbar_progress(){
        return this.time_seekbar_progress;
    }
}