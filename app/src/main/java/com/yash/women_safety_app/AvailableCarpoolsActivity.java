package com.yash.women_safety_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AvailableCarpoolsActivity extends AppCompatActivity {

    private static final String TAG = "AvailableCarpoolsActivity";
    private ImageButton backButton;
    private CardView bhavyaCarpool, ankitaCarpool, anshikaCarpool, manjulikaCarpool;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_available_carpools);

            initializeViews();
            setupClickListeners();
            setupBottomNavigation();
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        bhavyaCarpool = findViewById(R.id.bhavyaCarpool);
        ankitaCarpool = findViewById(R.id.ankitaCarpool);
        anshikaCarpool = findViewById(R.id.anshikaCarpool);
        manjulikaCarpool = findViewById(R.id.manjulikaCarpool);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupClickListeners() {
        try {
            if (backButton != null) {
                backButton.setOnClickListener(v -> {
                    try {
                        Intent homeIntent = new Intent(AvailableCarpoolsActivity.this, HomeActivity.class);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(homeIntent);
                        finish();
                    } catch (Exception e) {
                        Log.e(TAG, "Error in back button click: " + e.getMessage());
                        e.printStackTrace();
                        finish();
                    }
                });
            }

            // Set up carpool card click listeners
            if (bhavyaCarpool != null) {
                bhavyaCarpool.setOnClickListener(v -> {
                    // TODO: Navigate to Bhavya's carpool details
                });
            }

            if (ankitaCarpool != null) {
                ankitaCarpool.setOnClickListener(v -> {
                    // TODO: Navigate to Ankita's carpool details
                });
            }

            if (anshikaCarpool != null) {
                anshikaCarpool.setOnClickListener(v -> {
                    // TODO: Navigate to Anshika's carpool details
                });
            }

            if (manjulikaCarpool != null) {
                manjulikaCarpool.setOnClickListener(v -> {
                    // TODO: Navigate to Manjulika's carpool details
                });
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up click listeners: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void setupBottomNavigation() {
        try {
            if (bottomNavigation != null) {
                bottomNavigation.setOnItemSelectedListener(item -> {
                    int itemId = item.getItemId();
                    try {
                        if (itemId == R.id.navigation_home) {
                            Intent homeIntent = new Intent(this, HomeActivity.class);
                            startActivity(homeIntent);
                            finish();
                            return true;
                        } else if (itemId == R.id.navigation_map) {
                            Intent mapIntent = new Intent(this, MapActivity.class);
                            startActivity(mapIntent);
                            finish();
                            return true;
                        } else if (itemId == R.id.navigation_community) {
                            Intent communityIntent = new Intent(this, CommunityActivity.class);
                            startActivity(communityIntent);
                            finish();
                            return true;
                        } else if (itemId == R.id.navigation_emergency) {
                            // TODO: Navigate to emergency screen
                            return true;
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Error in navigation: " + e.getMessage());
                        e.printStackTrace();
                    }
                    return false;
                });
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up bottom navigation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        try {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(homeIntent);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Error in onBackPressed: " + e.getMessage());
            e.printStackTrace();
            finish();
        }
    }
} 