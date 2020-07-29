package com.example.apppem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddActivity extends AppCompatActivity {
    EditText nombreDispotivo;
    EditText temperatura;
    EditText humedad;
    EditText estado;
    Button add;

    private String estancia;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    //VARIABLES DE LOS DATODS QUE VAMOS A REGISTRAR
    private String name = "";
    private Double temp = 0.0;
    private Double hum =0.0;
    private int state =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        recibirEstancia();

        nombreDispotivo = (EditText) findViewById(R.id.addNombreDispositivo);
        temperatura = (EditText) findViewById(R.id.addTemperatura);
        humedad = (EditText) findViewById(R.id.addHumedad);
        estado = (EditText) findViewById(R.id.addEstado);
        add = (Button) findViewById(R.id.btn_add);

        //Evento OnClickListener para el boton
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombreDispotivo.getText().toString();
                temp = parseDouble(temperatura.getText().toString());
                hum = parseDouble(humedad.getText().toString());
                state = parseInt(estado.getText().toString());



                if(!name.isEmpty() && temp!=null && hum!=null && state!=0 || state !=1){
                        addSensor();


                }
                else{
                    Toast.makeText(AddActivity.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void recibirEstancia() {

        Bundle extra = getIntent().getExtras();
        estancia = extra.getString("estancia");

    }

    private void addSensor(){

                    Map<String, Object> map = new HashMap<>();
                    map.put("dispositivo",name);
                    map.put("estado",state);
                    map.put("humedad",hum);
                    map.put("temperatura",temp);






                    mDatabase.child("Sensores").child(estancia).child(name).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(), SensoresUbicacionActivity.class);
                                intent.putExtra("estancia",estancia);
                                finish();

                            }
                            else{
                                Toast.makeText(AddActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });





    }
}

