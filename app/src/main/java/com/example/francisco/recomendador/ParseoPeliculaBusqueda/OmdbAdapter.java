package com.example.francisco.recomendador.ParseoPeliculaBusqueda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francisco.recomendador.MainActivity;
import com.example.francisco.recomendador.R;

import java.util.List;

/**
 * Created by Francisco on 29/04/2016.
 */
public class OmdbAdapter extends ArrayAdapter<Omdb> {

    TextView title;
    TextView year;
    TextView genre;
    TextView plot;
    ImageView poster;


    public OmdbAdapter(Context context, List<Omdb> objects) {

        super(context, 0, objects);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){


        //Obteniendo una instancia del inflater
        LayoutInflater inflateromdb = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View b = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo
            b = inflateromdb.inflate(R.layout.pelicula_busqueda, parent, false);
        }
        //Obteniendo instancias de los elementos
        title = (TextView)b.findViewById(R.id.nombrePeliculaBuscada);
        year= (TextView)b.findViewById(R.id.añoPeliculaBuscada);
        genre= (TextView) b.findViewById(R.id.generoPeliculaBuscada);
        plot= (TextView) b.findViewById(R.id.descripcionPeliculaBuscada);
        poster= (ImageView)b.findViewById(R.id.imagenPeliculaBuscada);

        //Obteniendo instancia de la Tarea en la posiciÃ³n actual

        Omdb item = getItem(position);
        //Context contexto = imagenpelicula.getContext();
        //Picasso.with(contexto).load(item.getImagen()).into(imagenpelicula);

        //Picasso.with(context).load(item.getImagen()).into(imagenpelicula);

        title.setText(item.getTitle());
        year.setText(item.getYear());
        genre.setText(item.getGenre());
        plot.setText(item.getPlot());
        //Context context1 = mainActivity.getBaseContext();
        //Picasso.with(context1).load(item.getImagen()).into(imagenpelicula);

        //imagenpelicula.setImageURI(Uri.parse(item.getImagen()));
        poster.setImageResource(R.drawable.filmacion);
        //imagenpelicula.setImageResource(Integer.parseInt(item.getImagen()));
        //imagenpelicula.setImageResource(convertirRutaEnId(item.getImagen()));

        //Devolver al ListView la fila creada
        return b;

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
        return context.getResources().getIdentifier(nombre, "drawable", context.getPackageName());
    }


}
