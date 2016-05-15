package com.example.francisco.recomendador;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francisco.recomendador.Models.Descripcion;
import com.example.francisco.recomendador.net.api.OmdbApi;
import com.squareup.picasso.Picasso;

public class Pelicula_Activity extends AppCompatActivity implements OmdbApi.OnOmdb{

    OmdbApi omdbApi;

    TextView Title;
    TextView Year;
    TextView Rate;
    TextView Director;
    TextView Released;
    TextView Plot;
    TextView Actor;
    TextView Escritor;
    TextView Genero;
    TextView Premios;
    TextView Duracion;



    ImageView Img;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_);

        String Nombre = getIntent().getStringExtra("nombre");
        String Año = getIntent().getStringExtra("año");

        Title = (TextView) findViewById(R.id.dt_title);
        Year = (TextView) findViewById(R.id.dt_year);
        Rate = (TextView) findViewById(R.id.dt_rate);
        Director = (TextView) findViewById(R.id.dt_director);
        Released = (TextView) findViewById(R.id.dt_released);
        Plot = (TextView) findViewById(R.id.dt_plot);
        Actor = (TextView) findViewById(R.id.dt_actor);
        Escritor = (TextView) findViewById(R.id.dt_escritor);
        Genero = (TextView) findViewById(R.id.dt_genero);
        Premios = (TextView) findViewById(R.id.dt_premios);
        Duracion = (TextView) findViewById(R.id.dt_duracion);

        Img = (ImageView) findViewById(R.id.dt_img);

        omdbApi = new OmdbApi(this);

        omdbApi.getDescription(this,Nombre,Año);

    }

    @Override
    public void onOmdb(Descripcion des) {

        Director.setText("Director: "+des.getDirector());
        Year.setText("Año : "+des.getYear());
        Released.setText("Lanzamiento : "+des.getReleased());
        Plot.setText("Descripcion: "+des.getPlot());
        Rate.setText("Clasificacion: "+des.getRated());
        Title.setText("Titulo: "+des.getTitle());
        Actor.setText("Actores: "+des.getActors());
        Escritor.setText("Escritores: "+des.getWriter());
        Genero.setText("Generos: "+des.getGenre());
        Premios.setText("Actores: "+des.getAwards());
        Duracion.setText("Duracion: "+des.getRuntime());

        Picasso.with(this).load(Uri.parse("" + des.getPoster())).into(Img);

    }
}