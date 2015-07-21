package com.example.student.asteroids;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.*;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
    private Spaceship spaceship;
    private boolean down = false;
    Paint color = new Paint();
    //HashSet<Bullet> bullets = new HashSet<>();
    //color.setColor(Color.RED);
    public HashMap<Integer,Finger> fingers = new HashMap<>();



    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);
        setFocusable(true);
        spaceship = new Spaceship(BitmapFactory.decodeResource(getResources(),
                R.drawable.spaceship),getHeight() / 2, getWidth() / 2,this);
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
        //if(action != MotionEvent.ACTION_MOVE)
        //System.out.println(actionToString(action) + "  " + event.getActionIndex() + "  " + event.getPointerId(event.getActionIndex()) + "  " + event.getX(index) + "  " + event.getY(index));
        //System.out.println(event.getAction());
        //switch(action){
        //case MotionEvent.ACTION_DOWN:
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
            //System.out.println(fingers.size());
            if(fingers.get(0) == null) {

                MoveFinger p = new MoveFinger(event.getX(index), event.getY(index), id, this,spaceship);
                fingers.remove(id);
                fingers.put(0, p);
                p.setSpaceship(spaceship);
                //System.out.println(fingers.size());
                System.out.println("finger at 0");
            }else if(fingers.get(1) == null){
                //case MotionEvent.ACTION_POINTER_DOWN:
                //System.out.println(" pointer down" + id);
                DirFinger k = new DirFinger(event.getX(index), event.getY(index), id, this);
                fingers.put(1, k);
                k.setSpaceship(spaceship);
                System.out.println("finger at 1, 2");
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

    public static String actionToString(int action) {
        switch (action) {

            case MotionEvent.ACTION_DOWN: return "Down";
            case MotionEvent.ACTION_MOVE: return "Move";
            case MotionEvent.ACTION_POINTER_DOWN: return "Pointer Down";
            case MotionEvent.ACTION_UP: return "Up";
            case MotionEvent.ACTION_POINTER_UP: return "Pointer Up";
            case MotionEvent.ACTION_OUTSIDE: return "Outside";
            case MotionEvent.ACTION_CANCEL: return "Cancel";
        }
        return "nothing";
    }


    @Override
    protected void onDraw(Canvas canvas) {

       try {
            canvas.drawColor(Color.GREEN);
            /*if (!fingers.isEmpty())
                    for (Integer i : fingers.keySet()) {

                            fingers.get(i).draw(canvas);
                    }
*/
            //System.out.println(" ");
            spaceship.draw(canvas);
        }catch(Exception p){
                //System.out.println("asdfjskadf  " + p + "  " + fingers.size());
                //thread.join();

            }
    }


   /*public  Spaceship getSpaceship() {
        return spaceship;
    }*/
}
