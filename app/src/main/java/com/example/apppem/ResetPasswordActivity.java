package com.example.apppem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText editEmail;
    private Button btnreset;

    private String email = "";

    //Para reestablecer la contraseña debemos crear un objeto tipo FirebaseAuth
    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth= FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        setContentView(R.layout.activity_reset_password);

        editEmail = (EditText) findViewById(R.id.resetEmail);
        btnreset = (Button) findViewById(R.id.btn_reset);

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editEmail.getText().toString();
                if (!email.isEmpty()){
                    mDialog.setMessage("Espere un momento ....");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPassword();



                }
                else{
                    Toast.makeText(ResetPasswordActivity.this, "Debe introducir el email", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    private void resetPassword(){
        //Primero introducimos el idioma en el que queremos que llegue el correo
        mAuth.setLanguageCode("es");
        //Ahora establecemos el metodo que nos enviara un correo para reestablecer la contraseña
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ResetPasswordActivity.this, "Se ha enviado un correo para reestablecer su contraseña", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ResetPasswordActivity.this, "No se pudo enviar el correo correctamente", Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss();

            }
        });


    }
}