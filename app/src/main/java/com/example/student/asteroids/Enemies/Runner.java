package com.example.student.asteroids.Enemies;

import com.example.student.asteroids.GameView;

/**
 * Created by student on 7/22/2015.
 */
public class Runner extends Enemy{

    private long lastDash = System.currentTimeMillis();
    public Runner(GameView gameview){
        super(gameview);
    }

    public void dash(){

    }

}
