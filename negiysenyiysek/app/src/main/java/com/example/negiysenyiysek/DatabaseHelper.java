package com.example.negiysenyiysek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "negiysenyiysek.db";
    private static final int DATABASE_VERSION = 1;

    // Tablo isimleri
    private static final String TABLE_FOOD = "food";
    private static final String TABLE_MOVIES = "movies";
    private static final String TABLE_CLOTHING = "clothing";

    // Kolon isimleri
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // "food" tablosunu oluştur
        String CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT)";
        db.execSQL(CREATE_FOOD_TABLE);

        // "movies" tablosunu oluştur
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT)";
        db.execSQL(CREATE_MOVIES_TABLE);

        // "clothing" tablosunu oluştur
        String CREATE_CLOTHING_TABLE = "CREATE TABLE " + TABLE_CLOTHING + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT)";
        db.execSQL(CREATE_CLOTHING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLOTHING);
        onCreate(db);
    }

    // Yemek ekleme
    public void addFood(String foodName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, foodName);
        db.insert(TABLE_FOOD, null, values);
        db.close();
    }

    // Yemek listesini alma
    public List<String> getFood() {
        List<String> foodList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FOOD, new String[]{COLUMN_NAME}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                foodList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodList;
    }

    // Tüm yemekleri silme
    public void deleteAllFood() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD, null, null);
        db.close();
    }

    // Film ekleme
    public void addMovie(String movieName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, movieName);
        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

    // Film listesini alma
    public List<String> getMovies() {
        List<String> moviesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOVIES, new String[]{COLUMN_NAME}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                moviesList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return moviesList;
    }

    // Tüm filmleri silme
    public void deleteAllMovies() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, null, null);
        db.close();
    }

    // Kıyafet ekleme
    public void addClothing(String clothingName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, clothingName);
        db.insert(TABLE_CLOTHING, null, values);
        db.close();
    }

    // Kıyafet listesini alma
    public List<String> getClothing() {
        List<String> clothingList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CLOTHING, new String[]{COLUMN_NAME}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                clothingList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return clothingList;
    }

    // Tüm kıyafetleri silme
    public void deleteAllClothing() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLOTHING, null, null);
        db.close();
    }
}
