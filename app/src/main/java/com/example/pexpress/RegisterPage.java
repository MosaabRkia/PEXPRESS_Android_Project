package com.example.pexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    //emailRegister  passwordRegister confirmPasswordRegister
    TextView userName;
    TextView userLast;
    TextView email;
    TextView password;
    TextView ConPassword;
    Button RegisterBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        userName = findViewById(R.id.firstNameRegister);
        userLast = findViewById(R.id.lastNameRegister);

        RegisterBut = findViewById(R.id.registerButton);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);
        ConPassword = findViewById(R.id.confirmPasswordRegister);

        RegisterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }
    public void check(){
        //Toast.makeText(RegisterPage.this, "joined", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       // Toast.makeText(RegisterPage.this, password.toString(), Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            if(!password.getText().toString().equals(ConPassword.getText().toString())){
                                Toast.makeText(RegisterPage.this, "passwords is not the same !", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(RegisterPage.this, "sucess", Toast.LENGTH_SHORT).show();

                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(RegisterPage.this,MainActivity.class));
                            // updateUI(user);
                        } else {
                            Toast.makeText(RegisterPage.this, "failed", Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}