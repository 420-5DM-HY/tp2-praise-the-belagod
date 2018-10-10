package com.example.gbour.tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListeFluxActivity extends AppCompatActivity {

    /**
     * @author Gabriel Bourque
     * @decription Activité présentant la liste de flux à l'usager et permettant d'en
     * rajouter et d'en retirer.
     * @param savedInstanceState
     */

    ArrayList<DetailFlux> mesFlux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_flux);

        mesFlux = new ArrayList<DetailFlux>();

        ArrayAdapter<DetailFlux> aa = new DetailFluxAdapter(this, 0, mesFlux);
        final ListView lv = findViewById(R.id.lstListeFlux);
        lv.setAdapter(aa);

        Button btnAjouter = findViewById(R.id.btnAjouter);
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = ListeFluxActivity.this.findViewById(R.id.txtURL);
                mesFlux.add(new DetailFlux(et.getText().toString()));
            }
        });
    }
}
