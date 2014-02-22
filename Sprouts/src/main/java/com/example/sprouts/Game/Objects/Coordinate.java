package com.example.sprouts.Game.Objects;

/**
 * Created by Keagan on 2/20/14.
 */
public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x,int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o){
        if(o==null || !(o instanceof Coordinate)){
            return false;
        }
        return ((Coordinate)o).x==x && ((Coordinate)o).y==y;
    }
}
