package com.example.student.asteroids;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by student on 7/20/2015.
 */
public class DirFinger extends Finger{
    private GameView parent;
    private ArrayList<Float> x = new ArrayList<>();
    private ArrayList<Float> y = new ArrayList<>();
    private Spaceship spaceship;
    private Paint color;

    public DirFinger(float x,float y, int id,GameView parent){
        super(x,y,id,parent);
        color = new Paint();
    }

    public void setSpaceship(Spaceship spaceship){
        this.spaceship = spaceship;
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void moveShip(){
        if(Math.abs(x.get(x.size() - 1)) > Math.abs(y.get(y.size() - 1))){
            if(x.get(x.size() - 1) > 0){
                spaceship.setDir(1);
            }else{
                spaceship.setDir(3);
            }
        }else{
            if(y.get(y.size() - 1) > 0){
                spaceship.setDir(2);
            }else{
                spaceship.setDir(0);
            }
        }
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
