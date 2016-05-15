package com.example.francisco.recomendador;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.francisco.recomendador.Models.Gustadas;
import com.example.francisco.recomendador.adapters.PeliculasGustadasAdapter;
import com.example.francisco.recomendador.net.api.GustadasApi;

import java.util.ArrayList;
import java.util.List;

public class PeliculasGustadasActivity extends AppCompatActivity implements GustadasApi.Onlike,  View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    ListView list;
    List<Gustadas> data;
    NavigationView nav;
    EditText editText;
    Button button;
    PeliculasGustadasAdapter adapter;
    GustadasApi gustadasApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas_gustadas);
        //Usuario Id = Usuario.findById(Usuario.class, 1);
        //String Id = getIntent().getStringExtra("id");
        list= (ListView) findViewById(R.id.listaPeliculasGustadas);
        data = new ArrayList<>();
        nav = (NavigationView) findViewById(R.id.nav_gustadas);
        nav.setNavigationItemSelectedListener(this);
        editText = (EditText) findViewById(R.id.EditPeliculasGustadas);
        button = (Button) findViewById(R.id.btnPeliculasGustadas);
        button.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_inicio_perfil:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_usuarios_perfil:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, UsuariosSimilaresActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_buscar_perfil:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(this, BuscarPeliculaActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_perfil_perfil:
                Intent intent3 = new Intent(this, PeliculasGustadasActivity.class);
                startActivity(intent3);
                break;
        }
        return false;
    }

    @Override
    public void onlike(List<Gustadas> data) {

        for(Gustadas r:data){
            this.data.add(r);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnPeliculasGustadas:
                mostrarpeliculas();
        }
    }

    private void mostrarpeliculas() {

        //data.clear();
        String Id = editText.getText().toString();
        gustadasApi= new GustadasApi(this);
        gustadasApi.getGustadas(this, Id);
        adapter = new PeliculasGustadasAdapter(this, data);
        list.setAdapter(adapter);
    }
}