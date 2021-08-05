package com.example.pexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrdersList extends AppCompatActivity {

    ArrayList orders;

    ListView mListView;
    ArrayList<Order> ordersList;
    DatabaseReference reference;
    String emailIntent,nameIntent,UIDIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);

        LinearLayout loading = (LinearLayout) findViewById(R.id.loadingGif);
        Intent intent = getIntent();
        if (intent.getStringExtra("UID") != null) {
            UIDIntent = intent.getStringExtra("UID");
        } else UIDIntent = "1yDeZFWsPGVFl2suOG4SJ8PKlRI2";


        mListView = findViewById(R.id.ListOfOrders);
        orders = new ArrayList<Order>();


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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("data ======>>>>>","empty data");
            }
        });


    }
}









