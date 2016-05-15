package com.example.francisco.recomendador.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francisco.recomendador.Models.Gustadas;
import com.example.francisco.recomendador.Models.Recomendacion;
import com.example.francisco.recomendador.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Francisco on 14/05/2016.
 */
public class PeliculasGustadasAdapter extends BaseAdapter{
    Context context;
    List<Gustadas> data;

    public PeliculasGustadasAdapter(Context context, List<Gustadas> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;


        if(v == null)
            v = View.inflate(context, R.layout.item_peliculas_gustadas, null);

        Gustadas r = data.get(position);

        TextView nombre = (TextView) v.findViewById(R.id.nombrepeliculagustada);
        ImageView img = (ImageView) v.findViewById(R.id.imgpeliculagustada);

        nombre.setText(r.getPelicula());

        Picasso.with(context).load(Uri.parse(r.getPoster())).into(img);

        return v;
    }

}