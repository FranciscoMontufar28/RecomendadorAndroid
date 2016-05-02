package com.example.francisco.recomendador.ParseoPeliculaBusqueda;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francisco on 29/04/2016.
 */
public class JsonOmdbParser {
    public List<Omdb> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return leerArrayOmdb(reader);
        } finally {
            reader.close();
        }

    }



    public List<Omdb> leerArrayOmdb(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList<Omdb> omdb = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            omdb.add(leerOmdb(reader));
        }
        reader.endArray();
        return omdb;
    }

    public Omdb leerOmdb(JsonReader reader) throws IOException {
        // Variables locales
        String title = null;
        String year = null;
        String genre = null;
        String plot = null;
        String poster = null;

        // Iniciar objeto
        reader.beginObject();

        /*
        Lectura de cada atributo
         */
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "Title":
                    title = reader.nextString();
                    break;
                case "Year":
                    year = reader.nextString();
                    break;
                case "Genre":
                    genre = reader.nextString();
                    break;
                case "Plot":
                    plot = reader.nextString();
                    break;
                case "Poster":
                    poster = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Omdb(title, year, genre, plot, poster);
    }

}
