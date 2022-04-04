package com.example.dejavuapp;

public class Player {
    String playerName;
    int playerScore;
    int displayNumber;
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
        return playerScore;
    }


    public void setPlayerScore (int playerScore){
        this.playerScore = playerScore;
    }

    public void setDisplayNumber(int displayNumber){
        this.displayNumber = displayNumber;

    }

    public void checkAnswer(int check){
        this.check = check;
        int tempScore = 0;

        if(this.check == getDisplayNumber()){
            tempScore+= 10; setPlayerScore(tempScore);
        }
        else tempScore-=10; setPlayerScore(tempScore);


    }
}
