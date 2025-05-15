package com.yash.women_safety_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;

public class AddReportActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1002;
    private ImageButton backButton;
    private ImageButton imageButton;
    private ImageButton attachmentButton;
    private ImageButton emojiButton;
    private MaterialButton submitButton;
    private TextInputEditText titleInput;
    private TextInputEditText descriptionInput;
    private LinearLayout attachmentsContainer;
    private List<Uri> selectedFiles = new ArrayList<>();

    private final ActivityResultLauncher<Intent> filePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        selectedFiles.add(uri);
                        addFileToContainer(uri);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        imageButton = findViewById(R.id.imageButton);
        attachmentButton = findViewById(R.id.attachmentButton);
        emojiButton = findViewById(R.id.emojiButton);
        submitButton = findViewById(R.id.submitButton);
        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        attachmentsContainer = findViewById(R.id.attachmentsContainer);
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> onBackPressed());

        imageButton.setOnClickListener(v -> {
            // TODO: Handle image selection
        });

        attachmentButton.setOnClickListener(v -> {
            if (checkAndRequestPermissions()) {
                openFilePicker();
            }
        });

        emojiButton.setOnClickListener(v -> {
            // TODO: Show emoji picker
        });

        submitButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString().trim();
            String description = descriptionInput.getText().toString().trim();

            if (title.isEmpty()) {
                titleInput.setError("Title is required");
                return;
            }

            if (description.isEmpty()) {
                descriptionInput.setError("Description is required");
                return;
            }

            // TODO: Save report to database with attachments
            // For now, just go back to home screen
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
    }

    private boolean checkAndRequestPermissions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.READ_MEDIA_IMAGES,
                                Manifest.permission.READ_MEDIA_VIDEO,
                                Manifest.permission.READ_MEDIA_AUDIO
                        },
                        PERMISSION_REQUEST_CODE);
                return false;
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_CODE);
                return false;
            }
        }
        return true;
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        filePickerLauncher.launch(Intent.createChooser(intent, "Select Files"));
    }

    private void addFileToContainer(Uri uri) {
        View fileView = getLayoutInflater().inflate(R.layout.item_attachment, attachmentsContainer, false);
        TextView fileNameText = fileView.findViewById(R.id.fileNameText);
        ImageButton removeButton = fileView.findViewById(R.id.removeButton);

        String fileName = getFileName(uri);
        fileNameText.setText(fileName);

        removeButton.setOnClickListener(v -> {
            selectedFiles.remove(uri);
            attachmentsContainer.removeView(fileView);
        });

        attachmentsContainer.addView(fileView);
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (android.database.Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        result = cursor.getString(nameIndex);
                    }
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFilePicker();
            } else {
                Toast.makeText(this, "Permission denied. Cannot access files.", Toast.LENGTH_SHORT).show();
            }
        }
    }
} 