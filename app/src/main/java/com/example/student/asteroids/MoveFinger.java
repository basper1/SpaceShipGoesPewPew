package com.example.student.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by student on 7/17/2015.
 */
public class MoveFinger extends Finger {
    private Spaceship spaceship;
    private Paint color = new Paint();

    public MoveFinger(float x,float y, int id,GameView parent,Spaceship  ship){
        super(id, parent);
        spaceship = ship;
        startX = x;
        startY = y;
    }

    public void setSpaceship(Spaceship spaceship){
        this.spaceship = spaceship;
    }



    public void moveShip(){

        float xVel = Math.min(100,x - startX);
        float yVel = Math.min(100,y - startY);
        if(xVel < -100) xVel = -100;
        if(yVel < -100) yVel = -100;
        spaceship.setYVelocity((int) yVel);
        spaceship.setXVelocity((int) xVel);
    }




    public void move(float x,float y){
        this.x = x;
        this.y = y;
        //System.out.println(x + " " + y);
        moveShip();
    }




}
