package com.example.gbour.tp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.libfluxrss.RssItem;

import java.util.ArrayList;
import java.util.List;

public class DetailItemAdapter extends ArrayAdapter {
    /**
     * @author Gabriel Bourque
     * @description Adapteur d'objets de la classe DetailItem pour pouvoir les insérer
     * dans le ListeView.
     */

    List<RssItem> items;
    Article item;

    public DetailItemAdapter(Context context, int resource, List<RssItem> objects) {
        super(context, resource, objects);
        this.items = objects;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_article, parent, false);
        }

        TextView nomArticle = convertView.findViewById(R.id.txtNomArticle);
        nomArticle.setText(items.get(position).titre);
        //item.image = items.get(position).image;

        nomArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailItem.class);
                Bundle b = new Bundle();
                item = new Article();
                item.titre = items.get(position).titre;
                item.description = items.get(position).description;
                item.lien = items.get(position).lien;
                b.putSerializable("Article", item);
                intent.putExtra("Bundle", b);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
