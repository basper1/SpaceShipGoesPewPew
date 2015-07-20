package com.example.student.asteroids;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.HashSet;


/**
 * Created by student on 7/13/2015.
 */
public class Spaceship {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private long lastDraw = System.currentTimeMillis();
    private long lastShoot = System.currentTimeMillis();
    private GameView parent;
    private int dir = 2;
    private HashSet<Bullet> bullets;
    public final float FIREDELAY = 500;

    public Spaceship(Bitmap bitmap, int x,int y, GameView parent){
        bullets = new HashSet<>();
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public void stopShip() {
        xVelocity = 0;
        yVelocity = 0;
    }

    public void bullets(Canvas canvas){

        for(Bullet p: bullets){
            //System.out.println("asdf");
            p.move();
            p.draw(canvas);
            if(p.out()) bullets.remove(p);
        }
        if(System.currentTimeMillis() - lastShoot > FIREDELAY){
            lastShoot = System.currentTimeMillis();
            Bullet k = new Bullet(this,parent.getWidth(),parent.getHeight());
            bullets.add(k);
            //System.out.println("asdffhgfhf");
        }
    }



    public void draw(Canvas canvas) {
        long movement = System.currentTimeMillis() - lastDraw ;
        lastDraw =System.currentTimeMillis();
        canvas.drawBitmap(DirFinger.RotateBitmap(bitmap, dir * 90), x, y, null);
        bullets(canvas);
        x = Math.max(x + xVelocity * (int) movement / 50, 0);
        x = Math.min(x, parent.getWidth() - bitmap.getWidth());
        y = Math.max(y + yVelocity * (int) movement / 50, 0);
        y = Math.min(y, parent.getHeight() - bitmap.getHeight());
        //System.out.println(dir);
    }


    public void setXVelocity(int xVelocity) {
        //this.xVelocity = Math.max(0 - 100,xVelocity);
        this.xVelocity = Math.min( 100,xVelocity);
        if(this.xVelocity < -100) this.xVelocity = -100;
    }
    
    public void setYVelocity(int yVelocity) {
        //this.yVelocity = Math.max(0 - 100,yVelocity);
        this.yVelocity = Math.min( 100,yVelocity);
        if(this.yVelocity < -100) this.yVelocity = -100;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }

    public GameView getParent() {
        return parent;
    }
}
