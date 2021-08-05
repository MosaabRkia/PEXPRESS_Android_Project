package com.example.pexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class add_order extends AppCompatActivity {

    static int i = 0;
    FirebaseFirestore fStore;
    String emailIntent,nameIntent,UIDIntent;
    TextView title , name,countyFrom,addressTo;
    Button addOrderBtn;
    FirebaseFirestore db;
    AlertDialog.Builder sucessAlert;


    List<Order> ordersList;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

//        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        ordersList = new ArrayList<>();




        name = findViewById(R.id.nameUser);
        title = findViewById(R.id.titleUser);
        addOrderBtn = findViewById(R.id.addOrderBtn);
        countyFrom = findViewById(R.id.orderCountryAdd);
        addressTo = findViewById(R.id.orderAddressAdd);

        Intent intent = getIntent();
        if (intent.getStringExtra("email") != null) {
            emailIntent = intent.getStringExtra("email");
            title.setText("User Email :" + emailIntent);
        }
        else{
            title.setText("Unknown user");
        }
        if (intent.getStringExtra("fullName") != null) {
            nameIntent = intent.getStringExtra("fullName");
            name.setText("Name :" + nameIntent);
        }
        else{
            name.setText("Unknown user");
        }
        if (intent.getStringExtra("UID") != null){
            UIDIntent = intent.getStringExtra("UID");
            Toast.makeText(getApplicationContext(),UIDIntent, Toast.LENGTH_SHORT).show();
        }

        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"not delivery yet", "delivering", "delivered"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        sucessAlert = new AlertDialog.Builder(this);

        String text = dropdown.getSelectedItem().toString();
        Log.d("foundItItIt",text);

        reference = FirebaseDatabase.getInstance().getReference().child(UIDIntent);
        addOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueSelected = dropdown.getSelectedItem().toString();

               reference.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if (snapshot.exists()) {
                           for (DataSnapshot data : snapshot.getChildren()) {
                               Order order = data.getValue(Order.class);
                               ordersList.add(order);
                           }

                           Order ord = new Order(valueSelected, emailIntent, countyFrom.getText().toString(), addressTo.getText().toString());
                           ordersList.add(ord);
                           reference.setValue(ordersList).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   sucessAlert.setTitle("sucessfully added order for user " + nameIntent)
                                           .setMessage("UID : " + UIDIntent)
                                           .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {
                                                   startActivity(new Intent(getApplicationContext(), AdminShowUsers.class));
                                               }
                                           }).create().show();
                               }
                           });
                       }
                   }
                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
            }
        });
    }
}
