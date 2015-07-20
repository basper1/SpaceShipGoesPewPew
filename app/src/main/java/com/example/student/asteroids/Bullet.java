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
    private GameView parent;
    Paint color = new Paint();

    public Bullet(int x,int y,int yVel, GameView parent){
        this.x = x;
        this.y = y;
        yVel = - 10;
        xVel = 0;
        this.parent = parent;
    }

    public void draw(Canvas canvas){
        canvas.drawRect(x-1,x-5,x+1,x+5,color);
    }



}
