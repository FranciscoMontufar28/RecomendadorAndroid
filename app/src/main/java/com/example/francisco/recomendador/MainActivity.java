package com.example.francisco.recomendador;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
           Variables globales
            */
    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;

    private Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista= (ListView) findViewById(R.id.listaPeliculas);

        /*
        Comprobar la disponibilidad de la Red
         */
        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                //new JsonTask().execute(new URL("http://servidorexterno.site90.com/datos/get_all_animals.php"));
                new JsonTask().execute(new URL("http://192.168.0.13:8080/Recomendador/RestServlet?id=10"));
            } else {
                Toast.makeText(this, "Error de conexion", Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }

    private MainActivity(Context contexto) {
        mcontext = contexto;
    }

    public class JsonTask extends AsyncTask<URL, Void, List<Pelicula>> {

        @Override
        protected List<Pelicula> doInBackground(URL... urls) {
            List<Pelicula> peliculas = null;

            try {

                // Establecer la conexiÃ³n
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if(statusCode!=200) {
                    peliculas = new ArrayList<>();
                    peliculas.add(new Pelicula("Error",null,null,null));

                } else {

                    // Parsear el flujo con formato JSON
                    InputStream in = new BufferedInputStream(con.getInputStream());

                    // JsonAnimalParser parser = new JsonAnimalParser();
                    GsonPeliculasParser parser = new GsonPeliculasParser();

                    peliculas = parser.leerFlujoJson(in);


                }

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return peliculas;
        }

        @Override
        protected void onPostExecute(List<Pelicula> peliculas) {
            /*
            Asignar los objetos de Json parseados al adaptador
             */
            if(peliculas!=null) {
                adaptador = new PelicuasAdapter(getBaseContext(), peliculas);
                lista.setAdapter(adaptador);
            }else{
                Toast.makeText(
                        getBaseContext(),
                        "Ocuriio un error de Parsing Json",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }

        /*@Override
        public void onItemClick(int position) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.KEY_ID, data.get(position).getId());
            startActivity(intent);
        }*/

        /*@Override
        protected void onResult(List<Pelicula> peliculas) {

            if(peliculas!=null) {
                adaptador = new PelicuasAdapter(getBaseContext(), peliculas);
                lista.setAdapter(adaptador);
            }else{
                Toast.makeText(
                        getBaseContext(),
                        "Ocuriio un error de Parsing Json",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }*/
    }
}
