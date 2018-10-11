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
    private FluxAudio audio;
    private FluxVideo video;
    private String mediaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        Bundle extras = getIntent().getExtras();
        item = (Article)extras.getBundle("Bundle").getSerializable("Article");
        titre = item.titre;
        description = item.description;
        lien = item.lien;
        mediaType = item.mediaType;
        if (mediaType == null)
            mediaType = "";
        //image = item.image;

        TextView txtNom = this.findViewById(R.id.txtTitle);
        ImageView imageView = this.findViewById(R.id.imgIm);
        TextView txtDesc = this.findViewById(R.id.txtDesc);
        Button btnPlay = this.findViewById(R.id.btnJouerContenu);

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
        txtDesc.setText(item.description);
    }
}
