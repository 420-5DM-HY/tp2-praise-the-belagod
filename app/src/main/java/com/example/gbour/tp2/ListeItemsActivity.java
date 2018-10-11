package com.example.gbour.tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListeItemsActivity extends AppCompatActivity {

    ArrayList<DetailItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_items);

        Bundle extras = getIntent().getExtras();
        items = (ArrayList<DetailItem>) extras.getBundle("Bundle").getSerializable("Articles");
    }
}
