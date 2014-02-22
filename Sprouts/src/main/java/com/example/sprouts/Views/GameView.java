package com.example.sprouts.Views;
import com.example.sprouts.Game.GameSettings;
import com.example.sprouts.Game.Objects.Root;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.appcompat.R;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.MotionEvent;
import com.example.sprouts.Controllers.GameController;
import com.example.sprouts.Game.Objects.Node;
import com.example.sprouts.Game.Threads.GameLoopThread;
import com.example.sprouts.Graphics.Drawing.IDrawable;

import android.graphics.Path;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private Paint paint = new Paint();
    public GameController gameController;
    private GameSettings gameSettings;
    private SurfaceHolder surfaceHolder;
    private GameLoopThread gameLoopThread;

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public GameView(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(500, 500));
        setDrawingCacheEnabled(true);
        gameController = GameController.getInstance();
        gameSettings = new GameSettings(gameController,3);
        gameSettings.initializeAll();

        gameLoopThread = new GameLoopThread(this);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        for(IDrawable root: gameController.getRootsCopy()){
            root.draw(canvas,paint);
        }

        for(IDrawable node: gameController.getNodesCopy()){
            node.draw(canvas,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                gameController.actionDown(event);
                break;
            case MotionEvent.ACTION_UP:
                gameController.actionUp(event);
                break;
            case MotionEvent.ACTION_MOVE:
                gameController.actionMove(event);
                break;
        }
        return true;
    }
}