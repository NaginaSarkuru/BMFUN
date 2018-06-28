package com.example.mcflurry.bmfun;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity5 extends AppCompatActivity  {
    ImageView iv1, iv2, iv3, iv4, iv5;
    android.widget.LinearLayout dropLayout;
    TextView tvQuestion, tvAnswer, tvMaksud, mTextViewCountdown;
    Button btnNext;
    int total, activePic = 0;
    private static final long START_TIME_MILLIS = 60000; //1min
    private long mTimeLeftinMillis = START_TIME_MILLIS;
    private CountDownTimer mCountDownTimer;

    Random r;

    private static final String TAG = "MainActivity5";

    // Database Helper
    DatabaseHelper db;

    Peribahasa pb1,pb2,pb3,pb4,pb5;
//    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_5);
        dropLayout = (LinearLayout) findViewById(R.id.bottomLinear);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);

        btnNext = (Button) findViewById(R.id.btnNext);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer) ;
        tvMaksud = (TextView) findViewById(R.id.tvMaksud) ;
        mTextViewCountdown = findViewById(R.id.text_view_countdown);
        mTimerRunning();
        updateCountDownText();

        db = new DatabaseHelper(this);


        dropLayout.setOnDragListener(new View.OnDragListener(){


            @Override
            public boolean onDrag(View v, DragEvent event) {
                //TODO
                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DROP: {
                        //u ser has released the drag shadow
                        //                        fail = fail + 1;
//                        return (true);
                        total = total + 1;
                    }
                    case DragEvent.ACTION_DRAG_ENDED: {
                        //Shadow returns back only Activate drag_ended
                        // drag and drop operation has concluded

                        if (activePic == 1) {
                            tvAnswer.setText(iv1.getContentDescription());
                        } else if (activePic == 2) {
                            tvAnswer.setText(iv2.getContentDescription());
                        } else if (activePic == 3) {
                            tvAnswer.setText(iv3.getContentDescription());
                        } else if (activePic == 4) {
                            tvAnswer.setText(iv4.getContentDescription());
                        } else if (activePic == 5) {
                            tvAnswer.setText(iv5.getContentDescription());
                        }

                        return (true);
                    }
                    default:
                        break;

                }
                return true;

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });



        iv1.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //TODO
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(iv1);
                v.startDrag(data, shadow, null, 0);
                activePic = 1;
                return false;
            }



        });

        iv2.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //TODO
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(iv2);
                v.startDrag(data, shadow, null, 0);
                activePic = 2;
                return false;
            }
        });

        iv3.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //TODO
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(iv3);
                v.startDrag(data, shadow, null, 0);
                activePic = 3;
                return false;
            }
        });

        iv4.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //TODO
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(iv4);
                v.startDrag(data, shadow, null, 0);
                activePic = 4;
                return false;
            }
        });

        iv5.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //TODO
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(iv5);
                v.startDrag(data, shadow, null, 0);
                activePic =5;
                return false;
            }
        });



        insertQuestion();

    }

    public void insertQuestion() {
        pb1 = new Peribahasa();
        pb2 = new Peribahasa();
        pb3= new Peribahasa();
        pb4= new Peribahasa();
        pb5= new Peribahasa();

        int pbQuestion1,pbQuestion2,pbQuestion3, pbQuestion4, pbQuestion5;
//        int imgView1, imgView2, imgView3;


        r = new Random();
        List<Integer> randomQuestions = new ArrayList<Integer>(); //ArrayList<>(Arrays.asList(1,2,3,4)); creates a "list view" on an array whose size can't change.
        randomQuestions.add(1);randomQuestions.add(2);randomQuestions.add(3);randomQuestions.add(4);randomQuestions.add(5);randomQuestions.add(6);randomQuestions.add(7);randomQuestions.add(8);randomQuestions.add(9);randomQuestions.add(10);randomQuestions.add(11);randomQuestions.add(12);randomQuestions.add(13);randomQuestions.add(14);randomQuestions.add(15);


        Collections.shuffle(randomQuestions);
        pbQuestion1 = randomQuestions.get(0);
        pbQuestion2 = randomQuestions.get(1);
        pbQuestion3 = randomQuestions.get(2);
        pbQuestion4 = randomQuestions.get(3);
        pbQuestion5 = randomQuestions.get(4);

        pb1 = db.getPeribahasa(pbQuestion1);
        pb2 = db.getPeribahasa(pbQuestion2);
        pb3 = db.getPeribahasa(pbQuestion3);
        pb4 = db.getPeribahasa(pbQuestion4);
        pb5 = db.getPeribahasa(pbQuestion5);


        // set Question as pb1
        tvQuestion.setText(pb1.getQuestion());
        tvQuestion.setContentDescription(pb1.getAnswer());
        tvMaksud.setText(pb1.getMaksud());

        String mDrawableName1 = pb1.getImg();
        int resID1 = getResources().getIdentifier(mDrawableName1 , null, getPackageName());
        String mDrawableName2 = pb2.getImg();
        int resID2 = getResources().getIdentifier(mDrawableName2 , null, getPackageName());
        String mDrawableName3 = pb3.getImg();
        int resID3 = getResources().getIdentifier(mDrawableName3 , null, getPackageName());
        String mDrawableName4 = pb4.getImg();
        int resID4 = getResources().getIdentifier(mDrawableName4 , null, getPackageName());
        String mDrawableName5 = pb5.getImg();
        int resID5 = getResources().getIdentifier(mDrawableName5 , null, getPackageName());

        List<ImageView> shuffleImgView = new ArrayList<ImageView>();
        shuffleImgView.add(iv1);shuffleImgView.add(iv2);shuffleImgView.add(iv3);shuffleImgView.add(iv4);shuffleImgView.add(iv5);
//        Log.d(TAG, "insertQuestion: shuffleImgView = " + shuffleImgView.toString());
        Collections.shuffle(shuffleImgView);
//        Log.d(TAG, "insertQuestion: shuffleImgView after shuffling = " + shuffleImgView.toString());
        shuffleImgView.get(0).setImageResource(resID1);
        shuffleImgView.get(0).setContentDescription(pb1.getAnswer());
        shuffleImgView.get(1).setImageResource(resID2);
        shuffleImgView.get(1).setContentDescription(pb2.getAnswer());
        shuffleImgView.get(2).setImageResource(resID3);
        shuffleImgView.get(2).setContentDescription(pb3.getAnswer());
        shuffleImgView.get(3).setImageResource(resID4);
        shuffleImgView.get(3).setContentDescription(pb4.getAnswer());
        shuffleImgView.get(4).setImageResource(resID5);
        shuffleImgView.get(4).setContentDescription(pb5.getAnswer());



    }

    public void checkAnswer() {
        // If answer correct, increase point and reshuffle image
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvQuestion.getContentDescription() == tvAnswer.getText()) {
                    Toast.makeText(MainActivity5.this,"Correct",Toast.LENGTH_SHORT).show();
                    tvAnswer.setText("");
                    insertQuestion();
                } else {
                    Toast.makeText(MainActivity5.this,"Wrong. Try Again!",Toast.LENGTH_SHORT).show();
                    tvAnswer.setText("");
                    minusTimer();
                }

            }


        });
    }



