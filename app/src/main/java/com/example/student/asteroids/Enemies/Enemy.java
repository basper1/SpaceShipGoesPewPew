package com.example.student.asteroids.Enemies;

import com.example.student.asteroids.GameView;

/**
 * Created by student on 7/21/2015.
 * pop up and shoot enemy;
 * chase you enemy;
 * dash enemy;
 * run away from u and shoot enemy;
 * suiceide bomber enemy;
 *
 *
 *
 */
public class Enemy {
    private int x;
    private int y;
    GameView parent;
    int value;

    public Enemy(GameView parent){
        this.parent = parent;
    }

    public Enemy(GameView parent,int v){
        this.parent = parent;
        value = v;
    }

    public void moveTo(int x,int y){
        this.x = x;
        this.y = y;
        inSpace();
    }

    public void move(int x,int y){
        this.x += x;
        this.y += y;
    }

    public void setValue(int v){
        value  = v;
    }

    public void inSpace(){
        x = Math.max(0,Math.min(parent.getWidth(),x));
        y = Math.max(0,Math.min(parent.getHeight(),y));
    }
    public void draw(){

    }
}
