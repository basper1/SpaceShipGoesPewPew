package com.example.student.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by student on 7/20/2015.
 */
public class DirFinger extends Finger {
    private Spaceship spaceship;

    public DirFinger(float x, float y, int id, GameView parent) {
        startX = x;
        startY = y;
        this.id = id;
        this.parent = parent;
        //color.setColor(Color.BLACK);
        //color = new Paint();
        //System.out.println("dir");
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void moveShip() {

        float xDir = startX - x;
        float yDir = startY - y;
        //System.out.println(xDir + " " + yDir);
        if (Math.abs(xDir) > Math.abs(yDir)) {
            if (xDir < 0) {
                spaceship.setDir(1);
            } else {
                spaceship.setDir(3);
            }
        } else {
            if (yDir < 0) {
                spaceship.setDir(2);
            } else {
                spaceship.setDir(0);
            }
        }

        // new try :)
        /*if(Math.abs(x - startX) > Math.abs(startY - y)){
            if(Math.abs(x - startX) > 0){
                spaceship.setDir(1);
            }else{
                spaceship.setDir(3);
            }
        }else{
            if(Math.abs(startY - y) > 0){
                spaceship.setDir(2);
            }else{
                spaceship.setDir(0);
            }
        }*/
        //System.out.println(startX + " " + startY + " | " + x + " " + y + " | " + spaceship.getDir());
    }


    public void move(float x, float y) {
        //System.out.println(x + " " + y);
        this.x = x;
        this.y = y;
        moveShip();
    }

}