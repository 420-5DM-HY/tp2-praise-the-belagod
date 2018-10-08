package com.example.libfluxrss;

import android.graphics.Bitmap;

/**
 * @Author Julien Pineault
 * Modèle allant contenir les items du flux RSS
 */
public class RssItem {
    public String titre;
    public String description;
    public String lien;
    public Bitmap image;
    public RssItem(String titre, String desc, String lien, Bitmap image){
        this.titre = titre;
        this.description = desc;
        this.lien = lien;
        this.image = image;
    }
}
