package com.yash.women_safety_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CommunityActivity extends AppCompatActivity {

    private static final String TAG = "CommunityActivity";
    private ImageButton backButton;
    private ImageButton profileImage;
    private TextView notificationBadge;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private CommunityPagerAdapter pagerAdapter;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_community);

            // Initialize views
            initializeViews();
            
            // Set up ViewPager2 with adapter
            setupViewPager();
            
            // Set up bottom navigation
            setupBottomNavigation();
            
            // Set click listeners
            setupClickListeners();
            
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        profileImage = findViewById(R.id.profileImage);
        notificationBadge = findViewById(R.id.notificationBadge);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupViewPager() {
        try {
            pagerAdapter = new CommunityPagerAdapter(this);
            viewPager.setAdapter(pagerAdapter);

            // Connect TabLayout with ViewPager2
            new TabLayoutMediator(tabLayout, viewPager,
                    (tab, position) -> {
                        switch (position) {
                            case 0:
                                tab.setText("Popular");
                                break;
                            case 1:
                                tab.setText("You");
                                break;
                            case 2:
                                tab.setText("Clubs");
                                break;
                        }
                    }).attach();
        } catch (Exception e) {
            Log.e(TAG, "Error setting up ViewPager: " + e.getMessage());
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
                            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(homeIntent);
                            finish();
                            return true;
                        } else if (itemId == R.id.navigation_map) {
                            Intent mapIntent = new Intent(this, MapActivity.class);
                            mapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(mapIntent);
                            finish();
                            return true;
                        } else if (itemId == R.id.navigation_community) {
                            // Already on community screen
                            return true;
                        } else if (itemId == R.id.navigation_emergency) {
                            Intent emergencyIntent = new Intent(this, EmergencyActivity.class);
                            emergencyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(emergencyIntent);
                            finish();
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

    private void setupClickListeners() {
        try {
            if (backButton != null) {
                backButton.setOnClickListener(v -> {
                    Intent intent = new Intent(CommunityActivity.this, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                });
            }
            
            if (profileImage != null) {
                profileImage.setOnClickListener(v -> {
                    // TODO: Handle profile click
                });
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up click listeners: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
} 