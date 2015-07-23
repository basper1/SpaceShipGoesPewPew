package com.example.student.asteroids.Enemies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.student.asteroids.GameView;

/**
 * Created by student on 7/21/2015.
 * pop up and shoot enemy;
 * chase you enemy;
 * dash enemy;
 * run away from u and shoot enemy;
 * suiceide bomber enemy;
 *
 *
 *
 */
public class Enemy {
    public int x;
    public int y;
    public GameView parent;
    public int value;
    public float maxspeed;
    public float xVel;
    public float yVel;
    public int timer = 4000;
    public long lastRes = System.currentTimeMillis();
    public Paint paint;
    public int size = 50;

    public Enemy(GameView parent){
        this.parent = parent;
    }

    public Enemy(GameView parent,int v){
        lastRes = System.currentTimeMillis();
        this.parent = parent;
        value = v;
        paint = new Paint();
    }

    public void moveRand(){
        x = (int)(Math.random() * (parent.getWidth() -  40) + 20);
        y = (int)(Math.random() * (parent.getHeight() - 40) + 20);
        if(Math.abs(x - parent.spaceship.getX() + 10) <= 50 && Math.abs(y - parent.spaceship.getY() + 10) <= 50){
            x = parent.getWidth() - x;
            y = parent.getHeight() - y;
        }
    }

    public void moveTo(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void move(){
        this.x += xVel;
        this.y += yVel;
        inSpace();
    }

    public void setValue(int v){
        value  = v;
    }

    public void setXVel(float xVel){
        this.xVel = xVel;
    }

    public void setYVel(float yVel){
        this.yVel = yVel;
    }

    public void resetTime(){
        lastRes = System.currentTimeMillis();
    }

    public void inSpace(){
        x = Math.max(0, Math.min(parent.getWidth(), x));
        y = Math.max(0, Math.min(parent.getHeight(), y));
    }

    public int timeLeft(){
        return (int)(timer - (System.currentTimeMillis() - lastRes));
    }

    public void draw(Canvas canvas){
        //System.out.println(System.currentTimeMillis() - lastRes);
        if(System.currentTimeMillis() - lastRes > timer){
            parent.setGameState(2);
            return;
        }
        if(paint != null) {
            if(timeLeft() > timer / 3 * 2) paint.setColor(Color.GREEN);
            else if(timeLeft() > timer / 3) paint.setColor(Color.YELLOW);
            else paint.setColor(Color.RED);
            canvas.drawCircle(x, y, size, paint);
        }else{
            paint = new Paint();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
