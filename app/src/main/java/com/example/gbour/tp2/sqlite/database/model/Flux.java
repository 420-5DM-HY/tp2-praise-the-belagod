package com.example.gbour.tp2.sqlite.database.model;

import com.example.gbour.tp2.DetailFlux;

import java.util.ArrayList;

/**
 * @author Nicolas Gonzalez
 * @decription Permet d'obtenir les urls de la bd
 */
public class Flux {
    public static final String TABLE_NAME = "fluxs";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private  int id;
    private String url;
    private String timestamp;

    // Crée la table SQL
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_URL + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")";

    public Flux(){
    }

    public Flux(int id, String url, String timestamp){
        this.id = id;
        this.url = url;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
