package com.example.pexpress;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class TimesOpen extends AppCompatActivity {

    ImageView ShowBar ;
    DrawerLayout drawerLayout1;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_open);

        navigationView = findViewById(R.id.nav_view_test);
        drawerLayout1=findViewById(R.id.drawerLayout);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout1, R.string.openBar, R.string.closeBar);
        drawerLayout1.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {
                // Snackbar.make(contentLayout, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                // menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(TimesOpen.this, Profile.class));
                        break;
                    case R.id.nav_Calculate:
                        startActivity(new Intent(TimesOpen.this, PricesPage.class));
                        break;

                    case R.id.nav_logout:
                        startActivity(new Intent(TimesOpen.this, MainActivity.class));
                        break;
                    case R.id.nav_Terms:
                        startActivity(new Intent(TimesOpen.this, Terms.class));
                        break;
                    case R.id.nav_Opens:
                        startActivity(new Intent(TimesOpen.this, TimesOpen.class));
                        break;

                }
                drawerLayout1.closeDrawer(GravityCompat.START);
                return true;
            }

        });
        navigationView.setCheckedItem(R.id.nav_Opens);

        ShowBar = findViewById(R.id.ShowBar);

        ShowBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(MainActivity.this, RegisterPage.class));
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });
    }
}