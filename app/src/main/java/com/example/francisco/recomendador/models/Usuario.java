package com.example.francisco.recomendador.Models;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

public class Usuario extends SugarRecord{
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public static void init(Context context, String id){
        SugarContext.init(context);

        if(count(Usuario.class)<1) {
            Usuario usuario = new Usuario();
            usuario.setNombre(id);

            usuario.save();
        }
    }
}
