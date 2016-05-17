package com.example.francisco.recomendador.net.api;

import android.content.Context;

import com.example.francisco.recomendador.Models.Nuestra;
import com.example.francisco.recomendador.net.HttpAsyncTask;
import com.example.francisco.recomendador.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Francisco on 16/05/2016.
 */
public class NuestraApi implements HttpAsyncTask.OnResponseReceived  {
    OnNuestra onNuestra;

    public interface OnNuestra{
        void onNuestra(List<Nuestra> data);
    }

    public NuestraApi(OnNuestra onNuestra) {
        this.onNuestra = onNuestra;
    }

    public void getOwnRecomendations(Context context,String id){
        String url = String.format(context.getString(R.string.url_own), id);
        HttpAsyncTask task = new HttpAsyncTask(this);
        task.execute(url);

    }

    @Override
    public void onResponse(boolean success, String json) {
        Type type = new TypeToken<List<Nuestra>>(){}.getType();

        Gson gson = new Gson();
        List<Nuestra> data = gson.fromJson(json, type);
        onNuestra.onNuestra(data);
    }
}

