package com.example.blooddrop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    EditText Name, Email, Username, Password, Nic, Age, City, Mobilenumber;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Name = findViewById(R.id.editText);
        Email = findViewById(R.id.editText2);
        Username = findViewById(R.id.editText3);
        Password = findViewById(R.id.editText4);
        Nic = findViewById(R.id.editText5);
        Age = findViewById(R.id.editText6);
        City = findViewById(R.id.editText7);
        Mobilenumber = findViewById(R.id.editText8);
        signup = findViewById(R.id.button7);

        firebaseAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                String nic = Nic.getText().toString();
                String age = Age.getText().toString();
                String city = City.getText().toString();
                String mobile_number = Mobilenumber.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is required");
                    return;
                } else if (!email.contains("@")) {
                    Email.setError("Invalid email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password is required");
                    return;
                } else if (password.length() < 7) {
                    Password.setError("Password must be at least 7 characters long");
                    return;
                }
                if (TextUtils.isEmpty(mobile_number)) {
                    Mobilenumber.setError("Mobile Number is required");
                    return;
                } else if (mobile_number.length() < 10) {
                    Mobilenumber.setError("Invalid Mobile Number");
                    return;
                }

                // Create user in Firebase Authentication
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // User successfully registered
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                                EmailSender emailSender = new EmailSender();
                                emailSender.sendEmail(
                                        email,
                                        "Welcome to BloodDrop",
                                        "Dear " + name + ",\n\nWelcome to BloodDrop! We're excited to have you on board.\n\nBest Regards,\nBloodDrop Team"
                                );

                                // Store additional details in Firebase Realtime Database
                                rootNode = FirebaseDatabase.getInstance();
                                reference = rootNode.getReference("App Users");

                                usersHelperClass helperClass = new usersHelperClass(name, email, username, nic, age, city, mobile_number);
                                assert firebaseUser != null;
                                reference.child(firebaseUser.getUid()).setValue(helperClass);

                                Toast.makeText(signup.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signup.this, userlogin.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Registration failed
                                Toast.makeText(signup.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
