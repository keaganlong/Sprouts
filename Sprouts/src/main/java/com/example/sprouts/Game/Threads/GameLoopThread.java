package com.example.sprouts.Game.Threads;

import android.graphics.Canvas;

import com.example.sprouts.Views.GameView;

/**
 * Created by Keagan on 2/22/14.
 */
public class GameLoopThread extends Thread {
    private GameView gameView;
    private boolean running = false;
    private static final long FPS = 30;

    public GameLoopThread(GameView gameView){
        this.gameView = gameView;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    @Override
    public void run(){
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            Canvas canvas = null;
            startTime = System.currentTimeMillis();
            try {
                canvas = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()) {
                    //gameView.onDraw(canvas);
                    gameView.postInvalidate();
                }
            } finally {
                if (canvas != null) {
                    gameView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }
}
