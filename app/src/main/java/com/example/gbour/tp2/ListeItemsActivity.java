package com.example.gbour.tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.libfluxrss.RssItem;

import java.util.ArrayList;
import java.util.List;

public class ListeItemsActivity extends AppCompatActivity {

    ArrayList<RssItem> items;
    ArrayAdapter<DetailFlux> aa;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_items);

        Bundle extras = getIntent().getExtras();
        items = (ArrayList<RssItem>) extras.getBundle("Bundle").getSerializable("Articles");

        lv = findViewById(R.id.lstFluxItems);
        RefreshList();
    }

    public void RefreshList()
    {
        ListeItemsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                aa = new DetailItemAdapter(ListeItemsActivity.this, 0, items);
                lv.setAdapter(aa);
            }
        });
    }
}
