package com.example.student.asteroids;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by student on 7/17/2015.
 */
public class Button {
    private int id;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Paint paint;
    private String text;
    private boolean visable;

    public Button(int x1,int y1,int x2,int y2,String text){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.text = text;
        visable = false;
    }

    public void setVisable(boolean t){
        visable = t;
    }

    public void draw(Canvas canvas){
        canvas.drawRect(x1,y1,x2,y2,paint);
        canvas.drawText(text,x1,y1,paint);
    }


}
