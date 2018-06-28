//package com.example.mcflurry.bmfun;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class ImageHelper extends SQLiteOpenHelper {
//
//    private static final String TAG  = "ImageHelper";
//    private static final int DATABASE_VERSION = 1;
//    private static final String DATABASE_NAME = "bmfun";
//
//    private static final String TABLE_NAME = "images";
//    private static final String COL_ID = "id";
//    private static final String COL_IMAGE = "image";
////    private static final String COL_PASS = "question";
//    private static final String COL_DESC = "description";
//
//    private static final String TABLE_CREATE = "CREATE TABLE images ( id INT NOT NULL, image VARCHAR(30) NOT NULL , description VARCHAR(30) NOT NULL , PRIMARY KEY (`id`))";
//
//
//    public ImageHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//}
