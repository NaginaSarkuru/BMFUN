package com.example.mcflurry.bmfun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.mcflurry.bmfun.CustomAdapter;

import java.util.List;

public class Score extends AppCompatActivity {

    ListView lstView;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lstView = (ListView)findViewById(R.id.lstRanking);
        DatabaseHelper db = new DatabaseHelper(this);
        List<Ranking> lstRanking = db.getRanking();
        if(lstRanking.size() > 0) {
            CustomAdapter adapter = new CustomAdapter(this, lstRanking);
            lstView.setAdapter(adapter);
        }
    }


}
