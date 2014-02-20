package com.example.sprouts.Game.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.example.sprouts.Graphics.Drawing.DrawRoot;
import com.example.sprouts.Graphics.Drawing.IDrawStrategy;
import com.example.sprouts.Graphics.Drawing.IDrawable;

import java.util.ArrayList;

/**
 * Created by Keagan on 2/19/14.
 */
public class Root implements IDrawable{
    public IDrawStrategy drawStrategy = new DrawRoot(this);

    public Path currentPath;

    public Root(){
        currentPath = new Path();
    }

    public void clear(){
        currentPath = new Path();
    }

    public void draw(Canvas canvas, Paint paint){
        drawStrategy.draw(canvas, paint);
    }
}
