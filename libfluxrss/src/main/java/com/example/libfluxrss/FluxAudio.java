package com.example.libfluxrss;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FluxAudio implements Runnable {

    /**
     * @author Gabriel Bourque
     * @description Code pour le traitement des flux audio
     */

    private String url;
    private Context context;

    /**
     * Retourne un objet de la classe MediaPlayer avec un controlleur de sous-titres null pour éviter
     * une erreur de "subtitle controller should have already been set" pour les flux audio.
     * @param context le contexte de l'activité
     * @return l'objet de classe MediaPlayer
     */
    static MediaPlayer getMediaPlayer(Context context){

        MediaPlayer mediaplayer = new MediaPlayer();

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return mediaplayer;
        }

        try {
            Class<?> cMediaTimeProvider = Class.forName( "android.media.MediaTimeProvider" );
            Class<?> cSubtitleController = Class.forName( "android.media.SubtitleController" );
            Class<?> iSubtitleControllerAnchor = Class.forName( "android.media.SubtitleController$Anchor" );
            Class<?> iSubtitleControllerListener = Class.forName( "android.media.SubtitleController$Listener" );

            Constructor constructor = cSubtitleController.getConstructor(new Class[]{Context.class, cMediaTimeProvider, iSubtitleControllerListener});

            Object subtitleInstance = constructor.newInstance(context, null, null);

            Field f = cSubtitleController.getDeclaredField("mHandler");

            f.setAccessible(true);
            try {
                f.set(subtitleInstance, new Handler());
            }
            catch (IllegalAccessException e) {return mediaplayer;}
            finally {
                f.setAccessible(false);
            }

            Method setsubtitleanchor = mediaplayer.getClass().getMethod("setSubtitleAnchor", cSubtitleController, iSubtitleControllerAnchor);

            setsubtitleanchor.invoke(mediaplayer, subtitleInstance, null);
            //Log.e("", "subtitle is setted :p");
        } catch (Exception e) {}

        return mediaplayer;
    }

    /**
     * Constructeur d'objet de la classe FluxAudio
     * @param url l'url du flux
     * @param context le contexte de l'activité
     */
    public FluxAudio(String url, Context context)
    {
        this.url = url;
        this.context = context;
    }

    /**
     * Grâce à l'implémentation de l'interface Runnable, FluxAudio.start() sera exécuté
     * sur un thread séparé pour éviter de faire trop travailler le threac principal.
     */
    @Override
    public synchronized void run() {
            MediaPlayer mp = getMediaPlayer(context);

            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

            try {
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.setDataSource(url);
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
