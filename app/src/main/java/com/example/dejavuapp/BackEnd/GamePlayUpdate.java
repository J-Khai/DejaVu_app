package com.example.dejavuapp.BackEnd;


public class GamePlayUpdate {
    Player player = new Player();


    public int updateScore(boolean update){
        int tempScore = player.getPlayerScore();

        if (update) {
            tempScore += 25;
        }
        else {
            tempScore -= 25;
        }
        player.setPlayerScore(tempScore);
        return player.getPlayerScore();
    }






}
