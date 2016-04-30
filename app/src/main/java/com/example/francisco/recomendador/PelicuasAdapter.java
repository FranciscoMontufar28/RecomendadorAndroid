package com.example.francisco.recomendador;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by Francisco on 29/04/2016.
 */
public class PelicuasAdapter extends ArrayAdapter<Pelicula> {

    TextView nombrepelicula;
    TextView tagspelicula;
    ImageView imagenpelicula;
    TextView recomendacion;
    MainActivity mainActivity;


    public PelicuasAdapter(Context context, List<Pelicula> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){


        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View v = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo
            v = inflater.inflate(R.layout.item_lista, parent, false);
        }
        //Obteniendo instancias de los elementos
        nombrepelicula = (TextView)v.findViewById(R.id.nombrePelicula);
        tagspelicula= (TextView)v.findViewById(R.id.tagsPelicula);
        recomendacion= (TextView) v.findViewById(R.id.recomendacion);
        imagenpelicula = (ImageView)v.findViewById(R.id.imagenPelicula);

        //Obteniendo instancia de la Tarea en la posiciÃ³n actual

        Pelicula item = getItem(position);
        //Context contexto = imagenpelicula.getContext();
        //Picasso.with(contexto).load(item.getImagen()).into(imagenpelicula);

        //Picasso.with(context).load(item.getImagen()).into(imagenpelicula);

        nombrepelicula.setText(item.getNombre());
        tagspelicula.setText(item.getTags());
        recomendacion.setText(item.getRecomendacion());

        //Picasso.with(this)
        //imagenpelicula.setImageURI(Uri.parse(item.getImagen()));
        imagenpelicula.setImageResource(R.drawable.filmacion);
        //imagenpelicula.setImageResource(Integer.parseInt(item.getImagen()));
        //imagenpelicula.setImageResource(convertirRutaEnId(item.getImagen()));

        //Devolver al ListView la fila creada
        return v;

    }



    /**
     * Este mÃ©todo nos permite obtener el Id de un drawable a travÃ©s
     * de su nombre
     * @param nombre  Nombre del drawable sin la extensiÃ³n de la imagen
     *
     * @return Retorna un tipo int que representa el Id del recurso
     */
    private int convertirRutaEnId(String nombre){
        Context context = getContext();
        return context.getResources()
                .getIdentifier(nombre, "drawable", context.getPackageName());
    }
}
