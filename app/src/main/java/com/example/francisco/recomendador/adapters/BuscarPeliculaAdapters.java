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

import com.example.francisco.recomendador.Models.BuscarPelicula;
import com.example.francisco.recomendador.R;

public class BuscarPeliculaAdapters extends BaseAdapter {

    Context context;
    List<BuscarPelicula> data;

    public BuscarPeliculaAdapters(Context context, List<BuscarPelicula> data) {
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
            v = View.inflate(context, R.layout.pelicula_busqueda, null);

        BuscarPelicula b  = data.get(position);

        TextView Title = (TextView) v.findViewById(R.id.pb_nombre);
        TextView Year = (TextView) v.findViewById(R.id.pb_año);

        ImageView Img = (ImageView) v.findViewById(R.id.pb_imagen);

        Title.setText(b.getTitle());
        Year.setText(b.getYear());

        Picasso.with(context).load(Uri.parse(b.getPoster())).into(Img);

        return v;
    }


}
