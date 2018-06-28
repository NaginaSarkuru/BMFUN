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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {
    ImageView iv1, iv2, iv3;
    android.widget.LinearLayout dropLayout;
    TextView tvQuestion, tvAnswer, tvMaksud, mTextViewCountdown;
    Button btnNext;
    int total, activePic = 0;
    private static final long START_TIME_MILLIS = 60000; //1min
    private long mTimeLeftinMillis = START_TIME_MILLIS;
    private CountDownTimer mCountDownTimer;

    Random r;
//    Integer[] images = {
//            R.drawable.anak,R.drawable.besi,R.drawable.bulan, R.drawable.cempedak, R.drawable.daging, R.drawable.daun, R.drawable.anjing,
//            R.drawable.dua,R.drawable.dua_belas,R.drawable.gajah,R.drawable.gelombang,R.drawable.harimau,R.drawable.itik,R.drawable.kacang,
//            R.drawable.kain,R.drawable.kerbau,R.drawable.keris,R.drawable.kuali,R.drawable.kuku,R.drawable.mabuk,R.drawable.tebing,
//            R.drawable.telur};

    Integer[] images = {
            R.drawable.telur, R.drawable.kuali, R.drawable.daging, R.drawable.dua_belas, R.drawable.keris, R.drawable.kuku, R.drawable.ikan
            };


    String[] questions = {
            "Bagai ayam mengeram ","Bagai betanak di ","Bagai duri dalam","Bagai gelombang","Bagai gembala diberi ", "Bagai inai dengan","Ada air, adalah"
    };

    String[] answers = {
            "telur","kuali","daging","dua belas","keris","kuku","ikan"
    };

    String[] maksuds = {
            "Peri keelokan, merah muka seseorang.","Terlalu murah hati sehingga binasa kerananya.","Sesuatu hal yang selalu terasa tidak menyenangkan hati.","Kehebatan langkah seseorang pendekar.","Pemberian yang tidak berfaedah.","Sangat karib (tentang orang bersahabat atau orang berkasih-kasihan)","Ada negeri tentulah ada rakyatnya."
    };

    String pb_answer, pb_question, pb_maksud;




    private static final String TAG = "MainActivity";

    // Database Helper
    DatabaseHelper db;

    Peribahasa pb1,pb2,pb3;
//    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dropLayout = (LinearLayout) findViewById(R.id.bottomLinear);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);

        btnNext = (Button) findViewById(R.id.btnNext);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer) ;
        tvMaksud = (TextView) findViewById(R.id.tvMaksud) ;
        mTextViewCountdown = findViewById(R.id.text_view_countdown);
        mTimerRunning();
        updateCountDownText();



        //tvPoints = (TextView) findViewById(R.is.tvPoints);

        db = new DatabaseHelper(this);

//        pb1 = new Peribahasa();
//        pb2 = new Peribahasa();
//        pb3 = new Peribahasa();

//        List<Integer> imagesList = new ArrayList<Integer>();
//        imagesList.add(1);

//        pb = insertQuestion();

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
                // Check if answer is coorect then reshuffles images.
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



        insertQuestion();


//        fetchPeribahasa(1);
//        fetchingAllPeribahasa();
    }

    public void insertQuestion() {
        pb1 = new Peribahasa();
        pb2 = new Peribahasa();
        pb3= new Peribahasa();

        int pbQuestion1,pbQuestion2,pbQuestion3;
//        int imgView1, imgView2, imgView3;


        r = new Random();
        List<Integer> randomQuestions = new ArrayList<Integer>(); //ArrayList<>(Arrays.asList(1,2,3,4)); creates a "list view" on an array whose size can't change.
        randomQuestions.add(1);randomQuestions.add(2);randomQuestions.add(3);randomQuestions.add(4);randomQuestions.add(5);randomQuestions.add(6);randomQuestions.add(7);randomQuestions.add(8);randomQuestions.add(9);randomQuestions.add(10);randomQuestions.add(11);randomQuestions.add(12);randomQuestions.add(13);randomQuestions.add(14);randomQuestions.add(15);


        Collections.shuffle(randomQuestions);
        pbQuestion1 = randomQuestions.get(0);
        pbQuestion2 = randomQuestions.get(1);
        pbQuestion3 = randomQuestions.get(2);

        pb1 = db.getPeribahasa(pbQuestion1);
        pb2 = db.getPeribahasa(pbQuestion2);
        pb3 = db.getPeribahasa(pbQuestion3);


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

        List<ImageView> shuffleImgView = new ArrayList<ImageView>();
        shuffleImgView.add(iv1);shuffleImgView.add(iv2);shuffleImgView.add(iv3);
//        Log.d(TAG, "insertQuestion: shuffleImgView = " + shuffleImgView.toString());
        Collections.shuffle(shuffleImgView);
//        Log.d(TAG, "insertQuestion: shuffleImgView after shuffling = " + shuffleImgView.toString());
        shuffleImgView.get(0).setImageResource(resID1);
        shuffleImgView.get(0).setContentDescription(pb1.getAnswer());
        shuffleImgView.get(1).setImageResource(resID2);
        shuffleImgView.get(1).setContentDescription(pb2.getAnswer());
        shuffleImgView.get(2).setImageResource(resID3);
        shuffleImgView.get(2).setContentDescription(pb3.getAnswer());



    }

    public void checkAnswer() {
        // If answer correct, increase point and reshuffle image
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvQuestion.getContentDescription() == tvAnswer.getText()) {
                    Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                    tvAnswer.setText("");
                    insertQuestion();
                } else {
                    Toast.makeText(MainActivity.this,"Wrong. Try Again!",Toast.LENGTH_SHORT).show();
                    tvAnswer.setText("");
                    minusTimer();
                }

            }


        });
    }

    public void fetchPeribahasa(int peribahasa_id) {
        // Getting todos under "Watchlist" tag name
        Peribahasa pb = db.getPeribahasa(peribahasa_id);

        Log.d("PERIBAHASA_ID", String.valueOf(pb.getId()));
        Log.d("PERIBAHASA_PERIBAHASA", pb.getPeribahasa());
        Log.d("PERIBAHASA_MAKSUD", pb.getMaksud());
        Log.d("PERIBAHASA_QUESTION", pb.getQuestion());
        Log.d("PERIBAHASA_ANSWER", pb.getAnswer());
        Log.d("PERIBAHASA_IMAGE", String.valueOf(pb.getImg()));

    }


    public void fetchingAllPeribahasa(){

        List<Peribahasa> peribahasaList = db.getAllPeribahasa();
        for (Peribahasa pb : peribahasaList) {
            Log.d("PERIBAHASA_ID", String.valueOf(pb.getId()));
            Log.d("PERIBAHASA_PERIBAHASA", pb.getPeribahasa());
            Log.d("PERIBAHASA_MAKSUD", pb.getMaksud());
            Log.d("PERIBAHASA_QUESTION", pb.getQuestion());
            Log.d("PERIBAHASA_ANSWER", pb.getAnswer());
            Log.d("PERIBAHASA_IMAGE", String.valueOf(pb.getImg()));
        }
        //return something
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
                Gameover();
                Intent intent = new Intent(MainActivity.this, com.example.mcflurry.bmfun.Score.class);
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
