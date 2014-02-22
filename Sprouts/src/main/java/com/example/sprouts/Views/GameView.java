package com.example.sprouts.Views;
import com.example.sprouts.Game.Objects.Root;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.MotionEvent;
import com.example.sprouts.Controllers.GameController;
import com.example.sprouts.Game.Objects.Node;
import com.example.sprouts.Graphics.Drawing.IDrawable;

import android.graphics.Path;
import android.widget.ImageView;

public class GameView extends ImageView {
    Paint paint = new Paint();
    GameController gameController = GameController.getInstance();
    Bitmap bitmap;

    public GameView(Context context) {
        super(context);
        bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        setImageBitmap(bitmap);
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),paint);

        for(IDrawable root: gameController.roots){
            root.draw(canvas,paint);
        }

        for(IDrawable node: gameController.nodes){
            node.draw(canvas,paint);
        }
        gameController.updateRoots(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                gameController.actionDown(event);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                gameController.actionUp(event);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                gameController.actionMove(event);
                invalidate();
                break;
        }
        return true;
    }
}