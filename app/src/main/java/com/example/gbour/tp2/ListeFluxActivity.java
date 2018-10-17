package com.example.gbour.tp2;

import android.content.Context;
import android.os.DeadObjectException;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gbour.tp2.sqlite.database.model.DatabaseHelper;
import com.example.gbour.tp2.sqlite.database.model.Flux;

import org.jsoup.Jsoup;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.BufferOverflowException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class ListeFluxActivity extends AppCompatActivity {

    /**
     * @author Gabriel Bourque et Nicolas Gonzalez
     * @decription Activité présentant la liste de flux à l'usager et permettant d'en
     * rajouter et d'en retirer.
     * @param savedInstanceState
     */

    ArrayList<DetailFlux> mesFlux;
    ArrayAdapter<DetailFlux> aa;
    ArrayList<String> listUrls;
    EditText et;
    DetailFlux df;
    Thread tAdd;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_flux);

        mesFlux = new ArrayList<DetailFlux>();
        et = findViewById(R.id.txtURL);

        lv = findViewById(R.id.lstListeFlux);


        RefreshList();

        tAdd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                      df = new DetailFlux(et.getText().toString());

                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton btnAjouter = findViewById(R.id.btnAjouter);
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tAdd.start();
                try {
                        tAdd.join();
                        mesFlux.add(df);
                        SerializeFluxs();
                        RefreshList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * @author Gabriel Bourque et Nicolas Gonzalez
     * @description Permet de réinitialiser la liste de flux pour appliquer les modifications dynamiquement
     * au niveau de l'affichage
     */
    public void RefreshList()
    {
        Thread Load = new Thread(new Runnable() {
            @Override
            public void run() {
                DeserializeFluxs();

                if (listUrls != null)
                {
                    if (!listUrls.isEmpty()){
                        for (String lien:listUrls) {
                            try {
                                DetailFlux detailFlux = new DetailFlux(lien);
                                mesFlux.add(detailFlux);
                            } catch (ParserConfigurationException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (SAXException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Load.start();

        //Association de la liste au ListView
        ListeFluxActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                aa = new DetailFluxAdapter(ListeFluxActivity.this, 0, mesFlux);
                lv.setAdapter(aa);
            }
        });
    }

    /**
     * @author Nicolas Gonzalez
     * @decription Sauvegarde les fluxs
     */
    private void SerializeFluxs(){
        try {
            // Ouvre le fichier à écrire
            FileOutputStream fos = getApplicationContext().openFileOutput("SavedFluxs", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Écris chaque lien à sauvegarder
            for (DetailFlux df: mesFlux) {
                oos.writeObject(df.lien);
            }
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @author Nicolas Gonzalez
     * @decription Charge les fluxs
     */
    private void DeserializeFluxs(){
        try {

            // Ouvre le fichier à lire
            FileInputStream fis = getApplicationContext().openFileInput("SavedFluxs");
            ObjectInputStream ois = new ObjectInputStream(fis);

            // S'occupe de charger les urls sauvegardés
            String url = (String) ois.readObject();
            listUrls = new ArrayList<String>();
            listUrls.add(url);

            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
