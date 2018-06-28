package com.example.mcflurry.bmfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mcflurry.bmfun.Model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG  = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bmfun";

    private static final String TABLE_USERS = "users";
    private static final String COL_ID = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASS = "password";

    private static final String CREATE_USERS = "CREATE TABLE users ( id INT NOT NULL, username VARCHAR(30) NOT NULL , password VARCHAR(30) NOT NULL , PRIMARY KEY (`id`))";

    private static final String TABLE_PERIBAHASA = "peribahasa";
    private static final String PERIBAHASA_ID = "id";
    private static final String PERIBAHASA_PERIBAHASA = "peribahasa";
    private static final String PERIBAHASA_MAKSUD = "maksud";
    private static final String PERIBAHASA_QUESTION = "question";
    private static final String PERIBAHASA_ANSWER = "answer";
    private static final String PERIBAHASA_IMAGE = "image";

    private static final String CREATE_PERIBAHASA = "CREATE TABLE peribahasa ( id INT NOT NULL, peribahasa VARCHAR(120) NOT NULL, maksud VARCHAR(120) NOT NULL, question VARCHAR(120) NOT NULL, answer VARCHAR(120) NOT NULL, image VARCHAR(30) NOT NULL, PRIMARY KEY (`id`))";

    SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USERS);
        sqLiteDatabase.execSQL(CREATE_PERIBAHASA);

        Log.d(TAG, "onCreate: DATABASE HAS BEEN CREATED");
//        this.db = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PERIBAHASA);

        Log.d(TAG, "onUpgrade: onUpgrade DONE");

        onCreate(sqLiteDatabase);
    }

    public Peribahasa getPeribahasa(int peribahasa_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PERIBAHASA + " WHERE "
                + PERIBAHASA_ID + " = " + peribahasa_id;



        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Peribahasa pb = new Peribahasa();
        pb.setId(c.getInt((c.getColumnIndex(PERIBAHASA_ID))));
        pb.setMaksud(c.getString(c.getColumnIndex(PERIBAHASA_MAKSUD)));
        pb.setPeribahasa((c.getString(c.getColumnIndex(PERIBAHASA_PERIBAHASA))));
        pb.setQuestion(c.getString(c.getColumnIndex(PERIBAHASA_QUESTION)));
        pb.setAnswer(c.getString(c.getColumnIndex(PERIBAHASA_ANSWER)));
        pb.setImg(c.getString(c.getColumnIndex(PERIBAHASA_IMAGE)));

        Log.d(TAG, "getPeribahasa: "+ selectQuery);
        return pb;
    }



    public List<Peribahasa> getAllPeribahasa() {
        List<Peribahasa> peribahasaList = new ArrayList<Peribahasa>();
        String selectQuery = "SELECT  * FROM " + TABLE_PERIBAHASA;

//        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Peribahasa pb = new Peribahasa();
                pb.setId(c.getInt((c.getColumnIndex(PERIBAHASA_ID))));
                pb.setMaksud(c.getString(c.getColumnIndex(PERIBAHASA_MAKSUD)));
                pb.setPeribahasa((c.getString(c.getColumnIndex(PERIBAHASA_PERIBAHASA))));
                pb.setQuestion(c.getString(c.getColumnIndex(PERIBAHASA_QUESTION)));
                pb.setAnswer(c.getString(c.getColumnIndex(PERIBAHASA_ANSWER)));
                pb.setImg(c.getString(c.getColumnIndex(PERIBAHASA_IMAGE)));

                // adding to todo list
                peribahasaList.add(pb);
            } while (c.moveToNext());
        }

        return peribahasaList;
    }

    //Get score and order sorting
    public List<Ranking> getRanking(){
        List<Ranking>listRanking = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try{
            c=db.rawQuery("SELECT TOP 10 * FROM Ranking ORDER BY Score DESC;",null);
            //if(c==null) return null;
            c.moveToNext();
            do{
                int Id = c.getInt(c.getColumnIndex("ID"));
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

    public void insertScore(int Score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Score",Score);
        db.insert("Ranking",null,contentValues);
    }




//    public boolean insertData(User user) {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        String query = "select * from "+ TABLE_USERS;
//        Cursor cursor = db.rawQuery(query,null);
//        int count = cursor.getCount();
//
//        values.put(COL_ID,count);
//        values.put(COL_USERNAME, user.getUserName());
//        values.put(COL_PASS, user.getPassword());
//
//        long result = db.insert(TABLE_USERS, null, values);
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//
//    }

//    public String searchPass(String uname) {
//        db = this.getReadableDatabase();
//        String query = "select username, pass from " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(query,null);
//        String a,b;
//        b= "Not found";
//        if (cursor.moveToFirst()) {
//            a = cursor.getString(0);
//            b = cursor.getString(1);
//
//            while (cursor.moveToNext());
//
//        }
//        return b;
//    }

//    public boolean checkUser(String username, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String[] column = {COL_ID};
//        String selection = COL_USERNAME + " = ? " + COL_PASS + " = ?";
//        String[] selectionArgs = {username, password};
//
//        //("SELECT username FROM users WHERE username='?' AND password = '?'", new String[] {username,password})
//        Cursor c = db.query(TABLE_USERS, column,selection, selectionArgs, null, null,null);
//
//        int cursorCount = c.getCount();
//        c.close();
//        db.close();
//
//        if (cursorCount > 0) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }









    //CRUD Peribahasa table
//    public List<Peribahasa> getAllQuestion() {
//        List<Peribahasa> listQuestion = new ArrayList<>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor c;
//        try {
//            c = db.rawQuery("SELECT * FROM TABLE_PERIBAHASA ORDER BY Random()LIMIT 10", null);
//            if (c == null) return null;
//            c.moveToFirst();
//            do {
//
//                int ID = c.getInt(c.getColumnIndex(PERIBAHASA_ID));
//                String maksud = c.getString(c.getColumnIndex(PERIBAHASA_MAKSUD));
//                String peribahasa = c.getString(c.getColumnIndex(PERIBAHASA_PERIBAHASA));
//                String image = c.getString(c.getColumnIndex(PERIBAHASA_IMAGE));
//                String answer = c.getString(c.getColumnIndex(PERIBAHASA_ANSWER));
//                String question = c.getString(c.getColumnIndex(PERIBAHASA_QUESTION));
//
//                Peribahasa pb = new Peribahasa(ID, peribahasa, maksud, answer, question, image);
//                listQuestion.add(pb);
//            }
//            while (c.moveToNext());
//            c.close();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        db.close();
//        return listQuestion;
//    }
}


