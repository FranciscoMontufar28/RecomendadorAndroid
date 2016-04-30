package com.example.francisco.recomendador;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francisco on 29/04/2016.
 */
public class GsonPeliculasParser {

    public List<Pelicula> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia de la clase Gson
        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Pelicula> peliculas = new ArrayList<>();

        // Iniciar el array
        reader.beginArray();

        while (reader.hasNext()) {
            // Lectura de objetos
            Pelicula pelicula = gson.fromJson(reader, Pelicula.class);
            peliculas.add(pelicula);
        }


        reader.endArray();
        reader.close();
        return peliculas;
    }

}
