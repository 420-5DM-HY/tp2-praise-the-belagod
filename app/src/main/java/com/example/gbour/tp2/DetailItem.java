package com.example.gbour.tp2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.libfluxrss.FluxAudio;
import com.example.libfluxrss.FluxVideo;
import com.example.libfluxrss.ParseFluxRss;
import com.example.libfluxrss.RssItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Details d'un flux
 */
public class DetailItem extends AppCompatActivity implements Serializable{

    public String titre;
    public String description;
    public String lien;
    public Bitmap image;
    private Article item;
    private FluxAudio audio;
    private FluxVideo video;
    private String mediaType;
    private ParseFluxRss pfrss;
    private ArrayList<RssItem> items;
    private Thread tGetinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        Bundle extras = getIntent().getExtras();
        pfrss = new ParseFluxRss();
        item = (Article)extras.getBundle("Bundle").getSerializable("Article");

        tGetinfo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    items = (ArrayList<RssItem>)pfrss.getItems(item.lien);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        tGetinfo.start();
        try {
            tGetinfo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        titre = item.titre;
        description = item.description;
        mediaType = item.mediaType;
        if (mediaType == null)
            mediaType = "";

        int index = 0;
        for (int i=0;i<items.size();i++)
        {
            if (items.get(i).titre.equals(this.titre))
            {
                index = i;
            }
        }
        image = items.get(index).image;
        lien = item.lien;

        Document doc = Jsoup.parse(item.description);
        Elements p = doc.getElementsByTag("p");

        for(Element e : p){
            if(e != null){
                description += e.text();
            }
        }

        TextView txtNom = this.findViewById(R.id.txtTitle);
        ImageView imageView = this.findViewById(R.id.imgIm);
        TextView txtDesc = this.findViewById(R.id.txtDesc);
        Button btnPlay = this.findViewById(R.id.btnJouerContenu);
        imageView.setImageBitmap(image);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaType.contains("audio"))
                {
                    audio = new FluxAudio(lien, getApplicationContext());
                    audio.run();
                }
                else if (mediaType.contains("video"))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(lien));
                    startActivity(intent);
                }
            }
        });

        txtNom.setText(item.titre);
        txtDesc.setText(description);
    }
}
