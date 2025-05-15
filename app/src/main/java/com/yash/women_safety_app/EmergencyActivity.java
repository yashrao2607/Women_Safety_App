package com.yash.women_safety_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EmergencyActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private static final int SMS_PERMISSION_REQUEST_CODE = 1002;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private TextView locationCoordinatesText;
    private Location currentLocation;
    private final String[] emergencyNumbers = {
        "8053435873",
        "7374084224",
        "7428314311",
        "9728062278"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        locationCoordinatesText = findViewById(R.id.locationCoordinatesText);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Setup location callback
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null) {
                    currentLocation = locationResult.getLastLocation();
                    updateLocationUI(currentLocation);
                }
            }
        };

        // Check and request location permissions
        if (checkLocationPermission()) {
            startLocationUpdates();
        } else {
            requestLocationPermission();
        }

        // Setup SOS button click listener
        FrameLayout sosButton = findViewById(R.id.sosButtonContainer);
        sosButton.setOnClickListener(v -> {
            if (currentLocation != null) {
                if (checkSmsPermission()) {
                    sendEmergencySMS();
                } else {
                    requestSmsPermission();
                }
            } else {
                Toast.makeText(this, "Location not available. Please wait...", Toast.LENGTH_SHORT).show();
            }
        });

        // Setup bottom navigation
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.navigation_map) {
                Intent intent = new Intent(this, MapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.navigation_community) {
                Intent intent = new Intent(this, CommunityActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.navigation_emergency) {
                return true;
            }
            return false;
        });

        // Setup back button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkSmsPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }

    private void requestSmsPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS},
                SMS_PERMISSION_REQUEST_CODE);
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000) // Update every second
                .setFastestInterval(500);

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    private void updateLocationUI(Location location) {
        if (location != null) {
            String coordinates = String.format("Latitude: %.6f\nLongitude: %.6f",
                    location.getLatitude(), location.getLongitude());
            locationCoordinatesText.setText(coordinates);
        }
    }

    private void sendEmergencySMS() {
        String message = String.format("EMERGENCY! I need help! My current location is: Latitude: %.6f, Longitude: %.6f",
                currentLocation.getLatitude(), currentLocation.getLongitude());
        
        SmsManager smsManager = SmsManager.getDefault();
        for (String number : emergencyNumbers) {
            smsManager.sendTextMessage(number, null, message, null, null);
        }
        
        Toast.makeText(this, "Emergency SMS sent to contacts!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            }
        } else if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (currentLocation != null) {
                    sendEmergencySMS();
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkLocationPermission()) {
            startLocationUpdates();
        }
    }
} 