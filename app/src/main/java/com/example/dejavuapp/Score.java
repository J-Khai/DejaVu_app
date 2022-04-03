package com.example.dejavuapp;

public class Score {
    int playerScore;
    int displayNumber;
    int check;

    public Score(){
        this.playerScore = 0;
    }

    public void setPlayerScore (int playerScore){
        this.playerScore = playerScore;
    }
    public int getPlayerScore (){
        return playerScore;
    }

    public void setDisplayNumber(int displayNumber){
        this.displayNumber = displayNumber;

    }
    public int getDisplayNumber(){
        return this.displayNumber;
    }

    public void setcheck(int check){
        this.check = check;
        int tempScore = 0;

        if(this.check == getDisplayNumber()){
            tempScore+= 10; setPlayerScore(tempScore);
        }
        else tempScore-=10; setPlayerScore(tempScore);


    }

}
