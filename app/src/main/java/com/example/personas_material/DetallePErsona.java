package com.example.personas_material;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePErsona extends AppCompatActivity {
    private Persona p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_p_ersona);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ImageView foto;
        TextView cedula, nombre, apellido;
        Bundle bundle;
        Intent intent;
        String ced, nom, apell;
        int fot;

        foto = findViewById(R.id.imgFotoDetalle);
        cedula = findViewById(R.id.lblCedulaDetalle);
        nombre = findViewById(R.id.lblNombreDetalle);
        apellido = findViewById(R.id.lblApellidoDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        fot = bundle.getInt("foto");
        ced = bundle.getString("cedula");
        nom = bundle.getString("nombre");
        apell = bundle.getString("apellido");

        foto.setImageResource(bundle.getInt("foto"));
        cedula.setText(bundle.getString("cedula"));
        nombre.setText(bundle.getString("nombre"));
        apellido.setText(bundle.getString("apellido"));

        p = new Persona (ced, nom, apell, fot);
    }

    public void onBackPressed (){
        finish();
        Intent intent = new Intent(DetallePErsona.this, MainActivity.class);
        startActivity(intent);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_eliminar);
        builder.setMessage(R.string.pregunta_mensaje_eliminar);
        positivo = getString(R.string.opcion_si);
        negativo = getString(R.string.opcion_no);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                p.eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
