package com.example.student.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import java.util.*;

/**
 * Created by student on 7/15/2015.
 */
public class Finger {
    public int id;
    public GameView parent;
    public float startX;
    public float startY;
    public float x;
    public float y;
    public Paint color = new Paint();
    public Finger(){}

    public Finger(int id,GameView parent){
        this.id = id;
        this.parent = parent;
        color.setColor(Color.BLACK);
    }
    public Finger(float x,float y, int id,GameView parent){
        //System.out.println("finger created");
        startX = x;
        startY = y;
        this.id = id;
        this.parent = parent;
        color.setColor(Color.BLACK);
    }

    public void move(float x,float y){
        this.x = x;
        this.y = y;
    }

    public void remove(){
        parent.removeFinger(id);
    }


    public void draw(Canvas canvas){
        if(color != null) {
            color.setColor(Color.BLACK);
            color.setAlpha(25);
            System.out.println(color.getAlpha());

            canvas.drawCircle(startX, startY, 100, color);
        }else{
            color = new Paint();
        }
    }


}
