package com.example.sprouts.Graphics.Drawing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.sprouts.Game.Objects.Coordinate;
import com.example.sprouts.Game.Objects.Root;

/**
 * Created by Keagan on 2/19/14.
 */
public class DrawRoot implements IDrawStrategy{
    public Root root;

    public DrawRoot(Root root){
        this.root = root;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(root.player.color);
        canvas.drawPath(root.path,paint);
    }
}
