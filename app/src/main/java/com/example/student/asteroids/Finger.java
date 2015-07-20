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
    private int id;
    private GameView parent;
    private Paint color = new Paint();
    private ArrayList<Float> x = new ArrayList<>();
    private ArrayList<Float> y = new ArrayList<>();
    public Finger(){}

    public Finger(int id,GameView parent){
        this.id = id;
        this.parent = parent;
        color.setColor(Color.BLACK);
    }
    public Finger(float x,float y, int id,GameView parent){
        System.out.println("finger created");
        this.x.add(x);
        this.y.add(y);
        this.id = id;
        this.parent = parent;
        color.setColor(Color.BLACK);
    }

    public void move(float x,float y){
        this.x.add(x);
        this.y.add(y);
    }

    public void remove(){
        parent.removeFinger(id);
    }

    public int getSize(){
        System.out.println(x.size() + " sdafjag");
        return x.size();
    }

    public void draw(Canvas canvas){
        System.out.println(x.get(0) + " - " + y.get(0));
        if(x.size() > 0)
            try {

                canvas.drawCircle(x.get(0), y.get(0), 100, color);
            }catch(IndexOutOfBoundsException e){
                System.out.println("ERROR ERROR ERROR" + e);
            }
        else
            remove();
    }


}
