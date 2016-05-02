package com.example.francisco.recomendador;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.francisco.recomendador.ParseoPeliculaBusqueda.GsonOmdbParser;
import com.example.francisco.recomendador.ParseoPeliculaBusqueda.Omdb;
import com.example.francisco.recomendador.ParseoPeliculaBusqueda.OmdbAdapter;
import com.example.francisco.recomendador.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BuscarPeliculaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText pelicula;
    Button btnBuscar;

    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_pelicula);

        lista = (ListView) findViewById(R.id.Peliculabuscada);

        pelicula = (EditText) findViewById(R.id.buscarpelicula);
        btnBuscar = (Button) findViewById(R.id.btnbuscarpelicula);

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
        String nombredividido = nombre.replace(' ', '+');
        Toast.makeText(this,"http://www.omdbapi.com/?t="+nombredividido+"&y=&plot=short&r=json",Toast.LENGTH_LONG).show();

        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTaskdb().execute(new URL("http://www.omdbapi.com/?t="+nombredividido+"&y=&plot=short&r=json"));

                String link = ""+"http://www.omdbapi.com/?t="+nombredividido+"&y=&plot=short&r=json";

            } else {
                Toast.makeText(this, "Error de conexion", Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


   public class JsonTaskdb extends AsyncTask<URL, Void, List<Omdb>> {

        @Override
        protected List<Omdb> doInBackground(URL... urls) {
            List<Omdb> omdbs = null;

            try {

                // Establecer la conexiÃ³n
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if(statusCode!=200) {
                    omdbs = new ArrayList<>();
                    omdbs.add(new Omdb("Error",null,null,null,null));

                } else {

                    // Parsear el flujo con formato JSON
                    InputStream in = new BufferedInputStream(con.getInputStream());

                    // JsonAnimalParser parser = new JsonAnimalParser();
                    GsonOmdbParser parser = new GsonOmdbParser();

                    omdbs = parser.leerFlujoJsonOmdb(in);


                }

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return omdbs;
        }

        @Override
        protected void onPostExecute(List<Omdb> Omdb) {

            //Asignar los objetos de Json parseados al adaptador

            if(Omdb!=null) {
                adaptador = new OmdbAdapter(getBaseContext(), Omdb);
                lista.setAdapter(adaptador);
            }else{
                Toast.makeText(
                        getBaseContext(),
                        "Ocuriio un error de Parsing Json",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }



}
}
