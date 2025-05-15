package com.yash.women_safety_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    private CardView[] incidentCards;
    private CardView[] featureCards;
    private FloatingActionButton addReportButton;
    private BottomNavigationView bottomNavigation;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeViews();
        setupIncidentCards();
        setupFeatureCards();
        setupClickListeners();
    }

    private void initializeViews() {
        incidentCards = new CardView[]{
            findViewById(R.id.incidentCard1),
            findViewById(R.id.incidentCard2),
            findViewById(R.id.incidentCard3),
            findViewById(R.id.incidentCard4)
        };

        featureCards = new CardView[]{
            findViewById(R.id.featureCard1),
            findViewById(R.id.featureCard2),
            findViewById(R.id.featureCard3),
            findViewById(R.id.featureCard4)
        };

        addReportButton = findViewById(R.id.addReportButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        profileImage = findViewById(R.id.profileImage);
    }

    private void setupIncidentCards() {
        // Set different background colors for incident cards
        int[] backgroundColors = {
            getResources().getColor(R.color.pink),
            getResources().getColor(R.color.dark_blue),
            getResources().getColor(R.color.pink),
            getResources().getColor(R.color.dark_blue)
        };

        for (int i = 0; i < incidentCards.length; i++) {
            incidentCards[i].setCardBackgroundColor(backgroundColors[i]);
        }
    }

    private void setupFeatureCards() {
        String[] featureNames = {
            "Available Carpools",
            "Add Your Carpool",
            "Women Safety Events",
            "Safety Walk"
        };

        for (int i = 0; i < featureCards.length; i++) {
            TextView featureText = featureCards[i].findViewById(R.id.featureText);
            featureText.setText(featureNames[i]);

            // Add click listener for Available Carpools card
            if (i == 0) { // Index 0 is "Available Carpools"
                featureCards[i].setOnClickListener(v -> {
                    Intent intent = new Intent(this, AvailableCarpoolsActivity.class);
                    startActivity(intent);
                });
            }

            // Add click listener for Add Your Carpool card
            if (i == 1) { // Index 1 is "Add Your Carpool"
                featureCards[i].setOnClickListener(v -> {
                    Intent intent = new Intent(this, AddCarpoolActivity.class);
                    startActivity(intent);
                });
            }

            // Add click listener for Women Safety Events card
            if (i == 2) { // Index 2 is "Women Safety Events"
                featureCards[i].setOnClickListener(v -> {
                    Intent intent = new Intent(this, SafetyEventsActivity.class);
                    startActivity(intent);
                });
            }

            // Add click listener for Safety Walk card
            if (i == 3) { // Index 3 is "Safety Walk"
                featureCards[i].setOnClickListener(v -> {
                    Intent intent = new Intent(this, SafetyWalkActivity.class);
                    startActivity(intent);
                });
            }
        }
    }

    private void setupClickListeners() {
        addReportButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddReportActivity.class);
            startActivity(intent);
        });
        
        profileImage.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                // Already on home screen
                return true;
            } else if (itemId == R.id.navigation_map) {
                Intent mapIntent = new Intent(this, MapActivity.class);
                mapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(mapIntent);
                return true;
            } else if (itemId == R.id.navigation_community) {
                Intent communityIntent = new Intent(this, CommunityActivity.class);
                startActivity(communityIntent);
                return true;
            } else if (itemId == R.id.navigation_emergency) {
                Intent emergencyIntent = new Intent(this, EmergencyActivity.class);
                emergencyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(emergencyIntent);
                return true;
            }
            return false;
        });

        // Set up notification icon click
        ImageView notificationIcon = findViewById(R.id.notificationIcon);
        notificationIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationsActivity.class);
            startActivity(intent);
        });
    }
} 