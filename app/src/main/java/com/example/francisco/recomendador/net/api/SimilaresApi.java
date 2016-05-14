package com.example.francisco.recomendador.net.api;

import android.content.Context;

import com.example.francisco.recomendador.Models.Similares;
import com.example.francisco.recomendador.R;
import com.example.francisco.recomendador.net.HttpAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Francisco on 13/05/2016.
 */
public class SimilaresApi implements HttpAsyncTask.OnResponseReceived {

    OnSimilares onSimilares;

    public interface OnSimilares{
        void onSimilares(List<Similares> data);
    }

    public SimilaresApi(OnSimilares OnSimilares) {
        this.onSimilares = onSimilares;
    }

    public void getSimilares(Context context,String id){
        String url = String.format(context.getString(R.string.url_simila), id);
        HttpAsyncTask task = new HttpAsyncTask(this);
        task.execute(url);

    }

    @Override
    public void onResponse(boolean success, String json) {
        Type type = new TypeToken<List<Similares>>(){}.getType();

        Gson gson = new Gson();
        List<Similares> data = gson.fromJson(json, type);
        onSimilares.onSimilares(data);
    }
}
