package com.example.francisco.recomendador;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.francisco.recomendador.Models.Nuestra;
import com.example.francisco.recomendador.Models.Recomendacion;
import com.example.francisco.recomendador.adapters.NuestraRecomendacionAdapter;
import com.example.francisco.recomendador.adapters.RecomendacionAdapters;
import com.example.francisco.recomendador.net.api.NuestraApi;
import com.example.francisco.recomendador.net.api.RecomendacionApi;

import java.util.ArrayList;
import java.util.List;

public class NuestraRecomendacion extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, NuestraApi.OnNuestra {

    ListView list;
    List<Nuestra> data;
    NavigationView nav;
    NuestraRecomendacionAdapter adapter;
    NuestraApi nuestraApi;
    EditText editText;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestra_recomendacion);
        list= (ListView) findViewById(R.id.listaNuestro);
        data = new ArrayList<>();
        nav = (NavigationView) findViewById(R.id.nav_nuestro);
        nav.setNavigationItemSelectedListener(this);
        editText = (EditText) findViewById(R.id.EditUsrNuestro);
        button = (Button) findViewById(R.id.btnUsrNuestro);
        button.setOnClickListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detalles = new Intent(getApplicationContext(), Pelicula_Activity.class);
                detalles.putExtra("nombre", data.get(position).getNombre().toString());
                detalles.putExtra("año", data.get(position).getYear().toString());
                startActivity(detalles);

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_inicio_recomendacion:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_usuarios_recomendacion:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, UsuariosSimilaresActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_buscar_recomendacion:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(this, BuscarPeliculaActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_perfil_recomendacion:
                Intent intent3 = new Intent(this, PeliculasGustadasActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_recomendacion_recomendacion:
                Intent intent4 = new Intent(this, NuestraRecomendacion.class);
                startActivity(intent4);
                break;

        }
        return false;
    }

    @Override
    public void onNuestra (List<Nuestra> data) {

        for(Nuestra r:data){
            this.data.add(r);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnUsrNuestro:
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                generarpelicularecomendada();
                break;
        }
    }

    private void generarpelicularecomendada() {

        //data.clear();
        String Id = editText.getText().toString();
        nuestraApi= new NuestraApi(this);
        nuestraApi.getOwnRecomendations(this, Id);
        adapter = new NuestraRecomendacionAdapter(this, data);
        list.setAdapter(adapter);
    }
}