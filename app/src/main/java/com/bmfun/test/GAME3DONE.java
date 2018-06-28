package com.bmfun.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bmfun.test.DbHelper.DbHelper;

public class GAME3DONE extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtTotalScore, txtTotalQuestion;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3_done);

        DbHelper db = new DbHelper(this);

        btnTryAgain = (Button)findViewById(R.id.btnTryAgain);
        txtTotalScore = (TextView)findViewById(R.id.txtTotalScore);
        txtTotalQuestion = (TextView)findViewById(R.id.txtTotalQuestion);
        progressBar = (ProgressBar)findViewById(R.id.doneProgressBar);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Get Data from Playing
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");
            txtTotalScore.setText(String.format("SCORE: %d",score));
            txtTotalQuestion.setText(String.format("PASSED: %d/%d",score,totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);

            //Insert score to database
            db.insertScore(score);
        }


    }
}
