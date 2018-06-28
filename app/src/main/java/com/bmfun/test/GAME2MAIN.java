package com.bmfun.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GAME2MAIN extends AppCompatActivity {

    Button buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2_main);

        buttonPlay = (Button) findViewById(R.id.buttonPlay);


        buttonPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GAME2.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
