package com.example.personas_material;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab;
        RecyclerView lstPersonas;
        ArrayList<Persona> personas;
        AdaptadorPErsona adapter;
        LinearLayoutManager llm;

        lstPersonas= findViewById(R.id.lstPersona);
        personas = Datos.obtener();
        llm= new LinearLayoutManager(this);
        adapter = new AdaptadorPErsona(personas);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstPersonas.setLayoutManager(llm);
        lstPersonas.setAdapter(adapter);

        fab = findViewById(R.id.btnAgregar);


    }

    public void agregar (View v){
        Intent i;
        i = new Intent(MainActivity.this, AgregarPersona.class);
        startActivity(i);
        finish();
    }

}
