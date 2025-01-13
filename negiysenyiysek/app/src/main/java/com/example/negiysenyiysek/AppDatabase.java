package com.example.negiysenyiysek;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appdata.db";
    private static final int DATABASE_VERSION = 1;

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tablo oluşturma sorguları
        db.execSQL("CREATE TABLE clothing (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)");
        db.execSQL("CREATE TABLE food (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)");
        db.execSQL("CREATE TABLE movies (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS clothing");
        db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(db);
    }
}
