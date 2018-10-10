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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DetailItemAdapter extends ArrayAdapter {
    /**
     * @author Gabriel Bourque
     * @description Adapteur d'objets de la classe DetailItem pour pouvoir les insérer
     * dans le ListeView.
     */

    List<DetailItem> Flux;
    DetailItem item;

    public DetailItemAdapter(Context context, int resource, List<DetailItem> objects) {
        super(context, resource, objects);
        this.Flux = objects;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_detail_item, parent, false);
        }

        //text.setText(Taches.get(position).libelle);
        //fini.setChecked(Taches.get(position).finie);
//
        return convertView;
    }
}
