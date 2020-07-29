package com.example.apppem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SensoresActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        CardView botonCocina = (CardView) findViewById(R.id.cardKitchen);
        CardView botonHabitacion = (CardView) findViewById(R.id.cardRoom);
        CardView botonSalon = (CardView) findViewById(R.id.cardLivingRoom);
        CardView botonAseo = (CardView) findViewById(R.id.cardWC);


        mAuth = FirebaseAuth.getInstance();


        botonCocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SensoresActivity.this, "Ha seleccionado los sensores que se encuentran en la COCINA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SensoresUbicacionActivity.class);
                intent.putExtra("estancia","Cocina");
                startActivity(intent);
            }
        });

        botonSalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SensoresActivity.this, "Ha seleccionado los sensores que se encuentran en el SALÓN", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SensoresUbicacionActivity.class);
                intent.putExtra("estancia","Salon");
                startActivity(intent);

            }
        });

        botonHabitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SensoresActivity.this, "Ha seleccionado los sensores que se encuentran en la HABITACIÓN", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SensoresUbicacionActivity.class);
                intent.putExtra("estancia","Habitacion");
                startActivity(intent);
            }
        });

        botonAseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SensoresActivity.this, "Ha seleccionado los sensores que se encuentran en el BAÑO", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SensoresUbicacionActivity.class);
                intent.putExtra("estancia","Aseo");
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View view) {

    }
}







