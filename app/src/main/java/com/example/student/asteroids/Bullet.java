package com.example.student.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by student on 7/15/2015.
 */
public class Bullet {
    private int x;
    private int y;
    private int xVel;
    private int yVel;
    boolean isUp;
    private GameView parent;
    Paint color = new Paint();
    private long lastFire = 0;

    public Bullet(int x,int y,int yVel, GameView parent){
        this.x = x;
        this.y = y;
        yVel = - 10;
        xVel = 0;
        this.parent = parent;
    }

    public void paste(Spaceship spaceship){
        int dir = spaceship.getDir();
        if(dir % 2 == 0){

        }else{

        }
    }

    public void draw(Canvas canvas){
        canvas.drawRect(x,y,x+2,y+6,color);
    }



}
