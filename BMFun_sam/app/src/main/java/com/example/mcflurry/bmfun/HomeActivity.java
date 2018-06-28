package com.example.mcflurry.bmfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    Button btnEasy, btnHard, btnScore;

    public HomeActivity() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnEasy = (Button) findViewById(R.id.btnEasy);
        btnHard = (Button) findViewById(R.id.btnHard);
        btnScore = (Button)findViewById(R.id.btnScore);

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this, MainActivity.class);
                HomeActivity.this.startActivity(myIntent);
            }
        });

        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this, MainActivity5.class);
                HomeActivity.this.startActivity(myIntent);
            }
        });
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this, com.example.mcflurry.bmfun.Score.class);
                HomeActivity.this.startActivity(myIntent);
            }
        });
    }

}

