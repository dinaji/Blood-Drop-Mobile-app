package com.example.blooddrop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSendReset;
    private TextView tvBackToLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        etEmail = findViewById(R.id.etEmail);
        btnSendReset = findViewById(R.id.btnSendReset);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        mAuth = FirebaseAuth.getInstance();

        // Back to Login
        tvBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(forgetpassword.this, userlogin.class);
            startActivity(intent);
            finish();
        });

        // Send Reset Link
        btnSendReset.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email is required");
                return;
            }

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(forgetpassword.this,
                                    "Password reset email sent. Check your inbox.",
                                    Toast.LENGTH_LONG).show();

                            // Optionally redirect to login page
                            Intent intent = new Intent(forgetpassword.this, userlogin.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(forgetpassword.this,
                                    "Error: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}
