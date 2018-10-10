package com.example.gbour.tp2;

import android.content.Context;
import android.content.Intent;
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

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class DetailFluxAdapter extends ArrayAdapter {

    /**
     * @author Gabriel Bourque
     * @description Adapteur d'objets de la classe DetailFlux pour pouvoir les insérer
     * dans le ListeView.
     */

    List<DetailFlux> Flux;
    DetailFlux item;

    public DetailFluxAdapter(Context context, int resource, List<DetailFlux> objects) {
        super(context, resource, objects);
        this.Flux = objects;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_detail_item, parent, false);
        }

        TextView txtNom = convertView.findViewById(R.id.txtNomFlux);
        ImageView imgImage = convertView.findViewById(R.id.imgFlux);
        TextView txtNonLus = convertView.findViewById(R.id.txtNbArticlesNonLus);
        Button btnSupprimer = convertView.findViewById(R.id.btnSupprimer);

        item = (DetailFlux)Flux.get(position);
        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flux.remove(item);
            }
        });

        imgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<RssItem> items = item.GetArticles();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(DetailFluxAdapter.this, ListeItemsActivity.class);

            }
        });

        txtNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<RssItem> items = item.GetArticles();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }
        });

        //text.setText(Taches.get(position).libelle);
        //fini.setChecked(Taches.get(position).finie);
//
        return convertView;
    }
}
