package com.example.dejavuapp;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.dejavuapp.BackEnd.GamePlayUpdate;
import com.example.dejavuapp.BackEnd.Player;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {


    @Test
    public void test1() {

        Player player = new Player();
        GamePlayUpdate gamePlayUpdate = new GamePlayUpdate();
        player.setPlayerName("pppsaf");
        String nameE = "Khai";
        assertEquals(nameE, player.getPlayerName());
        //player.setPlayerScore(500);
        int scoreE = 100 + 25;
        player.setDisplayNumber(754);
        boolean a = player.checkAnswer(player.getDisplayNumber());
        int s = gamePlayUpdate.updateScore(a);
        assertEquals(scoreE, s);


    }
}