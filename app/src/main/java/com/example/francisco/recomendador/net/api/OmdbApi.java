package com.example.francisco.recomendador.net.api;

import android.content.Context;

import com.google.gson.Gson;

import com.example.francisco.recomendador.Models.Descripcion;
import com.example.francisco.recomendador.R;
import com.example.francisco.recomendador.net.HttpAsyncTask;


public class OmdbApi implements HttpAsyncTask.OnResponseReceived {

    OnOmdb onOmdb;

    public interface OnOmdb{
        void onOmdb(Descripcion des);
    }

    public OmdbApi(OnOmdb onRecomendation) {
        this.onOmdb = onRecomendation;
    }

    public void getDescription(Context context,String name, String year){

        String n =  name.replaceAll(" ", "+");
        String url = String.format(context.getString(R.string.url_omdb), n, year);
        HttpAsyncTask task = new HttpAsyncTask(this);
        task.execute(url);

    }

    @Override
    public void onResponse(boolean success, String json) {
        Gson gson = new Gson();
        Descripcion data = gson.fromJson(json, Descripcion.class);
        onOmdb.onOmdb(data);
    }

}
