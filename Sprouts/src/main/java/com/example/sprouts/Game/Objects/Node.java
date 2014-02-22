package com.example.sprouts.Game.Objects;
import com.example.sprouts.Game.Player;
import com.example.sprouts.Graphics.Drawing.IDrawable;
import com.example.sprouts.Graphics.Drawing.IDrawStrategy;
import com.example.sprouts.Graphics.Drawing.DrawNode;
import com.example.sprouts.Graphics.GameColors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Vibrator;

/**
 * Created by Keagan on 2/17/14.
 */
public class Node implements IDrawable{
    IDrawStrategy drawStrategy = new DrawNode(this);

    public float x;
    public float y;
    public int color;
    public int radius;
    public int inDegree;
    public Player owner;
    public static final int NODE_SELECTED_RADIUS = 40;
    public static final int NODE_RADIUS = 17;
    public static final int NODE_CAPACITY = 3;
    public boolean selected;

    public Node(float x, float y, Player owner){
        inDegree = 0;
        selected = false;
        this.x = x;
        this.y = y;
        this.color = GameColors.NODE_COLOR_0;
        this.radius = NODE_RADIUS;
        this.owner = owner;
    }

    public Node(float x, float y, Player owner, int inDegree){
        this.inDegree = inDegree;
        selected = false;
        this.x = x;
        this.y = y;
        this.color = GameColors.NODE_COLORS[inDegree];
        this.radius = NODE_RADIUS;
        this.owner = owner;
    }

    public void click(){
        selected = true;
    }

    public void unClick(){
        selected = false;
    }

    public void draw(Canvas canvas, Paint paint){
        drawStrategy.draw(canvas,paint);
    }

    public void set(){
        inDegree++;
        color = GameColors.NODE_COLORS[inDegree];
    }

    public void unSet(){
        inDegree--;
        color = GameColors.NODE_COLORS[inDegree];
    }


    public boolean isActive(){
        return inDegree<NODE_CAPACITY;
    }
}
