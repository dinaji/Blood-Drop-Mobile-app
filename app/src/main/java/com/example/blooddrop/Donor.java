package com.example.blooddrop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donor extends AppCompatActivity {


    EditText Name,NIC, Location, BloodType, Email,Age,DonateDate,PhoneNumber,LastDonatedDate;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donor);

        Name= findViewById(R.id.editText18);
        Email= findViewById(R.id.editText25);
        NIC= findViewById(R.id.editText19);
        Location= findViewById(R.id.editText20);
        BloodType= findViewById(R.id.editText21);
        Age= findViewById(R.id.editText22);
        DonateDate= findViewById(R.id.editText26);
        PhoneNumber= findViewById(R.id.editText24);
        LastDonatedDate= findViewById(R.id.editText23);
        submit=findViewById(R.id.button10);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("AppDonors");

            String name = Name.getText().toString();
            String email = Email.getText().toString();
            String nic = NIC.getText().toString();
            String location = Location.getText().toString();
            String bloodtype = BloodType.getText().toString();
            String age = Age.getText().toString();
            String donatedate = DonateDate.getText().toString();
            String mobile_number = PhoneNumber.getText().toString();
            String Ldonatedate = LastDonatedDate.getText().toString();

            if (TextUtils.isEmpty(Email.getText().toString())) {
                Email.setError("Email is required");
                return;
            } else if (!email.contains("@")) {
                Email.setError("Email is not valid!");
                return;
            }

            if (TextUtils.isEmpty(PhoneNumber.getText().toString())) {
                PhoneNumber.setError("Mobile Number is required");
                return;
            } else if (mobile_number.isEmpty() || mobile_number.length() < 10) {

                PhoneNumber.setError("Invalid Mobile Number");
                return;
            }
            if (TextUtils.isEmpty(NIC.getText().toString())) {
                NIC.setError("NIC is required");
                return;
            } else if (nic.isEmpty()) {
                NIC.setError("Nic is not valid!");
                return;
            }

            UserHelperClass1 helperClass = new UserHelperClass1(name, email, nic,location, bloodtype, age, donatedate, mobile_number, Ldonatedate);
            //    usersHelperClass usersHelperClass = new usersHelperClass();

            reference.child(nic).setValue(helperClass);

            Toast.makeText(Donor.this, "Application Sent", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Donor.this, HomePage.class);
            startActivity(intent);

        }
        });
    }
}