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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText nombreApellidos;
    EditText nombreUsuario;
    EditText correo;
    EditText contraseña;
    Button registro;


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    //VARIABLES DE LOS DATODS QUE VAMOS A REGISTRAR
    private String name = "";
    private String user = "";
    private String email ="";
    private String password ="";
    private String[] sensores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        nombreApellidos = (EditText) findViewById(R.id.registerNombre);
        nombreUsuario = (EditText) findViewById(R.id.registerUsuario);
        correo = (EditText) findViewById(R.id.registerEmail);
        contraseña = (EditText) findViewById(R.id.registerPass);
        registro = (Button) findViewById(R.id.btn_registrar);
        sensores = new String[5];
        sensores[0]= "Sensor1";

        //Evento OnClickListener para el boton
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombreApellidos.getText().toString();
                user = nombreUsuario.getText().toString();
                email = correo.getText().toString();
                password = contraseña.getText().toString();


                if(!name.isEmpty() && !user.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    if(password.length() >=6){
                        registerUser();
                    }
                    else{
                        Toast.makeText(Register.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(Register.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String id = mAuth.getCurrentUser().getUid();


                    //Map<String, Object> sensor = new HashMap<>();

                    //map.put("ubicacion",ubicacion);
                    //sensor.put("id",user);
                    //map.put("estado",estado);



                    Map<String, Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("nameUser",user);
                    map.put("email",email);
                    map.put("password",password);
                    //map.put("sensores",sensor);





                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(Register.this,Usuario.class));
                                finish();

                            }
                            else{
                                Toast.makeText(Register.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
                else{
                    Toast.makeText(Register.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}