package com.example.francisco.recomendador.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView recomendacion = (TextView) v.findViewById(R.id.coeficienteusuariosimilar);
        TextView usuario = (TextView) v.findViewById(R.id.nombreusuariosimilar);
        ImageView imagen = (ImageView) v.findViewById(R.id.imgpeliculagustada);

        recomendacion.setText(r.getRecomendacion());
        usuario.setText(r.getUsuario());

        Picasso.with(context).load(Uri.parse(r.getImagen())).into(imagen);

        return v;
    }

}
