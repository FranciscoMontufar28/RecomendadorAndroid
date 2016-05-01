package com.example.francisco.recomendador.models;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

/**
 * Created by Francisco on 30/04/2016.
 */
public class Usuario extends SugarRecord{

    String usrid;

    //region setter and getter
    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String id) {
        this.usrid = id;
    }
    //endregion

    public static void init(Context context, int x){
        SugarContext.init(context);
        if(count(Usuario.class)<1) {
            Usuario usuario = new Usuario();
            usuario.setUsrid(""+x);
            usuario.save();
        }
    }
}
