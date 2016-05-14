package com.example.francisco.recomendador.net.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import com.example.francisco.recomendador.Models.BuscarPelicula;
import com.example.francisco.recomendador.R;
import com.example.francisco.recomendador.net.HttpAsyncTask;

/**
 * Created by EQUIPO-ASUS on 06/05/2016.
 */
public class OmdbSearchApi implements HttpAsyncTask.OnResponseReceived{

    OnOmdbSearch onOmdbSearch;

    public interface OnOmdbSearch{

        void onOmdbSearch(List<BuscarPelicula> data);
    }

    public OmdbSearchApi(OnOmdbSearch onOmdbSearch) {
        this.onOmdbSearch = onOmdbSearch;
    }

    public void getDescriptionSearch(Context context, String name, String year){

        String n =  name.replaceAll(" ", "+");
        String url = String.format(context.getString(R.string.url_omdb_array), n, year);
        HttpAsyncTask task = new HttpAsyncTask(this);
        task.execute(url);
    }

    @Override
    public void onResponse(boolean success, String json) {

        Gson gson = new Gson();

        try {

            JSONObject obj = new JSONObject(json);
            Type type = new TypeToken<List<BuscarPelicula>>(){}.getType();
            List<BuscarPelicula> data = gson.fromJson(obj.get("Search").toString(), type);
            onOmdbSearch.onOmdbSearch(data);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
