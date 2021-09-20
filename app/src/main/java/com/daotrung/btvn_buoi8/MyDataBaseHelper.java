package com.daotrung.btvn_buoi8;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private  Context context ;
    public static final String DATABASE_NAME = "StudentManager.db" ;
    public static final int DATABASE_VERSION = 1 ;

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(QLySV.CREATE_SV_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("DROP TABLE IF EXISTS "+ QLySV.TABLE_NAME);
         onCreate(db);
    }
    // insert
    public Long createSV(QLySV qLySV){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(QLySV.COLUMN_NAME,qLySV.getName());
        contentValues.put(QLySV.COLUMN_ADDRESS,qLySV.getAddress());
        contentValues.put(QLySV.COLUMN_PHONE,qLySV.getPhone());

        long id = db.insert(QLySV.TABLE_NAME,null,contentValues);

        db.close();

        return id ;
    }

    public QLySV getSVByID(long id) {
        // get readable database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(QLySV.TABLE_NAME,
                new String[]{
                        QLySV.COLUMN_ID,
                        QLySV.COLUMN_NAME,
                        QLySV.COLUMN_ADDRESS,
                        QLySV.COLUMN_PHONE},
                QLySV.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare event object
        @SuppressLint("Range") QLySV qLySV = new QLySV(
                Long.parseLong(cursor.getString(cursor.getColumnIndex(QLySV.COLUMN_ID))),
                cursor.getString(cursor.getColumnIndex(QLySV.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(QLySV.COLUMN_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(QLySV.COLUMN_PHONE)));
        // close the db connection
        cursor.close();

        return qLySV;
    }


    @SuppressLint("Range")
    public ArrayList<QLySV> getAllSV() {
        ArrayList<QLySV> noteArrayList = new ArrayList<>();

        // Select All Events Query
        String selectQuery = "SELECT  * FROM "
                + QLySV.TABLE_NAME ;

        //Instance of database
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        //looping all rows
        if (cursor.moveToFirst()) {
            do {
                QLySV qLySV = new QLySV();
                qLySV.setId(Long.parseLong(cursor.getString(cursor.getColumnIndex(QLySV.COLUMN_ID)))); // getting the id
                qLySV.setName(cursor.getString(cursor.getColumnIndex(QLySV.COLUMN_NAME)));
                qLySV.setAddress(cursor.getString(cursor.getColumnIndex(QLySV.COLUMN_ADDRESS)));
                qLySV.setPhone(cursor.getString(cursor.getColumnIndex(QLySV.COLUMN_PHONE)));
                noteArrayList.add(qLySV); // add to the arrayList
                qLySV.toString();
            } while (cursor.moveToNext());
        }

        db.close();

        return noteArrayList;
    }


    public int updateEvent(QLySV qLySV) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QLySV.COLUMN_NAME, qLySV.getName());
        values.put(QLySV.COLUMN_ADDRESS, qLySV.getAddress());
        values.put(QLySV.COLUMN_PHONE, qLySV.getPhone());
        // updating event row
        return db.update(QLySV.TABLE_NAME, values, QLySV.COLUMN_ID + " = ?",
                new String[]{String.valueOf(qLySV.getId())});
    }


    public void deleteEvent(QLySV qLySV) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(QLySV.TABLE_NAME, QLySV.COLUMN_ID + " = ?",
                new String[]{String.valueOf(qLySV.getId())});
        db.close();
    }
}
