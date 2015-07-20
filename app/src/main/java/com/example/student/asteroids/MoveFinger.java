package com.example.student.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by student on 7/17/2015.
 */
public class MoveFinger extends Finger {
    private GameView parent;
    private ArrayList<Float> x = new ArrayList<>();
    private ArrayList<Float> y = new ArrayList<>();
    private Spaceship spaceship;
    private Paint color;

    public MoveFinger(float x,float y, int id,GameView parent){
        super(x,y,id,parent);
        color = new Paint();
    }

    public void setSpaceship(Spaceship spaceship){
        this.spaceship = spaceship;
    }



    public void moveShip(){

        float xVel = Math.min(100,x.get(x.size() - 1) - x.get(0));
        float yVel = Math.min(100, y.get(y.size() - 1) - y.get(0));
        if(xVel < -100) xVel = -100;
        if(yVel < -100) yVel = -100;
        spaceship.setYVelocity((int) yVel);
        spaceship.setXVelocity((int) xVel);
    }




    public void move(float x,float y){
        this.x.add(x);
        this.y.add(y);
        moveShip();
    }

    public void draw(Canvas canvas){
        if(x.size() > 0)
            //try {
                canvas.drawCircle(x.get(0), y.get(0), 100, color);
            //}catch(Exception e){
               // System.out.println("ERROR ERROR ERROR1234" + e);
            //}
        else
            remove();
    }
}
