package com.example.gbour.tp2;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.libfluxrss.ParseFluxRss;
import com.example.libfluxrss.RssItem;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DetailFlux {
    public String titre;
    public String description;
    public String lien;
    public Bitmap image;
    public int nbArticlesNonLus;

    public DetailFlux(String URL) throws ParserConfigurationException, IOException, SAXException {
        this.lien = URL;

        ParseFluxRss pfrss = new ParseFluxRss();

        ArrayList<RssItem> items = (ArrayList<RssItem>)pfrss.getItems(URL);
        titre = items.get(0).titre;
        description = items.get(0).description;
        image = items.get(0).image;
    }

    public ArrayList<RssItem> GetArticles() throws IOException, SAXException, ParserConfigurationException {
        ParseFluxRss pfrss = new ParseFluxRss();
        ArrayList<RssItem> items = (ArrayList<RssItem>)pfrss.getItems(lien);
        return items;
    }
}
