package com.example.apppem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SensoresUbicacionActivity extends AppCompatActivity {
    private RecyclerView lstPredicciones;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String estancia;

    private FloatingActionButton addSensor;

    FirebaseRecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmento_maestro);


        recibirDatos();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Sensores").child(estancia);
        addSensor = (FloatingActionButton) findViewById(R.id.floating_action_button);
        addSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra("estancia",estancia);
                startActivity(intent);
            }
        });
        RecyclerView recycler = (RecyclerView) findViewById(R.id.lstSensores);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        mAdapter =
                new FirebaseRecyclerAdapter<Sensor, SensorHolder>(
                        Sensor.class, R.layout.item_row, SensorHolder.class, mDatabase) {

                    @Override
                    public void populateViewHolder(SensorHolder predViewHolder, Sensor pred, int position) {
                        predViewHolder.setDispositivo(pred.getDispositivo());
                        predViewHolder.setEstado(pred.getEstado()+ "");
                        predViewHolder.setTemperatura(pred.getTemperatura() + "ºC");
                        predViewHolder.setHumedad(pred.getHumedad() + "%");
                    }
                };

        recycler.setAdapter(mAdapter);
    }

    private void recibirDatos() {
        Bundle extra = getIntent().getExtras();
        estancia = extra.getString("estancia");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }


    }
