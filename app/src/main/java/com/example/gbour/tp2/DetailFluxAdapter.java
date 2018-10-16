package com.example.gbour.tp2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.libfluxrss.RssItem;

import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class DetailFluxAdapter extends ArrayAdapter {

    /**
     * @author Gabriel Bourque
     * @description Adapteur d'objets de la classe DetailFlux pour pouvoir les insérer
     * dans le ListeView.
     */

    List<DetailFlux> Flux;
    ArrayList<RssItem> items;
    ArrayList<byte[]> images;
    DetailFlux item;
    Thread tGetArticles;
    ByteArrayOutputStream stream = new ByteArrayOutputStream();

    public DetailFluxAdapter(Context context, int resource, List<DetailFlux> objects) {
        super(context, resource, objects);
        this.Flux = objects;
    }

    /**
     * Permet d'obtenir le layout du détail d'un flux
     * @param position position dans la liste
     * @param convertView
     * @param parent
     * @return retourne la vue à afficher
     */
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_detail_item, parent, false);
        }

        TextView txtNom = convertView.findViewById(R.id.txtNomFlux);
        ImageView imgImage = convertView.findViewById(R.id.imgFlux);
        TextView txtNonLus = convertView.findViewById(R.id.txtNbArticlesNonLus);
        Button btnSupprimer = convertView.findViewById(R.id.btnSupprimer);

        item = Flux.get(position);

        txtNom.setText(Flux.get(position).titre);
        //txtNonLus.setText(Flux.get(position).nbArticlesNonLus);
        imgImage.setImageBitmap(item.image);

        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flux.remove(item);
            }
        });

        tGetArticles = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    items = (ArrayList<RssItem>) item.GetArticles();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }
        });

        imgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tGetArticles.start();
                try {
                    tGetArticles.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 * Passer à l'activité qui listera les items la liste des items liés à un flux.
                 */
                Intent intent = new Intent(getContext(), ListeItemsActivity.class);
                Bundle b = new Bundle();
                for (RssItem i : items)
                {
                    i.image = null;
                }
                b.putSerializable("Articles", items);
                intent.putExtra("Bundle", b);
                getContext().startActivity(intent);
            }
        });

        txtNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tGetArticles.start();
                try {
                    tGetArticles.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 * Passer à l'activité qui listera les items la liste des items liés à un flux.
                 */
                Intent intent = new Intent(getContext(), ListeItemsActivity.class);
                Bundle b = new Bundle();
                for (RssItem i : items)
                {
                    i.image = null;
                }
                b.putSerializable("Articles", items);
                intent.putExtras(b);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
