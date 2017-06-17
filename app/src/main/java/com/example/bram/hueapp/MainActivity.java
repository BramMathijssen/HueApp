package com.example.bram.hueapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * Referentie naar de (singleton) Bridge.
     */
    private final Bridge bridge = Bridge.getInstance();

    /**
     * Referentie naar de lijst van Light-instanties in de Bridge.
     */
    private final ArrayList<Light> list = bridge.getLights();

    /**
     * ListView voor de lijst met lampen.
     */
    private ListView listView;

    /**
     * Adapter voor de ListView.
     */
    private LightAdapter adapter;

    /**
     * Lifecycle method onCreate()
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lightListView);
        adapter = new LightAdapter(this, getLayoutInflater(), list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(this);
    }

    /**
     * Implementatie van AdapterView.OnItemClickListener.onItemClick().
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getApplicationContext(), LightDetailsActivity.class);
        i.putExtra("INDEX", position);
        startActivity(i);
    }

}