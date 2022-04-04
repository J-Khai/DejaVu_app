package com.example.dejavuapp;

public class Player {
    String playerName;
    int playerScore = 0;
    int displayNumber = 0;
    int check;

    public Player(String playerName, int score){
        this.playerName = playerName;
        this.playerScore = score;
    }

    public Player() {

    }

    public String getPlayerName (){
        return this.playerName;
    }
    public int getDisplayNumber(){
        return this.displayNumber;
    }
    public int getPlayerScore (){
        return this.playerScore;
    }


    public void setPlayerScore (int playerScore){
        this.playerScore = playerScore;
    }

    public void setDisplayNumber(int displayNumber){
        this.displayNumber = displayNumber;

    }

    public boolean checkAnswer(int check){
        this.check = check;
        return this.check == getDisplayNumber();


    }
}
