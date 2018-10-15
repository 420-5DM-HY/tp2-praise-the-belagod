package com.example.gbour.tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.gbour.tp2.sqlite.database.model.DatabaseHelper;
import com.example.gbour.tp2.sqlite.database.model.Flux;

import org.jsoup.Jsoup;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class ListeFluxActivity extends AppCompatActivity {

    /**
     * @author Gabriel Bourque
     * @decription Activité présentant la liste de flux à l'usager et permettant d'en
     * rajouter et d'en retirer.
     * @param savedInstanceState
     */

    ArrayList<DetailFlux> mesFlux;
    ArrayAdapter<DetailFlux> aa;
    EditText et;
    DetailFlux df;
    Thread tAdd;
    Thread tDelete;
    ListView lv;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_flux);

        db = new DatabaseHelper(this);

        mesFlux = new ArrayList<DetailFlux>();
        et = findViewById(R.id.txtURL);

        lv = findViewById(R.id.lstListeFlux);

        Thread SavedFluxs = new Thread(new Runnable() {
            @Override
            public void run() {
                if (db.getFluxsCount() > 0){
                    for (Flux f : db.getAllFluxs()) {
                        try {
                            mesFlux.add(new DetailFlux(f.getUrl()));
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
        });

        SavedFluxs.start();
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
                    boolean exist = false;

                    for (Flux f: db.getAllFluxs()) {

                        if (f.getUrl().equals(et.getText().toString())){
                            exist = true;
                        }
                    }

                    if(!exist){

                        db.insertUrl(et.getText().toString());
                        mesFlux.add(df);
                        tAdd.join();
                    }


                    RefreshList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        tDelete = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        Button btnSupprimer = findViewById(R.id.btnSupprimer);
        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tDelete.start();


                //db.deleteFlux();
            }
        });
    }

    public void RefreshList()
    {
        ListeFluxActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                aa = new DetailFluxAdapter(ListeFluxActivity.this, 0, mesFlux);
                lv.setAdapter(aa);
            }
        });
    }
}
