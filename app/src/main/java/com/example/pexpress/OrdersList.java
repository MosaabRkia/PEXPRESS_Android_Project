package com.example.pexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrdersList extends AppCompatActivity {

    ArrayList orders;

    ListView mListView;
    private FirebaseAuth mAuth;
    ArrayList<Order> ordersList;
    DatabaseReference reference;
    String emailIntent,nameIntent,UIDIntent;
    Button addOrderButton;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
        addOrderButton = findViewById(R.id.addOrderButton);
        LinearLayout loading = (LinearLayout) findViewById(R.id.loadingGif);
        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
//
            }
        });
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


            if(currentUser != null && currentUser.getUid().equals("1yDeZFWsPGVFl2suOG4SJ8PKlRI2")){
                //show
                addOrderButton.setVisibility(View.VISIBLE);
            }else{
                //hide
                addOrderButton.setVisibility(View.INVISIBLE);
        }



        Intent intent = getIntent();
        if (intent.getStringExtra("UID") != null) {
            UIDIntent = intent.getStringExtra("UID");
            Toast.makeText(getApplicationContext(), UIDIntent, Toast.LENGTH_SHORT).show();
        } else UIDIntent = "null";

        if (intent.getStringExtra("fullName") != null) {
            nameIntent = intent.getStringExtra("fullName");
        } else nameIntent = "null";

        if (intent.getStringExtra("email") != null) {
            emailIntent = intent.getStringExtra("email");
        } else emailIntent = "null";

//        if(UIDIntent.equals("1yDeZFWsPGVFl2suOG4SJ8PKlRI2")){
//            addOrderButton.setVisibility(View.VISIBLE);
//        }else{
//            addOrderButton.setVisibility(View.INVISIBLE);
//        }

        mListView = findViewById(R.id.ListOfOrders);
        orders = new ArrayList<Order>();
        addOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), add_order.class);
                intent.putExtra("email",emailIntent);
                intent.putExtra("fullName",nameIntent);
                intent.putExtra("UID",UIDIntent);
                getApplicationContext().startActivity(intent);
            }
        });



        reference = FirebaseDatabase.getInstance().getReference().child(UIDIntent);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot data:snapshot.getChildren()){
                        Order order = data.getValue(Order.class);
                        orders.add(order);
                        Log.i("dataaaaaaaaaa",order.getAddress_to() + " " +  order.getCountry_from()+ " " +order.getDate()+ " " +order.getStatus());
                    }
                    BarOrderAdapter adapter = new BarOrderAdapter(getApplicationContext(), R.layout.orderbar, orders);
                    mListView.setAdapter(adapter);
                    LinearLayout layout = findViewById(R.id.loadingGif);
// Gets the layout params that will allow you to resize the layout
                    ViewGroup.LayoutParams params = layout.getLayoutParams();
// Changes the height and width to the specified *pixels*
                    params.height = 0;
                    params.width = 0;
                    layout.setLayoutParams(params);

                    loading.setVisibility(View.INVISIBLE);
                }else{
                    Log.i("nooooooooooooo data",snapshot.toString());
                    LinearLayout layout = findViewById(R.id.loadingGif);
// Gets the layout params that will allow you to resize the layout
                    ViewGroup.LayoutParams params = layout.getLayoutParams();
// Changes the height and width to the specified *pixels*
                    params.height = 0;
                    params.width = 0;
                    layout.setLayoutParams(params);

                    loading.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                LinearLayout layout = findViewById(R.id.loadingGif);
// Gets the layout params that will allow you to resize the layout
                ViewGroup.LayoutParams params = layout.getLayoutParams();
// Changes the height and width to the specified *pixels*
                params.height = 0;
                params.width = 0;
                layout.setLayoutParams(params);

                loading.setVisibility(View.INVISIBLE);
                Log.i("data ======>>>>>","empty data");
            }
        });


    }
}









