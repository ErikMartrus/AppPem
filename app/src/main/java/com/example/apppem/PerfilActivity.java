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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PerfilActivity extends AppCompatActivity {
    EditText nombreApellidos;
    EditText nombreUsuario;
    EditText correo;
    EditText contraseña;
    Button actualizar;


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    //VARIABLES DE LOS DATODS QUE VAMOS A REGISTRAR
    private String name = "";
    private String user = "";
    private String email = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombreApellidos = (EditText) findViewById(R.id.actualizarNombre);
        nombreUsuario = (EditText) findViewById(R.id.actualizarUsuario);
        correo = (EditText) findViewById(R.id.actualizarEmail);
        contraseña = (EditText) findViewById(R.id.actualizarPass);
        actualizar = (Button) findViewById(R.id.btn_actualizar);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombreApellidos.getText().toString();
                user = nombreUsuario.getText().toString();
                email = correo.getText().toString();
                password = contraseña.getText().toString();


                if (password.length() >= 6) {
                    actualizarDatos();


                } else {
                    Toast.makeText(PerfilActivity.this, "La contraseña ha de tener un minimo de 6 caracteres", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void actualizarDatos() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("nameUser", user);
        map.put("email", email);
        map.put("password", password);
        String id = mAuth.getCurrentUser().getUid();

        mDatabase.child("Users").child(id).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(PerfilActivity.this, "Los datos se han actualizado correctamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PerfilActivity.this,Usuario.class));
                finish();



            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PerfilActivity.this, "Ha habido un error al actualizar los datos", Toast.LENGTH_SHORT).show();

            }
        });

    }
}