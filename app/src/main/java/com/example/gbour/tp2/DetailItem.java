package com.example.gbour.tp2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.libfluxrss.TraitementRSS;

public class DetailItem extends AppCompatActivity{

    public String titre;
    public String description;
    public String lien;
    public Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
    }
}
