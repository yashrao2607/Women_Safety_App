package com.yash.women_safety_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText confirmPasswordEditText;
    private MaterialButton signUpButton;
    private MaterialButton googleButton;
    private MaterialButton facebookButton;
    private MaterialButton appleButton;
    private TextView loginText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        googleButton = findViewById(R.id.googleButton);
        facebookButton = findViewById(R.id.facebookButton);
        appleButton = findViewById(R.id.appleButton);
        loginText = findViewById(R.id.loginText);
        // Set click listeners
        signUpButton.setOnClickListener(v -> handleSignUp());
        googleButton.setOnClickListener(v -> handleGoogleSignIn());
        facebookButton.setOnClickListener(v -> handleFacebookSignIn());
        appleButton.setOnClickListener(v -> handleAppleSignIn());
        loginText.setOnClickListener(v -> finish()); // Go back to login screen
    }

    private void handleSignUp() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Basic validation
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return;
        }

        signUpButton.setEnabled(false);
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                signUpButton.setEnabled(true);
                if (task.isSuccessful()) {
                    // Registration successful, go to HomeActivity
                    startActivity(new Intent(this, HomeActivity.class));
                    finish();
                } else {
                    String errorMsg = (task.getException() != null) ? task.getException().getMessage() : "Unknown error";
                    passwordEditText.setError(errorMsg);
                }
            });
    }

    private void handleGoogleSignIn() {
        // TODO: Implement Google Sign-In
    }

    private void handleFacebookSignIn() {
        // TODO: Implement Facebook Sign-In
    }

    private void handleAppleSignIn() {
        // TODO: Implement Apple Sign-In
    }
} 