//    public void fetchPeribahasa(int peribahasa_id) {
//        // Getting todos under "Watchlist" tag name
//        Peribahasa pb = db.getPeribahasa(peribahasa_id);
//
//        Log.d("PERIBAHASA_ID", String.valueOf(pb.getId()));
//        Log.d("PERIBAHASA_PERIBAHASA", pb.getPeribahasa());
//        Log.d("PERIBAHASA_MAKSUD", pb.getMaksud());
//        Log.d("PERIBAHASA_QUESTION", pb.getQuestion());
//        Log.d("PERIBAHASA_ANSWER", pb.getAnswer());
//        Log.d("PERIBAHASA_IMAGE", String.valueOf(pb.getImg()));
//
//    }


//    public void fetchingAllPeribahasa(){
//
//        List<Peribahasa> peribahasaList = db.getAllPeribahasa();
//        for (Peribahasa pb : peribahasaList) {
//            Log.d("PERIBAHASA_ID", String.valueOf(pb.getId()));
//            Log.d("PERIBAHASA_PERIBAHASA", pb.getPeribahasa());
//            Log.d("PERIBAHASA_MAKSUD", pb.getMaksud());
//            Log.d("PERIBAHASA_QUESTION", pb.getQuestion());
//            Log.d("PERIBAHASA_ANSWER", pb.getAnswer());
//            Log.d("PERIBAHASA_IMAGE", String.valueOf(pb.getImg()));
//        }
//        //return something
//    }

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
                Gameover();
                Intent intent = new Intent(MainActivity5.this, com.example.mcflurry.bmfun.Score.class);
                startActivity(intent);
            }
        }.start();
    }


    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftinMillis/1000) / 60;
        int seconds = (int) (mTimeLeftinMillis/1000) % 60;

        String timeleftformatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        mTextViewCountdown.setText(timeleftformatted);

    }

    public void Gameover(){
        int score;
        if(mTextViewCountdown.getText() == "0"){
            score = 20;
        }
        else{
            score = 100;
        }
        db = new DatabaseHelper(this);
        db.insertScore(score);


    }

    public void minusTimer(){
        mTimeLeftinMillis = (mTimeLeftinMillis - 10000);
        mCountDownTimer.cancel();
        mTimerRunning();

    }





}
