package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "logreg.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "felhasznalo";
    private static final String COL_ID = "ID";
    private static final String COL_EMAIL = "email";
    private static final String COL_FELHNEV = "felhasznalonev";
    private static final String COL_JELSZO = "jelszo";
    private static final String COL_TELJESNEV = "teljesnev";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " TEXT NOT NULL, " +
                COL_FELHNEV + " TEXT NOT NULL, " +
                COL_JELSZO + " TEXT NOT NULL, " +
                COL_TELJESNEV + " TEXT NOT NULL " + ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean regisztracio(String email, String felhnev, String jelszo, String teljesnev){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHNEV, felhnev);
        values.put(COL_JELSZO, jelszo);
        values.put(COL_TELJESNEV, teljesnev);
        long result =db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean bejelentkezes(String felhnev, String jelszo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.query(TABLE_NAME, new String[]{COL_ID, COL_EMAIL, COL_FELHNEV, COL_JELSZO},
                COL_FELHNEV + " =? AND " + COL_JELSZO + " =? ", new String[]{felhnev, jelszo}, null,null,null);
        return result.getCount() > 0;
    }

    public Cursor teljesnev(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[] {COL_ID, COL_EMAIL, COL_FELHNEV, COL_JELSZO}, null,null,null,null,null);
    }
}
