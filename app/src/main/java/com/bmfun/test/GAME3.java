package com.bmfun.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bmfun.test.Common.Common;
import com.bmfun.test.DbHelper.DbHelper;

import java.io.IOException;

public class GAME3 extends AppCompatActivity {

    SeekBar seekBar;
    TextView txtLevel;
    Button btnPlay, btnScore;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        txtLevel = (TextView) findViewById(R.id.txtLevel);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnScore = (Button) findViewById(R.id.btnScore);

        db = new DbHelper(this);
        try{
            db.createDatabase();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //Event
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0)
                    txtLevel.setText(Common.LEVEL.LEVEL1.toString());
                else if(progress == 1)
                    txtLevel.setText(Common.LEVEL.LEVEL2.toString());
                else if(progress == 2)
                    txtLevel.setText(Common.LEVEL.LEVEL3.toString());
                else if(progress == 3)
                    txtLevel.setText(Common.LEVEL.LEVEL4.toString());
                else if(progress == 4)
                    txtLevel.setText(Common.LEVEL.LEVEL5.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PlayingGame3.class);
                intent.putExtra("LEVEL",getPlayLevel()); //Sent Level to Playing Page
                startActivity(intent);
                finish();
            }
        });

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GAME3SCORE.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private String getPlayLevel() {
        if(seekBar.getProgress()==0)
            return Common.LEVEL.LEVEL1.toString();
        else if(seekBar.getProgress()==1)
            return Common.LEVEL.LEVEL2.toString();
        else if(seekBar.getProgress()==2)
            return Common.LEVEL.LEVEL3.toString();
        else if(seekBar.getProgress()==3)
            return Common.LEVEL.LEVEL4.toString();
        else
            return Common.LEVEL.LEVEL5.toString();
    }

}
