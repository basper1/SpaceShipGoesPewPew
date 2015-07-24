package com.example.student.asteroids;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.student.asteroids.Enemies.Enemy;

import java.util.*;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public GameThread thread;
    public Spaceship spaceship;
    private boolean down = false;
    Paint color = new Paint();
    public int height = this.getHeight();
    public int width = this.getWidth();
    public Score score = new Score(this);
    //HashSet<Bullet> bullets = new HashSet<>();
    //color.setColor(Color.RED);
    public HashMap<Integer,Finger> fingers = new HashMap<>();
    public HashSet<Enemy> enemies = new HashSet<>();
    public int gameState;
    private Paint paint;
    public int highscore;
    public MainActivity a;


    // shity start
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);
        setFocusable(true);
        spaceship = new Spaceship(BitmapFactory.decodeResource(getResources(),
                R.drawable.spaceship),getHeight() / 2, getWidth() / 2,this);
        Enemy p = new Enemy(this);
        enemies.add(p);
        p.moveTo(200, 200);
        Enemy w = new Enemy(this);
        enemies.add(w);
        w.moveRand();
        gameState = 0;
        paint = new Paint();

    }
    // changes game stat 0 = launch, 1 = game, 2 = deathscreen
    public void setGameState(int state){
        gameState = state;
        for(Enemy p:enemies){
            p.timer = 4000;
            p.size = 50;
            p.lastRes = System.currentTimeMillis();
            p.moveRand();
        }
    }

    public void removeEnemy(Enemy enemy){
        score.addScore(1);
        SharedPreferences sharedPref = a.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //System.out.println(highscore);
        if(score.score > highscore){

            editor.putInt("HighScore" , score.score);
            highscore = score.score;
            editor.apply();
        }
        enemy.resetTime();
        enemy.size = Math.max(enemy.size - 1,20);

        if(enemy.size <= 20){
            if(enemy.timer > 20) enemy.timer -= 50;
            else enemy.timer -= 10;
        }
        enemy.moveRand();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = false;
        while(retry){
            try{
                thread.join();
                retry = false;
            }catch (InterruptedException e){
            }
        }
    }

    public void removeFinger(int id){
        fingers.remove(id);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //System.out.println(event);
        int index = event.getActionIndex();
        int id = event.getPointerId(event.getActionIndex());
        int action = MotionEventCompat.getActionMasked(event);
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
            //System.out.println(fingers.size());
            if(gameState == 0 || gameState == 2){
                setGameState(1);
                score.score = 0;
            }
            if(fingers.get(0) == null) {

                MoveFinger p = new MoveFinger(event.getX(index), event.getY(index), id, this,spaceship);
                fingers.remove(id);
                fingers.put(0, p);
                p.setSpaceship(spaceship);
            }else if(fingers.get(1) == null){
                DirFinger k = new DirFinger(event.getX(index), event.getY(index), id, this);
                fingers.put(1, k);
                k.setSpaceship(spaceship);
                //System.out.println("finger at 1, 2");
            }else{
                Finger k = new Finger(event.getX(index), event.getY(index), id, this);
                fingers.put(id, k);
            }
        } else if (action == MotionEvent.ACTION_MOVE) {
            //break;
            //case MotionEvent.ACTION_MOVE:
            int count = event.getPointerCount();
            for (int i = 0; i < count; i++) {
                if (fingers.get(event.getPointerId(i)) != null)
                    fingers.get(event.getPointerId(i)).move(event.getX(i), event.getY(i));
            }
        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) {
            //System.out.println(fingers.size() + " sdkjjfhaslkjghadfjklha");
            //break;

            //break;
            //case MotionEvent.ACTION_UP:
            fingers.remove(id);
            spaceship.stopShip();

            //break;
            //case MotionEvent.ACTION_POINTER_UP:
            //break;

        } else if (action == MotionEvent.ACTION_OUTSIDE){
            //case MotionEvent.ACTION_OUTSIDE:
            fingers.get(id).remove();
        }  else if(action == MotionEvent.ACTION_CANCEL) {
            //break;
            //case MotionEvent.ACTION_CANCEL:
            fingers.clear();
            //break;
        }

        return true;
    }



    @Override
    protected void onDraw(Canvas canvas) {

       try {
           canvas.drawColor(Color.BLUE);
           for(Integer i:fingers.keySet()){
               fingers.get(i).draw(canvas);
           }
           if(gameState == 1) {
               for (Enemy k : enemies) {

                   k.draw(canvas);
                   for (Bullet i : spaceship.bullets) {
                       if (Math.abs(k.getX() - i.getX()) < k.size && Math.abs(k.getY() - i.getY()) < k.size) {
                           spaceship.bullets.remove(i);
                           removeEnemy(k);
                       }
                   }
                   if(Math.abs(k.getX() - spaceship.getX()) <= 30 && Math.abs(k.getY() - spaceship.getY()) <= 30){
                       setGameState(2);
                   }
               }
               spaceship.draw(canvas);
               score.draw(canvas);
           }else if(gameState == 0){
               paint.setColor(Color.BLACK);
               paint.setTextSize(100);
               canvas.drawText("Start", 0, 200, paint);
               paint.setTextSize(22);
               canvas.drawText("first finger is move", 0, 260, paint);
               canvas.drawText("second finger is shoot",0,280,paint);
               canvas.drawText("destroy the circles before they disappear",0,300,paint);
           }else{
               paint.setColor(Color.BLACK);
               paint.setTextSize(100);
               canvas.drawText("Score:" + score.score,0,200,paint);
               canvas.drawText("HighScore: " + highscore,0,300,paint);
           }

       }catch(Exception p){
           //System.out.println("asdfjskadf  " + p + "  " + fingers.size());
           //thread.join();

       }
    }
}
