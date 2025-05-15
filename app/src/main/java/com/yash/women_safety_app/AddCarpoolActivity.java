package com.yash.women_safety_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddCarpoolActivity extends AppCompatActivity {
    private ImageButton backButton;
    private MaterialButton submitButton;
    private TextInputEditText fromInput;
    private TextInputEditText toInput;
    private TextInputEditText timeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_carpool);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        backButton = findViewById(R.id.backButton);
        submitButton = findViewById(R.id.submitButton);
        fromInput = findViewById(R.id.fromInput);
        toInput = findViewById(R.id.toInput);
        timeInput = findViewById(R.id.timeInput);
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> onBackPressed());

        submitButton.setOnClickListener(v -> {
            String from = fromInput.getText().toString().trim();
            String to = toInput.getText().toString().trim();
            String time = timeInput.getText().toString().trim();

            if (from.isEmpty()) {
                fromInput.setError("From location is required");
                return;
            }

            if (to.isEmpty()) {
                toInput.setError("To location is required");
                return;
            }

            if (time.isEmpty()) {
                timeInput.setError("Time is required");
                return;
            }

            // TODO: Save carpool to database
            // For now, just go back to home screen
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
    }
} 