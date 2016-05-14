package com.example.francisco.recomendador.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francisco.recomendador.Models.Recomendacion;
import com.example.francisco.recomendador.Models.Similares;
import com.example.francisco.recomendador.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Francisco on 13/05/2016.
 */
public class UsuariosSimilaresAdapter extends BaseAdapter {

    Context context;
    List<Similares> data;

    public UsuariosSimilaresAdapter(Context context, List<Similares> data) {
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
            v = View.inflate(context, R.layout.item_usuarios_similares, null);

        Similares r = data.get(position);

        TextView coenficiente = (TextView) v.findViewById(R.id.coeficienteusuariosimilar);
        TextView id = (TextView) v.findViewById(R.id.idusuariosimilar);
       // TextView tags = (TextView) v.findViewById(R.id.tagsPelicula);
       // ImageView img = (ImageView) v.findViewById(R.id.imagenPelicula);

        coenficiente.setText(r.getCoenficiente());
        id.setText(r.getId());
        //recomendacion.setText(r.getRecomendacion());
        //tags.setText(r.getTags());

        //Picasso.with(context).load(Uri.parse(r.getImagen())).into(img);

        return v;
    }

}
