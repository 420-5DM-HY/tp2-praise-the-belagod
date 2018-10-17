package com.example.gbour.tp2;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.libfluxrss.ParseFluxRss;
import com.example.libfluxrss.RssItem;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @Author Gabriel Bourque
 * Classe contenant les détails d'un flux
 */
public class DetailFlux implements Serializable{
    public String titre;
    public String description;
    public String lien;
    public Bitmap image;
    public int nbArticlesNonLus;

    /**
     * Constructeur permettant d'enter les détails d'un flux
     * @param URL site à accéder
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public DetailFlux(String URL) throws ParserConfigurationException, IOException, SAXException {
        this.lien = URL;

        ParseFluxRss pfrss = new ParseFluxRss();

        ArrayList<RssItem> items = (ArrayList<RssItem>)pfrss.getItems(URL);
        //Obtention des données de présentation(détails) du flux
        titre = items.get(0).titre;
        description = items.get(0).description;
        image = items.get(0).image;
    }

    /**
     * Méthode permettant d'obtenir la liste des articles contenus dans un flux
     * @return les articles d'un flux
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public ArrayList<RssItem> GetArticles() throws IOException, SAXException, ParserConfigurationException {
        ParseFluxRss pfrss = new ParseFluxRss();
        ArrayList<RssItem> items = (ArrayList<RssItem>)pfrss.getItems(lien);
        for (RssItem item : items)
        {
            item.lien = this.lien;
        }
        return items;
    }
}
