package com.example.francisco.recomendador;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.francisco.recomendador.Models.Descripcion;
import com.example.francisco.recomendador.net.HttpAsyncTask;
import com.example.francisco.recomendador.net.api.OmdbApi;
import com.squareup.picasso.Picasso;

public class Pelicula_Calificar_Activity extends AppCompatActivity implements OmdbApi.OnOmdb, View.OnClickListener, HttpAsyncTask.OnResponseReceived {

    OmdbApi omdbApi;

    EditText id;
    EditText calificacion;

    Button button;

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

    String omdbId;

    ImageView Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula__calificar_);
        String Nombre = getIntent().getStringExtra("nombre");
        String Año = getIntent().getStringExtra("año");

        Title = (TextView) findViewById(R.id.dt_title_calificar);
        Year = (TextView) findViewById(R.id.dt_year_calificar);
        Rate = (TextView) findViewById(R.id.dt_rate_calificar);
        Director = (TextView) findViewById(R.id.dt_director_calificar);
        Released = (TextView) findViewById(R.id.dt_released_calificar);
        Plot = (TextView) findViewById(R.id.dt_plot_calificar);
        Actor = (TextView) findViewById(R.id.dt_actor_calificar);
        Escritor = (TextView) findViewById(R.id.dt_escritor_calificar);
        Genero = (TextView) findViewById(R.id.dt_genero_calificar);
        Premios = (TextView) findViewById(R.id.dt_premios_calificar);
        Duracion = (TextView) findViewById(R.id.dt_duracion_calificar);

        Img = (ImageView) findViewById(R.id.dt_img_calificar);

        id = (EditText) findViewById(R.id.IdUsuarioCalificar);
        calificacion = (EditText) findViewById(R.id.CalificacionUsuario);

        button = (Button) findViewById(R.id.BtnCalificacionUsuario);

        button.setOnClickListener(this);


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
        Premios.setText("Actores: " + des.getAwards());
        Duracion.setText("Duracion: " + des.getRuntime());

        omdbId = ""+des.getImdbID();


        Picasso.with(this).load(Uri.parse("" + des.getPoster())).into(Img);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.BtnCalificacionUsuario:
                enviarcalificacion();
                break;
        }

    }

    private void enviarcalificacion() {
        String IdUsr=id.getText().toString();
        String CalificacionUsr = calificacion.getText().toString();
        //Toast.makeText(this, IdUsr+","+""+omdbId+","+CalificacionUsr, Toast.LENGTH_LONG).show();
        String url = String.format(getString(R.string.url_calificar), IdUsr+","+""+omdbId+","+CalificacionUsr);
        HttpAsyncTask task = new HttpAsyncTask(this);
        task.execute(url);

    }

    @Override
    public void onResponse(boolean success, String json) {
        Toast.makeText(this, "Calificacion Agregada", Toast.LENGTH_LONG).show();
    }
}