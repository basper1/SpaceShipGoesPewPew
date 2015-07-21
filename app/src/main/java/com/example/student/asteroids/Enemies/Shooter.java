package com.example.student.asteroids.Enemies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.student.asteroids.Bullet;
import com.example.student.asteroids.GameView;
import com.example.student.asteroids.Spaceship;

/**
 * Created by student on 7/21/2015.
 */
public class Shooter extends Enemy{
    private boolean isUp = false;
    Paint paint = new Paint();
    public final int SIZE = 50;
    private int x;
    private int y;
    private int hp;
    GameView parent;
    int value;
    public Shooter(GameView gameView){
        super(gameView);
    }



    public void moveTo(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void draw(Canvas canvas){
        paint.setColor(Color.RED);
        canvas.drawCircle(x,y,SIZE,paint);
        if(isUp)
            paint.setColor(Color.GREEN);
            canvas.drawCircle(x,y,SIZE / 2, paint);
    }
}
