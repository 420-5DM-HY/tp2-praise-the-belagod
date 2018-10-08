package com.example.libfluxrss;

import android.graphics.Bitmap;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ParseFluxRss {
    /**
     * @Author Julien Pineault
     * @param url sous forme de string de FluxRSS à désérialiser
     * @return la liste des items
     * Méthode permettant d'obtenir une liste d'items affichable depuis un flux
     */
    public List<RssItem> getItems(String url)throws ParserConfigurationException,SAXException,IOException{
        // À compléter
        List<RssItem> items = new ArrayList<>();
        Document dom;
        DocumentBuilder builder;

            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            dom = builder.parse(url);
            items = parseFlux(dom);
        return items;
    }

    public List<RssItem> parseFlux(Document doc){
        String titre;
        String lien;
        String desc;
        Bitmap image;
        boolean isItem = false;
        List<RssItem> items = new ArrayList<>();
        int nbElements = doc.getElementsByTagName("title").getLength();
        items.add(new RssItem(doc.getElementsByTagName("title").item(0).getTextContent(),
                doc.getElementsByTagName("description").item(0).getTextContent(),
                doc.getElementsByTagName("link").item(0).getTextContent(),
                null));


        return items;
    }
}
