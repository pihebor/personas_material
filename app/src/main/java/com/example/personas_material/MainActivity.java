package com.example.personas_material;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorPErsona.OnPersonaClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab;
        RecyclerView lstPersonas;
        final ArrayList<Persona> personas;
        LinearLayoutManager llm;
        final AdaptadorPErsona adapter;

        lstPersonas= findViewById(R.id.lstPersona);
        personas = new ArrayList<>();
        llm= new LinearLayoutManager(this);
        adapter = new AdaptadorPErsona(personas, this);
        DatabaseReference databaseReference;
        String db ="Personas";

        llm.setOrientation(RecyclerView.VERTICAL);
        lstPersonas.setLayoutManager(llm);
        lstPersonas.setAdapter(adapter);

        fab = findViewById(R.id.btnAgregar);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            personas.clear();
            if (dataSnapshot.exists()){
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Persona p = dataSnapshot.getValue(Persona.class);
                    personas.add(p);
                }
            }
                adapter.notifyDataSetChanged();
                Datos.setPersonas(personas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void agregar (View v){
        Intent intent;
        intent = new Intent(MainActivity.this, AgregarPersona.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onPersonaClick (Persona p){
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("id", p.getId());
        bundle.putString("cedula", p.getCedula());
        bundle.putString("nombre", p.getNombre());
        bundle.putString("apellido", p.getApellido());


        intent = new Intent(MainActivity.this, DetallePErsona.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);
        //finish();
    }

}
