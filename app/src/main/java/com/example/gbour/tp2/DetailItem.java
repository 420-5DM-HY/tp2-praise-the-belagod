package com.example.gbour.tp2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.libfluxrss.TraitementRSS;

public class DetailItem extends AppCompatActivity implements TraitementRSS {

    private String titre;
    private String description;
    private String lien;
    private Bitmap image;

    public DetailItem(String URL)
    {
        this.lien = URL;
        //À compléter pour fetch le reste des infos de l'item.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
    }

    @Override
    public void Lecture() {
        //À compléter
    }
}
