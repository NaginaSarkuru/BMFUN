package com.bmfun.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bmfun.test.Common.CustomAdapter;
import com.bmfun.test.DbHelper.DbHelper;
import com.bmfun.test.Model.Ranking;

import java.util.List;

public class GAME3SCORE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3_score);

        ListView lstView = (ListView) findViewById(R.id.lstRanking);
        DbHelper db = new DbHelper(this);
        List<Ranking> lstRanking = db.getRanking();
        if(lstRanking.size() > 0) {
            CustomAdapter adapter = new CustomAdapter(this, lstRanking);
            lstView.setAdapter(adapter);
        }


    }
}
