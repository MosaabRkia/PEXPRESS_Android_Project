package com.example.pexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    TextView email;
    TextView password;
    Button btnToReg;
    Button btnLog ;
    TextView forgotPasswordBtn;
    String userId;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;
    FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.LoginEmail);
        password = findViewById(R.id.LoginPassword);
        btnLog = findViewById(R.id.ButtonLogin);

        btnToReg = findViewById(R.id.buttonRegister);
        forgotPasswordBtn = findViewById(R.id.forgotPassword);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        reset_alert = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = inflater.inflate(R.layout.reset_password_pop,null);
                reset_alert.setTitle("reset forgot password ?")
                        .setMessage("enter your email address to get password reset link.")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText email = view.findViewById(R.id.resetPassword);
                                if(email.getText().toString().isEmpty()){
                                    email.setError("Required Field");
                                    return;
                                }
                            firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //sucess
                                    Toast.makeText(MainActivity.this, "reset email sent",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //failed
                                    Toast.makeText(MainActivity.this, "make sure email wroten in right way",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                            }
                        }).setNegativeButton("Cancel",null)
                        .setView(view)
                        .create().show();

            }
        });

        btnToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterPage.class));
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLogin();
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void CheckLogin(){
        if(email.getText().toString().isEmpty() && password.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "Please Fill Fields.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        fAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                             userId = fAuth.getCurrentUser().getUid();

                            if(userId == "1yDeZFWsPGVFl2suOG4SJ8PKlRI2"){
                                Toast.makeText(MainActivity.this, "Admin",
                                        Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(MainActivity.this,Profile.class));
                                //FirebaseUser user = fAuth.getCurrentUser();
                                return;
                            }

                             FirebaseUser userAuth = fAuth.getCurrentUser();
                            // Sign in success, update UI with the signed-in user's information
                            //  Log.d(TAG, "signInWithEmail:success");
                            if(!userAuth.isEmailVerified()){
                                Toast.makeText(MainActivity.this, "Email not Verified please verify it before login!",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Toast.makeText(MainActivity.this, "Sucessfully Login",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,Profile.class));
                            FirebaseUser user = fAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //  Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });

    }
}