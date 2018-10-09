package com.example.gbour.tp2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailItem extends AppCompatActivity {

    private String titre;
    private String description;
    private String lien;
    private Bitmap image;
    private int nbArticlesNonLus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

    }
}
