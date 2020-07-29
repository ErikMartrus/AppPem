package com.example.apppem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnregistrar;
    Button btnlogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        btnlogin = findViewById(R.id.btnInicialLogin);

        btnregistrar=findViewById(R.id.mainRegister);
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegistro = new Intent(MainActivity.this,Register.class);
                MainActivity.this.startActivity(intentRegistro);

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Se contempla la posibilidad de que si el usuario ya ha hecho el Login y se cierra la aplicacion, no es necesario volver a realizar el login
        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this,Usuario.class));
            finish();

        }
    }
}