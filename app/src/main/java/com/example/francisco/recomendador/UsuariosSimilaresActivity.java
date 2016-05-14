package com.example.francisco.recomendador;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.francisco.recomendador.Models.Recomendacion;
import com.example.francisco.recomendador.Models.Similares;
import com.example.francisco.recomendador.adapters.UsuariosSimilaresAdapter;
import com.example.francisco.recomendador.net.api.SimilaresApi;

import java.util.ArrayList;
import java.util.List;

public class UsuariosSimilaresActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SimilaresApi.OnSimilares {
    ListView list;
    List<Similares> data;
    NavigationView nav;
    SimilaresApi similaresApi;
    UsuariosSimilaresAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_similares);

        String Id = getIntent().getStringExtra("id");
        list= (ListView) findViewById(R.id.listaUsuariosSimilares);
        similaresApi= new SimilaresApi(this);
        similaresApi.getSimilares(this, Id);
        data = new ArrayList<>();
        adapter = new UsuariosSimilaresAdapter(this, data);

        nav = (NavigationView) findViewById(R.id.navUsuariosSimilares);
        nav.setNavigationItemSelectedListener(this);

        list.setAdapter(adapter);




    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_inicio:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_usuarios:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_buscar:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(this, BuscarPeliculaActivity.class);
                startActivity(intent2);
                break;
        }
        return false;
    }
    @Override
    public void onSimilares(List<Similares> data) {

        for(Similares r:data){
            this.data.add(r);
        }
        adapter.notifyDataSetChanged();
    }

}
