package com.example.gbour.tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListeFluxActivity extends AppCompatActivity {

    /**
     * @author Gabriel Bourque
     * @decription Activité présentant la liste de flux à l'usager et permettant d'en
     * rajouter et d'en retirer.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_flux);
    }
}
