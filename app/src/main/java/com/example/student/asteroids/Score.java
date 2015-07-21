package com.example.student.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by student on 7/21/2015.
 */
public class Score {
    public int score = 0;
    private GameView parent;
    private Paint paint;

    public Score(GameView parent){
        this.parent = parent;
        paint = new Paint();
        paint.setTextSize(20);
    }

    public void addScore(int s){
        score += s;
    }

    public void draw(Canvas canvas){
        //System.out.println("score: " + score);
        paint.setColor(Color.BLUE);
        canvas.drawText("Score: " + score,0,20,paint);
    }
}
