package com.yash.women_safety_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SafetyEventsActivity extends AppCompatActivity {
    private ImageButton backButton;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_events);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> onBackPressed());

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.navigation_map) {
                // TODO: Navigate to map screen
                return true;
            } else if (itemId == R.id.navigation_community) {
                // TODO: Navigate to community screen
                return true;
            } else if (itemId == R.id.navigation_emergency) {
                // TODO: Navigate to emergency screen
                return true;
            }
            return false;
        });
    }
} 