package com.example.francisco.recomendador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.francisco.recomendador.models.Usuario;

public class Inicio_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText idusuario;
    Button buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_);
        idusuario = (EditText) findViewById(R.id.idusuarioinicio);
        buscar = (Button) findViewById(R.id.btnbuscar);
        
        buscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnbuscar:
                accionbuscar(this);
                break;
        }
    }

    private void accionbuscar(Context context) {

        String texto = idusuario.getText().toString();
        int x = Integer.parseInt(texto);
        Toast.makeText(this, ""+x, Toast.LENGTH_SHORT).show();
        Usuario.init(this, x);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
