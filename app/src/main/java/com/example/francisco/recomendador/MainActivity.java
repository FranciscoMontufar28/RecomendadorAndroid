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

import com.example.francisco.recomendador.Models.Recomendacion;
import com.example.francisco.recomendador.adapters.RecomendacionAdapters;
import com.example.francisco.recomendador.net.api.RecomendacionApi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecomendacionApi.OnRecomendation, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    //RecyclerView list;
    ListView list;
    List<Recomendacion> data;
    NavigationView nav;
    RecomendacionAdapters adapter;
    RecomendacionApi recomendacionApi;
    EditText editText;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Usuario Id = Usuario.findById(Usuario.class, 1);
        //String Id = getIntent().getStringExtra("id");
        list= (ListView) findViewById(R.id.listaPeliculas);
        data = new ArrayList<>();
        nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(this);
        editText = (EditText) findViewById(R.id.EditUsrMain);
        button = (Button) findViewById(R.id.btnUsrMain);
        button.setOnClickListener(this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent detalles = new Intent(getApplicationContext(), Pelicula_Activity.class);
                detalles.putExtra("nombre", data.get(position).getNombre().toString());
                detalles.putExtra("año", data.get(position).getTags().toString());
                startActivity(detalles);

            }
        });


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
                Intent intent1 = new Intent(this, UsuariosSimilaresActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_buscar:
                //Toast.makeText(this, "main activit",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(this, BuscarPeliculaActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_perfil:
                Intent intent3 = new Intent(this, PeliculasGustadasActivity.class);
                startActivity(intent3);
                break;

        }
        return false;
    }

    @Override
    public void onRecomendation(List<Recomendacion> data) {

        for(Recomendacion r:data){
            this.data.add(r);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnUsrMain:
                generarpelicularecomendada();
        }
    }

    private void generarpelicularecomendada() {

        //data.clear();
        String Id = editText.getText().toString();
        recomendacionApi= new RecomendacionApi(this);
        recomendacionApi.getRecomendations(this, Id);
        adapter = new RecomendacionAdapters(this, data);
        list.setAdapter(adapter);
    }
}
