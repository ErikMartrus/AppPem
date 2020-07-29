package com.example.apppem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Usuario extends AppCompatActivity {

    private Button btnSignOut;
    private FirebaseAuth mAuth;
    private TextView nombreApellidos;
    private TextView  nombreUsuario;
    private TextView  correo;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        ImageButton home = (ImageButton) findViewById(R.id.m_home);
        ImageButton sensor = (ImageButton) findViewById(R.id.m_sensor);

        nombreApellidos = (TextView ) findViewById(R.id.textRealName);
        nombreUsuario = (TextView ) findViewById(R.id.textUser);
        correo = (TextView ) findViewById(R.id.textEmail);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(intent);
                finish();

            }
        });

        sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SensoresActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //Falta el de los sensores

        btnSignOut = (Button) findViewById(R.id.btn_cerrar);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(Usuario.this,MainActivity.class));
                finish();

            }
        });

        getUserInfo();

    }

    private void getUserInfo(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nameUser = snapshot.child("nameUser").getValue().toString();
                    String realName = snapshot.child("name").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();

                    nombreApellidos.setText(realName);
                    nombreUsuario.setText(nameUser);
                    correo.setText(email);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}