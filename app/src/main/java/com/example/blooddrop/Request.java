package com.example.blooddrop;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Request extends AppCompatActivity {


    EditText Rname, Rnic, Rlocation, Rbloodtype, Rage, Rnumber, Remail;
    Button rfile, rsubmit;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String fileUri = "";
    private static final int PICK_FILE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request);

        Rname = findViewById(R.id.editText18);
        Rnic = findViewById(R.id.editText19);
        Rlocation = findViewById(R.id.editText20);
        Rbloodtype = findViewById(R.id.editText21);
        Rage = findViewById(R.id.editText22);
        Rnumber = findViewById(R.id.editText23);
        Remail = findViewById(R.id.editText24);

        rfile=findViewById(R.id.selectFileButton);
        rsubmit=findViewById(R.id.button10);

        rfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();
            }
        });

        rsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Requests");

                String name = Rname.getText().toString();
                String nic = Rnic.getText().toString();
                String location = Rlocation.getText().toString();
                String bloodtype = Rbloodtype.getText().toString();
                String age = Rage.getText().toString();
                String mobile_number = Rnumber.getText().toString();
                String email = Remail.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Remail.setError("Email is required");
                    return;
                } else if (!email.contains("@")) {
                    Remail.setError("Email is not valid!");
                    return;
                }
                if (TextUtils.isEmpty(mobile_number) || mobile_number.length() < 10) {
                    Rnumber.setError("Invalid Mobile Number");
                    return;
                }
                if (TextUtils.isEmpty(nic)) {
                    Rnic.setError("NIC is required");
                    return;
                }
                RequestHelperClass request = new RequestHelperClass(name, email, nic, bloodtype, location, age, mobile_number, fileUri);
                reference.child(nic).setValue(request);
                Toast.makeText(Request.this, "Request Sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Request.this, HomePage.class);
                startActivity(intent);

            }
        });
    }
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select a file"), PICK_FILE_REQUEST);
    }


    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                String filePath = uri.getPath();
                TextView filePathTextView = findViewById(R.id.filePathTextView);
                filePathTextView.setText(filePath);
                Toast.makeText(this, "Selected file: " + filePath, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
