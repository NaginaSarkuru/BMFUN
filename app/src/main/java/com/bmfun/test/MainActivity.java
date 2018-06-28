package com.bmfun.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Buttonnext2;
    private Button Buttonnext3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Buttonnext2 = (Button) findViewById(R.id.button_NEXT2);
        Buttonnext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGAME2MAIN();
            }
        });


        Buttonnext3 = (Button) findViewById(R.id.button_NEXT3);
        Buttonnext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGAME3();
            }
        });


    }

    public void openGAME2MAIN(){
        Intent intent = new Intent(this, GAME2MAIN.class);
        startActivity(intent);
    }

    public void openGAME3(){
        Intent intent = new Intent(this, GAME3.class);
        startActivity(intent);
    }
}
