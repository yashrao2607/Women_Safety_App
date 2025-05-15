package com.yash.women_safety_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private MaterialButton signInButton;
    private TextView createAccountText;
    private ImageButton googleSignInButton;
    private ImageButton facebookSignInButton;
    private ImageButton appleSignInButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        signInButton = findViewById(R.id.signInButton);
        createAccountText = findViewById(R.id.createAccountText);
        googleSignInButton = findViewById(R.id.googleSignInButton);
        facebookSignInButton = findViewById(R.id.facebookSignInButton);
        appleSignInButton = findViewById(R.id.appleSignInButton);
    }

    private void setupClickListeners() {
        signInButton.setOnClickListener(v -> handleSignIn());
        createAccountText.setOnClickListener(v -> handleCreateAccount());
        googleSignInButton.setOnClickListener(v -> handleGoogleSignIn());
        facebookSignInButton.setOnClickListener(v -> handleFacebookSignIn());
        appleSignInButton.setOnClickListener(v -> handleAppleSignIn());
    }

    private void handleSignIn() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        signInButton.setEnabled(false);
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                signInButton.setEnabled(true);
                if (task.isSuccessful()) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Authentication failed: " + (task.getException() != null ? task.getException().getMessage() : "Unknown error"), Toast.LENGTH_LONG).show();
                }
            });
    }

    private void handleCreateAccount() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void handleGoogleSignIn() {
        // TODO: Implement Google Sign In
        Toast.makeText(this, "Google sign in clicked", Toast.LENGTH_SHORT).show();
    }

    private void handleFacebookSignIn() {
        // TODO: Implement Facebook Sign In
        Toast.makeText(this, "Facebook sign in clicked", Toast.LENGTH_SHORT).show();
    }

    private void handleAppleSignIn() {
        // TODO: Implement Apple Sign In
        Toast.makeText(this, "Apple sign in clicked", Toast.LENGTH_SHORT).show();
    }
} 