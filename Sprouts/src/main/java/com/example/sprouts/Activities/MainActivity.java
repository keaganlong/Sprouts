package com.example.sprouts.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.sprouts.R;

public class MainActivity extends ActionBarActivity {
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.playButton = (Button)this.findViewById(R.id.play);
        this.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,GameActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
