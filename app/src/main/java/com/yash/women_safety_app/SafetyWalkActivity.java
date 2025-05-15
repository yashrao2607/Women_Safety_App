package com.yash.women_safety_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class SafetyWalkActivity extends AppCompatActivity {
    private static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 1003;
    private static final String MUMMY_PHONE_NUMBER = "+917374084224";

    private ImageButton backButton;
    private MaterialButton createRequestButton;
    private MaterialButton acceptRequestButton;
    private BottomNavigationView bottomNavigation;
    private LinearLayout mummyContactLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_walk);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        createRequestButton = findViewById(R.id.createRequestButton);
        acceptRequestButton = findViewById(R.id.acceptRequestButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        mummyContactLayout = findViewById(R.id.mummyContactLayout);
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());

        // Mummy contact click listener
        mummyContactLayout.setOnClickListener(v -> {
            if (checkCallPermission()) {
                makePhoneCall();
            }
        });

        // Create request button click listener
        createRequestButton.setOnClickListener(v -> {
            // TODO: Implement create request functionality
        });

        // Accept request button click listener
        acceptRequestButton.setOnClickListener(v -> {
            // TODO: Implement accept request functionality
        });

        // Bottom navigation item selected listener
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (itemId == R.id.navigation_map) {
                startActivity(new Intent(this, MapActivity.class));
                return true;
            } else if (itemId == R.id.navigation_community) {
                startActivity(new Intent(this, CommunityActivity.class));
                return true;
            } else if (itemId == R.id.navigation_emergency) {
                startActivity(new Intent(this, EmergencyActivity.class));
                return true;
            }
            return false;
        });
    }

    private boolean checkCallPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    CALL_PHONE_PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    private void makePhoneCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + MUMMY_PHONE_NUMBER));
        try {
            startActivity(callIntent);
        } catch (SecurityException e) {
            Toast.makeText(this, "Call permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Call permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
} 