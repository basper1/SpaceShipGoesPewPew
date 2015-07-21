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
    int width;
    int height;
    Paint color = new Paint();
    private long lastDraw = System.currentTimeMillis();
    Spaceship parent;
    int dir;

    public Bullet(Spaceship spaceship,int w,int h){

        create(spaceship);
        dir = spaceship.getDir();
        width = w;
        height = h;
        //System.out.println("bullet created");
    }

    public void move(){
        long distance = System.currentTimeMillis() - lastDraw;

        //System.out.println(distance);
        //System.out.println(x + " " + y);
        lastDraw =System.currentTimeMillis();
        switch (dir){
            case(0): y -= distance; break;
            case(1): x += distance; break;
            case(2): y += distance; break;
            case(3): x -= distance; break;
        }
    }

    public boolean out(){
        if(x < 0 || y < 0 || x > width || y > height){
            System.out.println("bullet deleted" + x + " " +  y + " " + height + " "+ width);
            return true;
        }
        return false;
    }

    public void create(Spaceship spaceship){
        x = spaceship.getX() + spaceship.getBitmap().getHeight() / 2;
        y = spaceship.getY() + spaceship.getBitmap().getHeight() / 2;
        return;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x, y, 10, color);
        //System.out.println("bullet drawn");
    }
}
