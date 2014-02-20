package com.example.sprouts.Game;

import android.graphics.Color;

import com.example.sprouts.Graphics.GameColors;

/**
 * Created by Keagan on 2/17/14.
 */
public class Player {
    public int id;
    public int color;

    public Player(int id){
        this.id = id;
        if(id==1) this.color = GameColors.PLAYER_1_COLOR;
        else this.color = GameColors.PLAYER_2_COLOR;
    }
}
