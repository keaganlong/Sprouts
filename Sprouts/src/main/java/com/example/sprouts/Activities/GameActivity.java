package com.example.sprouts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;

import com.example.sprouts.Controllers.GameController;
import com.example.sprouts.Views.GameView;
import com.example.sprouts.R;

public class GameActivity extends Activity {
    GameView gameView;

    @Override
    public void onBackPressed(){
        GameController.destroyInstance();
        Intent myIntent = new Intent(GameActivity.this,MainActivity.class);
        GameActivity.this.startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = new GameView(this);
        gameView.setBackgroundColor(Color.WHITE);
        setContentView(gameView);
    }
}
