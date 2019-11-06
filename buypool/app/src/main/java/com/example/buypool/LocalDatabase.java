package com.example.buypool;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalDatabase extends SQLiteOpenHelper {
    public LocalDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userslocal(id integer primary key autoincrement,email varchar(40), password varchar(30),is_remember int default 0,is_active int default 0);");
        db.execSQL("create table usersremote(id integer primary key autoincrement, username varchar(20),email varchar(40), password varchar(30),phone_number varchar(20));");

        //        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", "renjie.fu@ucdconnect.ie");
//        contentValues.put("password", "123456");
//        contentValues.put("is_remember", 1);
//        contentValues.put("is_active", 1)   ;
//
//        db.insert("users", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
