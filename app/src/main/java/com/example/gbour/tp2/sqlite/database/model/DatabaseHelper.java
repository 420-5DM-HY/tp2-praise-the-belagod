package com.example.gbour.tp2.sqlite.database.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gbour.tp2.DetailFlux;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolas Gonzalez
 * @decription Permet de gérer le CRUD de la bd
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "urls_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create fluxs table
        db.execSQL(Flux.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Flux.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertUrl(String url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Flux.COLUMN_URL, url);

        long id = db.insert(Flux.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public Flux getUrl(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Flux.TABLE_NAME,
                new String[]{Flux.COLUMN_ID, Flux.COLUMN_URL, Flux.COLUMN_TIMESTAMP},
                Flux.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare flux object
        Flux flux = new Flux(
                cursor.getInt(cursor.getColumnIndex(Flux.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Flux.COLUMN_URL)),
                cursor.getString(cursor.getColumnIndex(Flux.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return flux;
    }

    public List<Flux> getAllFluxs() {
        List<Flux> fluxs = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Flux.TABLE_NAME + " ORDER BY " +
                Flux.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Flux flux = new Flux();
                flux.setId(cursor.getInt(cursor.getColumnIndex(Flux.COLUMN_ID)));
                flux.setUrl(cursor.getString(cursor.getColumnIndex(Flux.COLUMN_URL)));
                flux.setTimestamp(cursor.getString(cursor.getColumnIndex(Flux.COLUMN_TIMESTAMP)));

                 fluxs.add(flux);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return fluxs list
        return fluxs;
    }

    public int getFluxsCount() {
        String countQuery = "SELECT  * FROM " + Flux.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateFlux(Flux flux) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Flux.COLUMN_URL, flux.getUrl());

        // updating row
        return db.update(Flux.TABLE_NAME, values, Flux.COLUMN_ID + " = ?",
                new String[]{String.valueOf(flux.getId())});
    }

    public void deleteFlux(Flux flux) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Flux.TABLE_NAME, Flux.COLUMN_ID + " = ?",
                new String[]{String.valueOf(flux.getId())});
        db.close();
    }
}
