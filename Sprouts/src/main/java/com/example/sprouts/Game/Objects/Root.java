package com.example.sprouts.Game.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.sprouts.Game.Player;
import com.example.sprouts.Graphics.Drawing.DrawRoot;
import com.example.sprouts.Graphics.Drawing.IDrawStrategy;
import com.example.sprouts.Graphics.Drawing.IDrawable;

import java.util.ArrayList;

/**
 * Created by Keagan on 2/19/14.
 */
public class Root implements IDrawable{
    public IDrawStrategy drawStrategy = new DrawRoot(this);
    public Player player;
    public ArrayList<Coordinate> coordinates;
    public Path path;
    private static float STEP_SIZE = 0.5f;

    public Root(Player player){
        coordinates = new ArrayList();
        this.player = player;
        path = new Path();
    }

    public void clear(){
        path = new Path();
    }

    public void draw(Canvas canvas, Paint paint){
        drawStrategy.draw(canvas, paint);
    }

    public void startAt(float x, float y){
        path.moveTo(x,y);
    }

    public void lineTo(float x, float y){
        path.lineTo(x,y);
    }

//    public void lineTo(float x1, float y1){
//        float x = currCoord.x;
//        float y = currCoord.y;
//        float deltaX = (x1-x);
//        float deltaY = (y1-y);
//        float m = 0;
//        if(deltaX!=0){
//            m = deltaY/deltaX;
//        }
//        float b = y-m*x;
//        if(deltaX>0){
//            if(deltaY>0){
//                while(x<x1){
//                    x+=STEP_SIZE;
//                    y=x*m+b;
//                    coordinates.add(new Coordinate((int)x,(int)y));
//                }
//            }
//            else if(deltaY<0){
//                while(x<x1){
//                    x+=STEP_SIZE;
//                    y=x*m+b;
//                    coordinates.add(new Coordinate((int)x,(int)y));
//                }
//            }
//            else
//                while(x<x1){
//                    x+=STEP_SIZE;
//                    y=x*m+b;
//                    coordinates.add(new Coordinate((int)x,(int)y));
//                }
//        }
//        else if(deltaX < 0){
//            if(deltaY>0){
//                while(x>x1){
//                    x-=STEP_SIZE;
//                    y=x*m+b;
//                    coordinates.add(new Coordinate((int)x,(int)y));
//                }
//            }
//            else if(deltaY<0){
//                while(x>x1){
//                    x-=STEP_SIZE;
//                    y=x*m+b;
//                    coordinates.add(new Coordinate((int)x,(int)y));
//                }
//            }
//            else{
//                while(x>x1){
//                    x-=STEP_SIZE;
//                    y=x*m+b;
//                    coordinates.add(new Coordinate((int)x,(int)y));
//                }
//            }
//        }
//        else{
//            if(deltaY > 0){
//                while(y<y1){
//                    y+=STEP_SIZE;
//                    coordinates.add(new Coordinate((int)x,(int)y));
//                }
//            }
//            else if(deltaY<0){
//                while(y>y1){
//                    y-=STEP_SIZE;
//                    coordinates.add(new Coordinate((int)x,(int)y));
//                }
//            }
//            else{
//            }
//        }
//
//        currCoord = coordinates.get(coordinates.size()-1);
//    }
}
