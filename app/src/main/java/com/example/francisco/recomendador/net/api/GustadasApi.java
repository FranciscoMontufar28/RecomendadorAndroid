package com.example.francisco.recomendador.net.api;

import android.content.Context;

import com.example.francisco.recomendador.Models.Gustadas;
import com.example.francisco.recomendador.Models.Recomendacion;
import com.example.francisco.recomendador.R;
import com.example.francisco.recomendador.net.HttpAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Francisco on 14/05/2016.
 */
public class GustadasApi implements HttpAsyncTask.OnResponseReceived {

    Onlike onlike;

    public interface Onlike{
        void onlike(List<Gustadas> data);
    }

    public GustadasApi(Onlike onlike) {
        this.onlike = onlike;
    }

    public void getGustadas(Context context,String id){
        String url = String.format(context.getString(R.string.url_liked), id);
        HttpAsyncTask task = new HttpAsyncTask(this);
        task.execute(url);

    }

    @Override
    public void onResponse(boolean success, String json) {
        Type type = new TypeToken<List<Gustadas>>(){}.getType();

        Gson gson = new Gson();
        List<Gustadas> data = gson.fromJson(json, type);
        onlike.onlike(data);
    }
}

