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


public class RegistrarseActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText correo;
    private EditText password;
    private EditText password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);



        mAuth = FirebaseAuth.getInstance();




        correo = findViewById(R.id.txtcorreoi);
        password = findViewById(R.id.txtpasswordi);
        password2 = findViewById(R.id.txtpassword2);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }


    public void registrarUsuario(View view){


    if (password.getText().toString().equals(password2.getText().toString().trim())){

      mAuth.createUserWithEmailAndPassword(correo.getText().toString().trim(),password.getText().toString().trim()).
              addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {

                             FirebaseUser user = mAuth.getCurrentUser();

                             Intent i = new Intent(getApplicationContext(), MainActivity.class);
                             startActivity(i);
                         }else{
                             Toast.makeText(getApplicationContext(), "Autenticacion FALLIDA", Toast.LENGTH_SHORT).show();
                         }
                  }
              });

    }
    else{

        Toast.makeText(this, " Las Contraseñas  no son iguales", Toast.LENGTH_SHORT).show();

    }











//        SignInCredential googleCredential = oneTapClient.getSignInCredentialFromIntent(data);
//        String idToken = googleCredential.getGoogleIdToken();
//        if (idToken !=  null) {
//            // Got an ID token from Google. Use it to authenticate
//            // with Firebase.
//            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
//            mAuth.signInWithCredential(firebaseCredential)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "signInWithCredential:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInWithCredential:failure", task.getException());
//                                updateUI(null);
//                            }
//                        }
//                    });
//        }



    }
}