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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AfterLoginPage extends AppCompatActivity {

    DrawerLayout drawerLayout1;
    ImageView ShowBar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_page);

        ShowBar = findViewById(R.id.ShowBar);
        drawerLayout1 = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
//int double string float final static public private protected void (type return)

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
                                                                         Toast.makeText(AfterLoginPage.this, "Home", Toast.LENGTH_SHORT).show();
                                                                         break;
                                                                     case R.id.nav_Calculate:
                                                                         startActivity(new Intent(AfterLoginPage.this, PricesPage.class));
                                                                         break;
                                                                     case R.id.nav_ContactUs:
                                                                         Intent intent = new Intent(AfterLoginPage.this, ContactUs.class);
                                                                         startActivity(intent);
                                                                         break;
                                                                     case R.id.nav_logout:
                                                                         Intent intent1 = new Intent(AfterLoginPage.this, MainActivity.class);
                                                                         intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                         // add where not allow to back
                                                                         startActivity(intent1);
                                                                         break;
                                                                     case R.id.nav_Terms:
                                                                         startActivity(new Intent(AfterLoginPage.this, Terms.class));
                                                                         break;
                                                                     case R.id.nav_Opens:
                                                                         Intent intent6 = new Intent(AfterLoginPage.this, TimesOpen.class);
                                                                         intent6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                         startActivity(intent6);
                                                                         break;
                                                                     case R.id.nav_Profile:
                                                                         //go to profile
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
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });
    }

}
