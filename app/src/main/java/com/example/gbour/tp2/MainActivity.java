package com.example.gbour.tp2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.libfluxrss.ParseFluxRss;
import com.example.libfluxrss.RssItem;

import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    TextView txtResult;
    String data;
    //.setTag() et .getTag() .invalidateViews() à garder en tête J.P.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult =  findViewById(R.id.txtResultat);

        ((TextView)findViewById(R.id.txtUrl)).setText("http://feeds.twit.tv/sn_video_hd.xml");
        findViewById(R.id.btnGetDatas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFlux();
            }
        });
        // Code à Gab pour l'audio
        /*
        String url = "http://www.podtrac.com/pts/redirect.mp3/cdn.twit.tv/audio/sn/sn0679/sn0679.mp3";
        MediaPlayer mp = new MediaPlayer();
        try {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(url);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }
    private void getFlux(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseFluxRss pFR = new ParseFluxRss();
                List<RssItem> lstItems = new ArrayList<>();
                try {
                    lstItems = pFR.getItems(((TextView)findViewById(R.id.txtUrl)).getText().toString());
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
                }
             catch (Exception e)
            {
                e.printStackTrace();
            }

            }
        });

    }
}
