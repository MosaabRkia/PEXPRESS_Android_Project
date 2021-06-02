package com.example.pexpress;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class Profile extends AppCompatActivity {

    ImageView ShowBar ;
    DrawerLayout drawerLayout1;
    NavigationView navigationView;
    CardView FamilyBut;
    CardView EditProfile;
    CardView MyOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        FamilyBut = findViewById(R.id.FamilyBut);
        EditProfile = findViewById(R.id.EditProfile);
        MyOrders = findViewById(R.id.MyOrders);

        FamilyBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(Profile.this, ListFamily.class));
            }
        });

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, EditProfile.class));
            }
        });

        MyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, OrderList.class));
            }
        });


        drawerLayout1=findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);

        ShowBar = findViewById(R.id.ShowBar);

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
                        startActivity(new Intent(Profile.this, AfterLoginPage.class));
                        break;
                    case R.id.nav_Calculate:
                        startActivity(new Intent(Profile.this, PricesPage.class));
                        break;
                    case R.id.nav_ContactUs:
                        startActivity(new Intent(Profile.this, ContactUs.class));
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
                    case R.id.nav_Profile:
                        startActivity(new Intent(Profile.this, Profile.class));
                        break;
                }
                drawerLayout1.closeDrawer(GravityCompat.START);
                return true;
            }

        });
        navigationView.setCheckedItem(R.id.nav_home);


        ShowBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(MainActivity.this, RegisterPage.class));
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });
    }
}