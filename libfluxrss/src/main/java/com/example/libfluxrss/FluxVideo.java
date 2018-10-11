package com.example.libfluxrss;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.MediaController;
import android.widget.VideoView;

public class FluxVideo {

    MediaController mediaController;
    Context context;
    String url;

    public FluxVideo(String URL, Context context)
    {
        this.url = URL;
        this.context = context;
        this.mediaController = new MediaController(context);
    }

    public void LoadVideo()
    {

    }
}
