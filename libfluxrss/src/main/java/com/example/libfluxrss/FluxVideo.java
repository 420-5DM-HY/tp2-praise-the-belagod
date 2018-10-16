package com.example.libfluxrss;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * @Author Gabriel Bourque
 * Classe contenant les informations des flux vidéos
 */
public class FluxVideo {

    MediaController mediaController;
    Context context;
    String url;

    /**
     * @Author Gabriel Bourque
     * Constructeur permettant d'entrer les informations de base des flux vidéos
     * @param URL
     * @param context
     */
    public FluxVideo(String URL, Context context)
    {
        this.url = URL;
        this.context = context;
        this.mediaController = new MediaController(context);
    }

    /**
     * @Author Gabriel Bourque
     * Méthode permettant de charger la vidéo
     */
    public void LoadVideo()
    {

    }
}
