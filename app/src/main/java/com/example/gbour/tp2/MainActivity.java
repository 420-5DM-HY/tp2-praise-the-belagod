package com.example.gbour.tp2;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.libfluxrss.FluxAudio;
import com.example.libfluxrss.ParseFluxRss;
import com.example.libfluxrss.RssItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    TextView txtResult;
    String data = "";
    FluxAudio audio;
    VideoView videoView;
    MediaController mediaController;
    List<RssItem> lstItems;
    //.setTag() et .getTag() .invalidateViews() à garder en tête J.P.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txtResultat);

        // Test videos
        //((TextView) findViewById(R.id.txtUrl)).setText("http://feeds.twit.tv/sn_video_hd.xml");
        //Test de Flux
        ((TextView) findViewById(R.id.txtUrl)).setText("https://www.developpez.com/index/rss");

        findViewById(R.id.btnGetDatas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFlux();
            }
        });

        Button btnAudio = this.findViewById(R.id.btnAudio);
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio = new FluxAudio("http://www.podtrac.com/pts/redirect.mp3/cdn.twit.tv/audio/sn/sn0679/sn0679.mp3", MainActivity.this);
                audio.run();
                //videoView.stopPlayback();
            }
        });


        Button btnVideo = this.findViewById(R.id.btnPlay);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LoadVideo("http://www.podtrac.com/pts/redirect.mp4/cdn.twit.tv/video/sn/sn0681/sn0681_h264m_1280x720_1872.mp4");
                        videoView.start();
                    }
                });
            }
        });
    }

    private void LoadVideo(final String url)
    {
        mediaController = new MediaController(MainActivity.this);
        mediaController.setAnchorView(videoView);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                videoView = (VideoView)findViewById(R.id.vidVideo);
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(Uri.parse(url));
                videoView.setZOrderOnTop(true);
            }
        });
        videoView.requestFocus();
    }

    private void getFlux(){
        lstItems = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseFluxRss pFR = new ParseFluxRss();
                try {
                    lstItems = pFR.getItems(((TextView) findViewById(R.id.txtUrl)).getText().toString());
                    data = lstItems.get(0).description;
                } catch (Exception e) {
                    data = e.getMessage();
                }

                try {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            txtResult.setText(data);
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
