package com.example.gbour.tp2;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.libfluxrss.ParseFluxRss;
import com.example.libfluxrss.RssItem;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class DetailFlux {
    private String titre;
    private String description;
    private String lien;
    private Bitmap image;
    private int nbArticlesNonLus;

    public DetailFlux(String URL)
    {
        this.lien = URL;
        //À compléter pour fetch le reste des infos de l'item.
    }

    public List<RssItem> GetArticles() throws IOException, SAXException, ParserConfigurationException {
        ParseFluxRss pfrss = new ParseFluxRss();
        List<RssItem> items = pfrss.getItems(lien);
        return items;
    }
}
