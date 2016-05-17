package com.example.francisco.recomendador.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francisco.recomendador.Models.Nuestra;
import com.example.francisco.recomendador.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Francisco on 16/05/2016.
 */
public class NuestraRecomendacionAdapter extends BaseAdapter{
    Context context;
    List<Nuestra> data;

    public NuestraRecomendacionAdapter(Context context, List<Nuestra> data) {
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
            v = View.inflate(context, R.layout.item_nuestra_recomendacion, null);

        Nuestra r = data.get(position);

        TextView nombre = (TextView) v.findViewById(R.id.nombrePeliculaNuestra);
        TextView año = (TextView) v.findViewById(R.id.tagsPeliculaNuestra);
        ImageView img = (ImageView) v.findViewById(R.id.imagenPeliculaNuestra);

        nombre.setText(r.getNombre());
        año.setText(r.getYear());
        //tags.setText(r.getTags());

        Picasso.with(context).load(Uri.parse(r.getImagen())).into(img);

        return v;
    }

}
