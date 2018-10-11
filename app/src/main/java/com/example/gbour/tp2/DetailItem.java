package com.example.gbour.tp2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.libfluxrss.RssItem;
import com.example.libfluxrss.TraitementRSS;

import java.io.Serializable;
import java.util.ArrayList;

public class DetailItem extends AppCompatActivity implements Serializable{

    public String titre;
    public String description;
    public String lien;
    public Bitmap image;
    private Article item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        Bundle extras = getIntent().getExtras();
        item = (Article)extras.getBundle("Bundle").getSerializable("Article");

        TextView txtNom = this.findViewById(R.id.txtTitle);
        ImageView imageView = this.findViewById(R.id.imgIm);
        TextView txtDesc = this.findViewById(R.id.txtDesc);

        txtNom.setText(item.titre);
        txtDesc.setText(item.description);
    }
}
