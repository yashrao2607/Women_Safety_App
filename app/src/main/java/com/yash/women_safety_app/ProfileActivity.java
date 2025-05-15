package com.yash.women_safety_app;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1005;

    private ImageButton backButton;
    private ImageButton settingsButton;
    private ImageView profileImage;
    private MaterialButton faqButton;
    private MaterialButton contactButton;
    private BottomNavigationView bottomNavigation;
    private LinearLayout editProfileLayout;
    private TextView userNameText;
    private TextView userLocationText;

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        profileImage.setImageURI(selectedImageUri);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        settingsButton = findViewById(R.id.settingsButton);
        profileImage = findViewById(R.id.profileImage);
        faqButton = findViewById(R.id.faqButton);
        contactButton = findViewById(R.id.contactButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        editProfileLayout = findViewById(R.id.editProfileLayout);
        userNameText = findViewById(R.id.userNameText);
        userLocationText = findViewById(R.id.userLocationText);
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> onBackPressed());

        settingsButton.setOnClickListener(v -> {
            // TODO: Open settings screen
        });

        editProfileLayout.setOnClickListener(v -> {
            if (checkStoragePermission()) {
                openImagePicker();
            }
        });

        profileImage.setOnClickListener(v -> {
            if (checkStoragePermission()) {
                openImagePicker();
            }
        });

        userNameText.setOnClickListener(v -> showEditDialog("Edit Name", userNameText.getText().toString(), 
            newText -> userNameText.setText(newText)));

        userLocationText.setOnClickListener(v -> showEditDialog("Edit Location", userLocationText.getText().toString(), 
            newText -> userLocationText.setText(newText)));

        faqButton.setOnClickListener(v -> {
            // TODO: Open FAQ screen
        });

        contactButton.setOnClickListener(v -> {
            // TODO: Open contact screen
        });

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

    private void showEditDialog(String title, String currentText, OnTextEditedListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        final EditText input = new EditText(this);
        input.setText(currentText);
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newText = input.getText().toString().trim();
            if (!newText.isEmpty()) {
                listener.onTextEdited(newText);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private boolean checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // For Android 13 and above
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                        STORAGE_PERMISSION_REQUEST_CODE);
                return false;
            }
        } else {
            // For Android 12 and below
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_REQUEST_CODE);
                return false;
            }
        }
        return true;
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(this, "Storage permission is required to change profile picture", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private interface OnTextEditedListener {
        void onTextEdited(String newText);
    }
} 