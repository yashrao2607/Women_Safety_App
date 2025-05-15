package com.yash.women_safety_app;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapActivity extends AppCompatActivity {

    private ImageButton backButton;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> finish());
    }
} 