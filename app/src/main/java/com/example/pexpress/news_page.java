package com.example.pexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class news_page extends AppCompatActivity {

    ArrayList newsList;
    Button addNewsBtn;
    ListView mListView;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

    

        LinearLayout loading = (LinearLayout) findViewById(R.id.loadingGif);
        mListView = findViewById(R.id.ListOfNews);
        newsList = new ArrayList<News>();
        addNewsBtn = findViewById(R.id.addNewsBtn);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser.getUid().equals("1yDeZFWsPGVFl2suOG4SJ8PKlRI2") && currentUser != null){
// Gets the layout params that will allow you to resize the layout
            addNewsBtn.setVisibility(View.VISIBLE);
        }
        else{
            addNewsBtn.setVisibility(View.INVISIBLE);
        }

        addNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),add_news_admin.class));
            }
        });
        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Profile.class));
            }
        });
        
        
        
        reference = FirebaseDatabase.getInstance().getReference().child("news");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot data:snapshot.getChildren()){
                        News news = data.getValue(News.class);
                        newsList.add(news);
                       // Log.i("dataaaaaaaaaa",order.getAddress_to() + " " +  order.getCountry_from()+ " " +order.getDate()+ " " +order.getStatus());
                    }
                    BarNewsAdapter adapter = new BarNewsAdapter(getApplicationContext(), R.layout.news_bar, newsList);
                   mListView.setAdapter(adapter);

                    // Gets linearlayout
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









