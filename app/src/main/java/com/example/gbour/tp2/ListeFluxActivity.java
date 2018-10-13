package com.example.gbour.tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

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
                    RefreshList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
