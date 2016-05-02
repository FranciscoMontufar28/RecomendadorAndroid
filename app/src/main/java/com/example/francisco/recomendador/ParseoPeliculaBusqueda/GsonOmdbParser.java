package com.example.francisco.recomendador.ParseoPeliculaBusqueda;

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
public class GsonOmdbParser {

    public List<Omdb> leerFlujoJsonOmdb(InputStream in) throws IOException {
        // Nueva instancia de la clase Gson
        Gson gson = new Gson();

        JsonReader readerdb = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Omdb> Omdbd = new ArrayList<>();

        // Iniciar el array
        readerdb.beginArray();

        while (readerdb.hasNext()) {
            // Lectura de objetos
            Omdb omdb = gson.fromJson(readerdb, Omdb.class);
            Omdbd.add(omdb);
        }


        readerdb.endArray();
        readerdb.close();
        return Omdbd;
    }

}
