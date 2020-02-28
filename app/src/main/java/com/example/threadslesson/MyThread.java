package com.example.threadslesson;

import android.animation.ArgbEvaluator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

public class MyThread extends Thread {

    private boolean flag;
    private long starttime;
    private Paint paint;
    private SurfaceHolder holder;
    private long prevRedrawTime;
    private ArgbEvaluator argbev;

    MyThread(SurfaceHolder h){
        this.holder=h;
        this.flag=false;
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        argbev=new ArgbEvaluator();
    }

    public long getTime(){
        return System.nanoTime()/1000;
    }

    public void setRunning(boolean f){
        flag=f;
        //prevRedrawTime=getTime();
    }

    public void run(){
        Canvas canvas;
        //starttime=getTime();
        while(flag){
            canvas=holder.lockCanvas();
            Random rand=new Random();
            //int color= Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
            int color=(int) argbev.evaluate((float)Math.random(),Color.MAGENTA,Color.RED);
            paint.setColor(color);
            int radius=Math.min(canvas.getWidth(),canvas.getHeight())/2;
            canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,radius,paint);
            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            holder.unlockCanvasAndPost(canvas);
        }


    }
}
