package com.example.blooddrop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userlogin extends AppCompatActivity {
    private EditText usernameField, passwordField;
    private Button logbtn ;
    private TextView fogbtn,signbtn;
    private DatabaseReference databaseReference;

    public static  final String SHARED_PREFS  = "sharedPrefs";
  //  private EditText D1;
   // private Button login, register;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userlogin);


        usernameField = findViewById(R.id.editTextText);
        passwordField = findViewById(R.id.editTextText2);
        logbtn = findViewById(R.id.button5);
        fogbtn = findViewById(R.id.textView5);
        signbtn = findViewById(R.id.register);

        fogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), forgetpassword.class);
                startActivity(intent);
                finish();
            }
        });

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
                finish();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("App Users");

        checkBox();

        logbtn.setOnClickListener(v -> {
            String email = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            loginUser(email, password);
        });

    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false); // Default is false

        if (isLoggedIn) {
            // If the user is logged in, show success and go to the HomePage
            Toast.makeText(userlogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);
            finish();
        }
    }


    private void loginUser(final String email, final String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if (TextUtils.isEmpty(email)) {
            usernameField.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Password is required");
            return;
        }

        // Sign in with email and password using FirebaseAuth
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Login successful
                        Toast.makeText(userlogin.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        // Save login state using SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", true);  // Save login status as true
                        editor.putString("email", email); // Save the logged-in email
                        editor.apply();

                        // Redirect to the Home Page
                        Intent intent = new Intent(userlogin.this, HomePage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Handle errors
                        String errorMessage = task.getException().getMessage();
                        Toast.makeText(userlogin.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });

    }




}
