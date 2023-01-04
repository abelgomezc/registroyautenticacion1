package com.abelgomez.registroyautenticacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class IniciarSesionActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText correo;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

      correo = findViewById(R.id.txtcorreoi);
      password= findViewById(R.id.txtpasswordi);

        mAuth = FirebaseAuth.getInstance();
    }


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    public void iniciarSesion(View view){

        mAuth.signInWithEmailAndPassword(correo.getText().toString().trim(),password.getText().toString().trim()).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Inicio de sesion exitosa", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);

                        }else{
                            Toast.makeText(getApplicationContext(), "Inicio de sesion fallida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}