package com.example.pexpress;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class ListFamily extends AppCompatActivity {

    int[] IMAGES = {R.drawable.photo1, R.drawable.photo2, R.drawable.photo3, R.drawable.photo4};
    String[] Names = {"A A Ran", "Big B", "dani jan", "selena gomez"};
    String[] IDs = {"222222" , "444444", "555555", "666666"};
    User[] users;
    
    ListView listView1;
    ImageView ShowBar ;
    DrawerLayout drawerLayout1;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_family);

        navigationView = findViewById(R.id.nav_view);
        listView1 = (ListView) findViewById(R.id.listView1);
        CustomAdapter customAdapter = new CustomAdapter();
        listView1.setAdapter(customAdapter);

        drawerLayout1=findViewById(R.id.drawerLayout);
        // navigationView=findViewById(R.id.nav_view);
        // textView=findViewById(R.id.textView);

        ShowBar = findViewById(R.id.ShowBar);


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
                        startActivity(new Intent(ListFamily.this, AfterLoginPage.class));
                        break;
                    case R.id.nav_Calculate:
                        startActivity(new Intent(ListFamily.this, PricesPage.class));
                        break;
                    case R.id.nav_ContactUs:
                        startActivity(new Intent(ListFamily.this, ContactUs.class));
                        break;
                    case R.id.nav_logout:
                        startActivity(new Intent(ListFamily.this, MainActivity.class));
                        break;
                    case R.id.nav_Terms:
                        startActivity(new Intent(ListFamily.this, Terms.class));
                        break;
                    case R.id.nav_Opens:
                        startActivity(new Intent(ListFamily.this, TimesOpen.class));
                        break;
                    case R.id.nav_Profile:
                        startActivity(new Intent(ListFamily.this, Profile.class));
                        break;
                }
                drawerLayout1.closeDrawer(GravityCompat.START);
                return true;
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

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

        view = getLayoutInflater().inflate(R.layout.layout_profiles, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);
        TextView textView_Name = (TextView)view.findViewById(R.id.textView_Name);
        TextView textView_Dec = (TextView)view.findViewById(R.id.textView_Description);
        imageView.setImageResource(IMAGES[i]);
        textView_Name.setText("Name: " + Names[i]);
        textView_Dec.setText("ID: " + IDs[i]);

        return view;
    }
}

}


