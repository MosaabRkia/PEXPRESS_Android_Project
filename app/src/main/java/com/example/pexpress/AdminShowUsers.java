package com.example.pexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AdminShowUsers extends AppCompatActivity {

    ArrayList users;
    FirebaseFirestore db;
    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_users);
         mListView =  findViewById(R.id.bte56);
        users = new ArrayList<User>();
        LinearLayout loading = (LinearLayout) findViewById(R.id.loadingGif);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<Object> list = queryDocumentSnapshots.toObjects(Object.class);
                            for (Object d : list) {
                                HashMap convert1 = (HashMap) d;
                                Object convert2 = convert1.get("userInfo");
                                HashMap finalData = (HashMap) convert2;
                                User newUser = new User(finalData.get("firstName").toString(), finalData.get("lastName").toString(), finalData.get("email").toString(), finalData.get("password").toString(),finalData.get("uid").toString());
                                users.add(newUser);

                            }
                            UserListAdapter adapter = new UserListAdapter(getApplicationContext(), R.layout.user_bar_display, users);

                            Log.i("mListViewwwwwwwwwwwwww",mListView.toString());
                            mListView.setAdapter(adapter);


                        }
                    }
                });


    }
}



