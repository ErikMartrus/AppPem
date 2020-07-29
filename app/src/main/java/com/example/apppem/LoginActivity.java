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

public class LoginActivity extends AppCompatActivity {
    private EditText contraseña;
    private Button btnLogin;
    private EditText correo;
    private Button btnPass;

    private String email ="";
    private String password = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        correo = (EditText) findViewById(R.id.loginEmail);
        contraseña = (EditText) findViewById(R.id.loginPass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnPass = (Button) findViewById(R.id.btn_pass);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = correo.getText().toString();
                password = contraseña.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()){
                    loginUser();

                }
                else{
                    Toast.makeText(LoginActivity.this, "Complete los campos correctamente", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
            }
        });
    }

    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this,Usuario.class));
                    finish();
                }
                else{ 
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesion y que compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}