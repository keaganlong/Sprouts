package com.example.sprouts.Graphics.Drawing;
import com.example.sprouts.Game.Objects.Node;
import android.graphics.Canvas;
import android.graphics.Paint;
/**
 * Created by Keagan on 2/19/14.
 */
public class DrawNode implements IDrawStrategy{
    Node node;

    public DrawNode(Node node){
        this.node = node;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.reset();
        paint.setColor(node.color);
        canvas.drawCircle(node.x, node.y, node.radius, paint);
    }
}
