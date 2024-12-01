package com.example.blooddrop;

//import static com.example.blooddrop.userlogin.SHARED_PREFS;

import static com.example.blooddrop.HomePage.SHARED_PREFS;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class sidebar extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle navigation item clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("Navigation", "Item clicked: ${item.itemId}");
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    Toast.makeText(sidebar.this, "Home selected", Toast.LENGTH_SHORT).show();
                    // Handle Home action
                } else if (itemId == R.id.nav_contact) {
                    Toast.makeText(sidebar.this, "Privacy Policy selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_privacy) {
                    Toast.makeText(sidebar.this, "Log Out selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_logout) {
                    handleLogout();
                }
                return true;
            }

        });


    }
    private void handleLogout() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", "");
        editor.apply();

        Intent intent = new Intent(getApplicationContext(), userlogin.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
