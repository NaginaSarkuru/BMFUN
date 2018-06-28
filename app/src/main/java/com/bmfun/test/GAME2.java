package com.bmfun.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bmfun.test.Adapter.GridViewAnswerAdapter;
import com.bmfun.test.Adapter.GridViewSuggestAdapter;
import com.bmfun.test.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GAME2 extends AppCompatActivity {

    private static final long START_TIME_MILLIS = 40000; //50secs
    private TextView mTextViewCountdown;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftinMillis = START_TIME_MILLIS;

    public List<String> suggestSource = new ArrayList<>();

    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;

    public Button btnSubmit;

    public GridView gridViewAnswer, gridViewSuggest;

    public ImageView imgViewQuestion;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount=1;
    static final private int QUIZ_COUNT = 10;


    int[] image_list={
            R.drawable.buahtangan,
            R.drawable.ulatbuku,
            R.drawable.kakiayam,
            R.drawable.cakarayam,
            R.drawable.carigaduh,
            R.drawable.carijalan,
            R.drawable.makanangin,
            R.drawable.ringantulang,
            R.drawable.jantunghati,
            R.drawable.kakibangku,
            R.drawable.berattulang,
            R.drawable.kakitangan,
            R.drawable.cakapbesar,
            R.drawable.rajasehari,
            R.drawable.langkahseribu,
            R.drawable.mataduitan,
            R.drawable.gajibuta,
            R.drawable.mandipeluh,
            R.drawable.banyakmulut,
            R.drawable.buahtangan,
            R.drawable.muluttempayan,
            R.drawable.peninglalat,
            R.drawable.saguhati,
            R.drawable.gelihati,
            R.drawable.adahati,
            R.drawable.matapetai,
            R.drawable.isirumah,
            R.drawable.rupaparas,
            R.drawable.anakemas,
            R.drawable.makanbawang



    };

    public char[] answer;

    String correct_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        mTextViewCountdown = findViewById(R.id.text_view_countdown);

        mTimerRunning();
        updateCountDownText();

        //Init View
        initView();
    }

    public void openGAME2MAIN(){
        Intent intent = new Intent(this,GAME2MAIN.class);
        startActivity(intent);
    }

    private void mTimerRunning (){
        mCountDownTimer = new CountDownTimer(mTimeLeftinMillis, 1000){
            @Override
            public void onTick(long millisUntilFinish){
                mTimeLeftinMillis = millisUntilFinish;
                updateCountDownText();
            }

            @Override
            public void onFinish(){
                mTextViewCountdown.setText("Time's Up!");
                openGAME2MAIN();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftinMillis/1000) / 60;
        int seconds = (int) (mTimeLeftinMillis/1000) % 60;

        String timeleftformatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        mTextViewCountdown.setText(timeleftformatted);

    }

    private void initView() {
        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView)findViewById(R.id.gridViewSuggest);

        imgViewQuestion = (ImageView)findViewById(R.id.imgLogo);

        //Add Setup List Here
        setupList();

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";
                for (int i = 0; i< Common.user_submit_answer.length; i++)
                    result+=String.valueOf(Common.user_submit_answer[i]);
                if(result.equals(correct_answer))
                {
                    Toast.makeText(getApplicationContext(),"Finish ! This is "+result,Toast.LENGTH_SHORT).show();

                    //Reset
                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    //Set Adapter
                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource,getApplicationContext(),GAME2.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    setupList();
                }
                else
                {
                    Toast.makeText(GAME2.this, "Incorrect !! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupList() {
        //Random logo
        Random random = new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);

        correct_answer = getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);

        answer = correct_answer.toCharArray();

        Common.user_submit_answer = new char[answer.length];

        //Add Answer Character To List
        suggestSource.clear();
        for (char item:answer)
        {
            //Add logo name to list
            suggestSource.add(String.valueOf(item));
        }

        //Random add some character to list
        for (int i=answer.length;i<answer.length*2;i++)
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);

        //Sort Random
        Collections.shuffle(suggestSource);

        //Set for GridView
        answerAdapter = new GridViewAnswerAdapter(setupNullList(),this);
        suggestAdapter = new GridViewSuggestAdapter(suggestSource,this,GAME2.this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);

    }

    public void checkAnswer(View view) {
        //Get pushed button

        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            //Correct
            alertTitle = "Correct" ;


        } else{
            //wrong
            alertTitle = "Wrong" ;

        }


        //Create Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {

                    //Show Result

                } else {
                    quizCount++;


                }


            }
        });

        builder.setCancelable(false);
        builder.show();
    }



    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for (int i=0;i<answer.length;i++)
            result[i]=' ';
        return result;
    }


}
