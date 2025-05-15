package com.yash.women_safety_app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Setup bottom navigation
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                return true;
            } else if (itemId == R.id.navigation_map) {
                // TODO: Implement map navigation
                return true;
            } else if (itemId == R.id.navigation_community) {
                // TODO: Implement community navigation
                return true;
            } else if (itemId == R.id.navigation_emergency) {
                // Navigate to EmergencyActivity
                startActivity(new Intent(this, EmergencyActivity.class));
                return true;
            }
            return false;
        });
    }
} 