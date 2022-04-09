package com.example.dejavuapp.BackEnd;

public class Player {
    private  String playerName;
    int playerScore = 100;
    int displayNumber;
    int check;


    public void setPlayerName(String playerName){
        this.playerName = playerName;

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
        this.playerScore= playerScore;
    }

    public void setDisplayNumber(int displayNumber){
        this.displayNumber = displayNumber;

    }

    public boolean checkAnswer(int check){
        this.check = check;
        return this.check == getDisplayNumber();


    }
}
