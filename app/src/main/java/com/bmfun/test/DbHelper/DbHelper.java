package com.bmfun.test.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bmfun.test.Model.Question;
import com.bmfun.test.Model.Ranking;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static String DB_NAME="BMdb.db";
    private static String DB_PATH="";
    private SQLiteDatabase mDataBase;
    private Context mContext = null;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH=context.getApplicationInfo().dataDir+"/databases/";
        File file = new File(DB_PATH+"BMdb.db");
        if(file.exists())
            openDatabase(); //db insert
        this.mContext = mContext;
    }

    public void openDatabase(){
        String myPath = DB_PATH+DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDatabase() throws IOException {
        try{
            InputStream myInput = mContext.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH+DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0)
                myOutput.write(buffer,0,length);

            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase tempDB = null;
        try{
            String myPath = DB_PATH+DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        if(tempDB != null)
            tempDB.close();
        return tempDB!=null?true:false;
    }

    public void createDatabase() throws IOException{
        boolean isDBExists = checkDataBase();
        if(isDBExists){

        }
        else {
            this.getReadableDatabase();
            try{
                copyDatabase();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void close() {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //CRUD For Table
    public List<Question> getAllQuestion() {
        List<Question> listQuestion = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM QuizList ORDER BY Random()LIMIT 10", null);
            if (c == null) return null;
            c.moveToFirst();
            do {
                int ID = c.getInt(c.getColumnIndex("ID"));
                String images = c.getString(c.getColumnIndex("images"));
                String questions = c.getString(c.getColumnIndex("questions"));
                String answers = c.getString(c.getColumnIndex("answers"));
                String correctPB = c.getString(c.getColumnIndex("correctPB"));

                Question question = new Question(ID, images, questions, answers, correctPB);
                listQuestion.add(question);
            }
            while (c.moveToNext());
            c.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return listQuestion;
    }



    //Insert Score Ranking Table
    public void insertScore(int Score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Score",Score);
        db.insert("Ranking",null,contentValues);
    }

    //Get score and order sorting
    public List<Ranking> getRanking(){
        List<Ranking>listRanking = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try{
            c=db.rawQuery("SELECT * FROM Ranking ORDER BY Score DESC;",null);
            if(c==null) return null;
            c.moveToNext();
            do{
                int Id = c.getInt(c.getColumnIndex("Id"));
                int Score = c.getInt(c.getColumnIndex("Score"));

                Ranking ranking = new Ranking(Id,Score);
                listRanking.add(ranking);
            }
            while(c.moveToNext());
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listRanking;
    }

}
