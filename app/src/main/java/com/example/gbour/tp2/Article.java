package com.example.gbour.tp2;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * @Author Gabriel Bourque
 * Article Contenu dans un flux
 */
public class Article implements Serializable{
    public String titre;
    public String description;
    public String lien;
    public Bitmap image;
    public String mediaType;
}
