package com.example.francisco.recomendador;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.francisco.recomendador.Models.BuscarPelicula;
import com.example.francisco.recomendador.adapters.BuscarPeliculaAdapters;
import com.example.francisco.recomendador.net.api.OmdbSearchApi;

import java.util.ArrayList;
import java.util.List;

public class BuscarPeliculaActivity extends AppCompatActivity implements View.OnClickListener, OmdbSearchApi.OnOmdbSearch {

    EditText pelicula;
    Button btnBuscar;

    ListView list;
    OmdbSearchApi omdbSearchApi;

    List<BuscarPelicula> data;
    BuscarPeliculaAdapters adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_pelicula);
        data = new ArrayList<>();

        list = (ListView) findViewById(R.id.Peliculabuscada);
        pelicula = (EditText) findViewById(R.id.buscarpelicula);
        btnBuscar = (Button) findViewById(R.id.btnbuscarpelicula);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent lanzar = new Intent(getApplicationContext(), Pelicula_Activity.class);
                lanzar.putExtra("nombre", data.get(position).getTitle().toString());
                lanzar.putExtra("año", data.get(position).getYear().toString());
                startActivity(lanzar);
            }
        });
        btnBuscar.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnbuscarpelicula:
                buscarpelicula();
                break;
        }
    }

    private void buscarpelicula() {

        String nombre = pelicula.getText().toString();
        omdbSearchApi = new OmdbSearchApi(this);
        adapter = new BuscarPeliculaAdapters(this, data);
        omdbSearchApi.getDescriptionSearch(this, nombre, "");
        list.setAdapter(adapter);


    }


    @Override
    public void onOmdbSearch(List<BuscarPelicula> data) {
        for(BuscarPelicula b:data){
            this.data.add(b);
        }
        adapter.notifyDataSetChanged();
    }
}
