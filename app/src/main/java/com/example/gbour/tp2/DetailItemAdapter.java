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

import java.util.ArrayList;
import java.util.List;

public class DetailItemAdapter extends ArrayAdapter {
    /**
     * @author Gabriel Bourque
     * @description Adapteur d'objets de la classe DetailItem pour pouvoir les insérer
     * dans le ListeView.
     */

    List<DetailItem> items;
    DetailItem item;

    public DetailItemAdapter(Context context, int resource, List<DetailItem> objects) {
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

        nomArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
}
