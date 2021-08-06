package com.example.pexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Profile extends AppCompatActivity {

    TextView welcoming;
    String name;
    String userUID;
    private FirebaseAuth mAuth;
    CardView MyOrdersBtn,NewsBtn,usersBtnAdmin;
    User userSignedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);



        MyOrdersBtn = findViewById(R.id.MyOrdersBtn);
        NewsBtn = findViewById(R.id.NewsBtn);
        usersBtnAdmin = findViewById(R.id.usersBtnAdmin);
        LinearLayout adminBTN = (LinearLayout) findViewById(R.id.usersBtnAdminLinear);
        LinearLayout ordersBTN = (LinearLayout) findViewById(R.id.myOrdersLinear);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // No user is signed in go login
        } else {

            if(currentUser.getUid().equals("1yDeZFWsPGVFl2suOG4SJ8PKlRI2")){
// Gets the layout params that will allow you to resize the layout
                ViewGroup.LayoutParams params = ordersBTN.getLayoutParams();
// Changes the height and width to the specified *pixels*
                params.height = 0;
                params.width = 0;
                ordersBTN.setLayoutParams(params);


            }else{
                ViewGroup.LayoutParams params = adminBTN.getLayoutParams();
// Changes the height and width to the specified *pixels*
                params.height = 0;
                params.width = 0;
                adminBTN.setLayoutParams(params);

            }

            // User logged in
             userUID = currentUser.getUid();
        }
        Log.i("userId===>",userUID);



        LocalTime time = LocalTime.now(ZoneId.of("Asia/Jerusalem"));
        welcoming = findViewById(R.id.titleWelcome);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        MyOrdersBtn = findViewById(R.id.MyOrdersBtn);

        DocumentReference documentReference = db.collection("users").document(userUID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            //String list = documentSnapshot.get("userInfo");
                            //String value = documentSnapshot.getString("firstName");
                            Object x = documentSnapshot.get("userInfo");
                            HashMap finalData = (HashMap) x;
                            userSignedIn = new User(finalData.get("firstName").toString(), finalData.get("lastName").toString(), finalData.get("email").toString(), finalData.get("password").toString(),finalData.get("uid").toString());


                            if(time.getHour() > 4 && time.getHour() < 13){
                                welcoming.setText("Good Morning " + userSignedIn.getFirstName() + " " + userSignedIn.getLastName() );
                            }
                            if(time.getHour() > 13 && time.getHour() < 19){
                                welcoming.setText("Good afternoon " + userSignedIn.getFirstName() + " " + userSignedIn.getLastName() );
                            }
                            if(time.getHour() > 19 && time.getHour() < 23){
                                welcoming.setText("Good evening " + userSignedIn.getFirstName() + " " + userSignedIn.getLastName());
                            }
                            if(time.getHour() >= 0 && time.getHour() < 5){
                                welcoming.setText("Good evening " + userSignedIn.getFirstName() + " " + userSignedIn.getLastName());
                            }



                            NewsBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getApplicationContext(), news_page.class);
                                    intent.putExtra("UID",userSignedIn.getUID());
                                    startActivity(intent);
                                }
                            });

                            MyOrdersBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getApplicationContext(), OrdersList.class);
                                    intent.putExtra("UID",userSignedIn.getUID());
                                    startActivity(intent);
                                }
                            });

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


//        Date currentTime = Calendar.getInstance().getTime();
//        Date date = new Date();   // given date
//        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
//        calendar.setTime(currentTime);   // assigns calendar to given date
//        calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
//        //calendar.get(Calendar.HOUR);        // gets hour in 12h format
//        calendar.get(Calendar.MONTH);


       //good morning 5-12
        //after noon 12-18
        //good evening 18-22
        //good night 22-5




//        FamilyBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 startActivity(new Intent(Profile.this, ListFamily.class));
//            }
//        });
//
//        EditProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Profile.this, EditProfile.class));
//            }
//        });
//
//        MyOrders.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Profile.this, OrderList.class));
//            }
//        });


        DrawerLayout drawerLayout1=findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view_test);

        ImageView ShowBar = findViewById(R.id.ShowBar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout1, R.string.openBar, R.string.closeBar);
        drawerLayout1.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(Profile.this, Profile.class));
                        break;
                    case R.id.nav_Calculate:
                        startActivity(new Intent(Profile.this, PricesPage.class));
                        break;

                    case R.id.nav_logout:
                        startActivity(new Intent(Profile.this, MainActivity.class));
                        break;
                    case R.id.nav_Terms:
                        startActivity(new Intent(Profile.this, Terms.class));
                        break;
                    case R.id.nav_Opens:
                        startActivity(new Intent(Profile.this, TimesOpen.class));
                        break;

                }
                drawerLayout1.closeDrawer(GravityCompat.START);
                return true;
            }

        });
        navigationView.setCheckedItem(R.id.nav_home);

        usersBtnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminShowUsers.class));
            }
        });

        ShowBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(MainActivity.this, RegisterPage.class));
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });
    }
}