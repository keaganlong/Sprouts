package com.example.sprouts.Game.Objects;
import com.example.sprouts.Game.Player;
import com.example.sprouts.Graphics.Drawing.IDrawable;
import com.example.sprouts.Graphics.Drawing.IDrawStrategy;
import com.example.sprouts.Graphics.Drawing.DrawNode;
import com.example.sprouts.Graphics.GameColors;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Keagan on 2/17/14.
 */
public class Node implements IDrawable{
    IDrawStrategy drawStrategy = new DrawNode(this);

    public float x;
    public float y;
    public int color;
    public int radius;
    public boolean isActive;
    public Player owner;
    public static final int NODE_SELECTED_RADIUS = 25;
    public static final int NODE_RADIUS = 17;

    public Node(float x, float y, Player owner){
        this.x = x;
        this.y = y;
        this.color = GameColors.NODE_COLOR;
        this.radius = NODE_RADIUS;
        this.owner = owner;
        isActive = true;
    }

    public void click(){
        color = GameColors.NODE_SELECTED_COLOR;
        radius = NODE_SELECTED_RADIUS;
    }

    public void unClick(){
        color = GameColors.NODE_COLOR;
        radius = NODE_RADIUS;
    }

    public void draw(Canvas canvas, Paint paint){
        drawStrategy.draw(canvas,paint);
    }
}
