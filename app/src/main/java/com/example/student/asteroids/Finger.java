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
        color = new Paint();
        color.setColor(Color.BLACK);
    }
    public Finger(float x,float y, int id,GameView parent){
        //System.out.println(x + " " + y);
        this.x.add(x);
        this.y.add(y);
        this.id = id;
        this.parent = parent;
    }

    public void move(float x,float y){
        this.x.add(x);
        this.y.add(y);
    }

    public void remove(){
        parent.removeFinger(id);
    }

    public int getSize(){
        return x.size();
    }

    public void draw(Canvas canvas){
        if(x.size() > 0)
            try {
                canvas.drawCircle(x.get(x.size() - 1), y.get(x.size() - 1), 10, color);
            }catch(IndexOutOfBoundsException e){
                System.out.println("ERROR ERROR ERROR" + e);
            }
        else
            remove();
    }


}
