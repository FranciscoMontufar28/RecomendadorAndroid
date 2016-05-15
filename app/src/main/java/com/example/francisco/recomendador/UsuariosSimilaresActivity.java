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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.francisco.recomendador.Models.Recomendacion;
import com.example.francisco.recomendador.Models.Similares;
import com.example.francisco.recomendador.adapters.UsuariosSimilaresAdapter;
import com.example.francisco.recomendador.net.api.SimilaresApi;

import java.util.ArrayList;
import java.util.List;

public class UsuariosSimilaresActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SimilaresApi.OnSimilares, View.OnClickListener {
    ListView list;
    List<Similares> data;
    NavigationView nav;
    SimilaresApi similaresApi;
    UsuariosSimilaresAdapter adapter;
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_similares);

        //String Id = getIntent().getStringExtra("id");
        list= (ListView) findViewById(R.id.listaUsuariosSimilares);

        data = new ArrayList<>();


        nav = (NavigationView) findViewById(R.id.navUsuariosSimilares);
        nav.setNavigationItemSelectedListener(this);

        editText = (EditText) findViewById(R.id.EditUsrSimilar);
        button = (Button) findViewById(R.id.btnUsrSimilar);

        button.setOnClickListener(this);






    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_inicio_similares:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_usuarios_similares:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, UsuariosSimilaresActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_buscar_similares:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(this, BuscarPeliculaActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_perfil_similares:
                Intent intent3 = new Intent(this, PeliculasGustadasActivity.class);
                startActivity(intent3);
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnUsrSimilar:
                buscarUsuariosSimilares();
                break;
        }

    }

    private void buscarUsuariosSimilares() {

        String Id = editText.getText().toString();
        similaresApi= new SimilaresApi(this);
        similaresApi.getSimilares(this, Id);
        adapter = new UsuariosSimilaresAdapter(this, data);
        list.setAdapter(adapter);
    }


}
