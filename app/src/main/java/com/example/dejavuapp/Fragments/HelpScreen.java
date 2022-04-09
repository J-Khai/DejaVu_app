package com.example.dejavuapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.dejavuapp.HomeScreenActivity;
import com.example.dejavuapp.R;

public class HelpScreen extends Fragment {
    Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        context = getActivity();
        return inflater.inflate(R.layout.fragment_help_screen, container, false);
    }
    public void onStart(){
        super.onStart();
        ImageButton help_backhome = (ImageButton) context.findViewById(R.id.help_backhome);
        help_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }




}