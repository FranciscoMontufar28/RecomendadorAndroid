package com.example.francisco.recomendador.BackGround;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.francisco.recomendador.GsonPeliculasParser;
import com.example.francisco.recomendador.MainActivity;
import com.example.francisco.recomendador.PelicuasAdapter;
import com.example.francisco.recomendador.Pelicula;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francisco on 29/04/2016.
 */



public class PeliculasAsyncTask extends AsyncTask<URL, Void, List<Pelicula>>{
    /*public interface PeliculasAsyncTask{
        void onResult(List<Pelicula> peliculas);
    }*/

    //private PeliculasAsyncTask peliculasAsyncTask;
    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;


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


    /*@Override
    protected void onPostExecute(List<Pelicula> peliculas) {
        peliculasAsyncTask.onResult(peliculas);
    }*/
}
