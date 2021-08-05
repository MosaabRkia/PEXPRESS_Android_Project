package com.example.pexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {

    public static final String TAG = "TAG";
    private FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView firstname;
    TextView lastname;
    TextView email;
    TextView password;
    TextView ConPassword;
    Button RegisterBut;
    String userID;
    String emailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        firstname = findViewById(R.id.resetPassword);
        lastname = findViewById(R.id.lastNameRegister);
        RegisterBut = findViewById(R.id.registerButton);
        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);
        ConPassword = findViewById(R.id.confirmPasswordRegister);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        RegisterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }

    public void sendToast(String text){
        Toast.makeText(RegisterPage.this, text, Toast.LENGTH_SHORT).show();
    }

    public void check(){
            if(firstname.getText().toString().isEmpty()){
                sendToast("first name is Empty !");
                return;
            }
            if(lastname.getText().toString().isEmpty()){
                sendToast("last name is Empty !");
                return;
            }
            if(email.getText().toString().isEmpty()){
                sendToast("email is Empty !");
                return;
            }
            if(password.getText().toString().isEmpty()) {
                sendToast("password is Empty !");
                return;
            }
            if(!password.getText().toString().equals(ConPassword.getText().toString())){
                sendToast("Password is not the same !");
                return;
            }

             fAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser userVerification = fAuth.getCurrentUser();
                            userVerification.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    sendToast("Verification Email Has Been Sent.");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"Email not sent. error : " + e.getMessage());
                                }
                            });

                            Toast.makeText(RegisterPage.this, "success", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("userInfo",new User(firstname.getText().toString(),lastname.getText().toString(),email.getText().toString(),password.getText().toString(),userID));
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>(){
                                @Override
                                public void onSuccess(Void aVoid){
                                    Log.d(TAG,"sucessfully registered info user uID: "+userID);
                                }
                            });

                            startActivity(new Intent(RegisterPage.this,MainActivity.class));

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