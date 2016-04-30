package com.example.francisco.recomendador;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francisco on 29/04/2016.
 */
public class JsonPeliculaParser {
    public List<Pelicula> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return leerArrayPeliculas(reader);
        } finally {
            reader.close();
        }

    }



    public List<Pelicula> leerArrayPeliculas(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList<Pelicula> peliculas = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            peliculas.add(leerPelicula(reader));
        }
        reader.endArray();
        return peliculas;
    }

    public Pelicula leerPelicula(JsonReader reader) throws IOException {
        // Variables locales
        String nombre = null;
        String tags = null;
        String imagen = null;
        String recomendacion = null;

        // Iniciar objeto
        reader.beginObject();

        /*
        Lectura de cada atributo
         */
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "nombre":
                    nombre = reader.nextString();
                    break;
                case "tags":
                    tags = reader.nextString();
                    break;
                case "imagen":
                    imagen = reader.nextString();
                    break;
                case "recomendacion":
                    recomendacion = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Pelicula(nombre, tags, imagen, recomendacion);
    }

}
