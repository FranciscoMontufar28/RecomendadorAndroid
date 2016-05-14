package com.example.francisco.recomendador.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.francisco.recomendador.Models.Recomendacion;
import com.example.francisco.recomendador.R;


/**
 * Created by EQUIPO-ASUS on 05/05/2016.
 */
public class RecomendacionAdapters extends BaseAdapter {

    Context context;
    List<Recomendacion> data;

    public RecomendacionAdapters(Context context, List<Recomendacion> data) {
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
            v = View.inflate(context, R.layout.item_lista, null);

        Recomendacion r = data.get(position);

        TextView nombre = (TextView) v.findViewById(R.id.nombrePelicula);
        TextView recomendacion = (TextView) v.findViewById(R.id.recomendacion);
        TextView tags = (TextView) v.findViewById(R.id.tagsPelicula);
        ImageView img = (ImageView) v.findViewById(R.id.imagenPelicula);

        nombre.setText(r.getNombre());
        tags.setText(r.getTags());
        recomendacion.setText(r.getRecomendacion());
        //tags.setText(r.getTags());

        Picasso.with(context).load(Uri.parse(r.getImagen())).into(img);

        return v;
    }

}
