package com.example.francisco.recomendador.net.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import com.example.francisco.recomendador.Models.Recomendacion;
import com.example.francisco.recomendador.R;
import com.example.francisco.recomendador.net.HttpAsyncTask;


public class RecomendacionApi implements HttpAsyncTask.OnResponseReceived {

    OnRecomendation onRecomendation;

    public interface OnRecomendation{
        void onRecomendation(List<Recomendacion> data);
    }

    public RecomendacionApi(OnRecomendation onRecomendation) {
        this.onRecomendation = onRecomendation;
    }

    public void getRecomendations(Context context,String id){
        String url = String.format(context.getString(R.string.url_recom), id);
        HttpAsyncTask task = new HttpAsyncTask(this);
        task.execute(url);

    }

    @Override
    public void onResponse(boolean success, String json) {
        Type type = new TypeToken<List<Recomendacion>>(){}.getType();

        Gson gson = new Gson();
        List<Recomendacion> data = gson.fromJson(json, type);
        onRecomendation.onRecomendation(data);
    }
}
