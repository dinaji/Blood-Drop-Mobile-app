package com.example.blooddrop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home2 extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home2);

        TextView dn = findViewById(R.id.donate);
        TextView re = findViewById(R.id.requst);
        TextView ba = findViewById(R.id.bank);


       dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(home2.this,Donor.class);
                startActivity(intent);

            }
        });


      re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(home2.this,Requestpage.class);
                startActivity(intent);

            }
        });



       ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(home2.this,bloodbank.class);
                startActivity(intent);

            }
        });
        // D.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View v) {

        // Intent intent = new Intent(home.this,Donor.class);
        // startActivity(intent);

        // }
        //   });

        // R1.setOnClickListener(new View.OnClickListener() {
        //  @Override
        // public void onClick(View v) {

        //  Intent intent = new Intent(home.this,Requestpage.class);
///startActivity(intent);

        //  }
        //});

        //  B1.setOnClickListener(new View.OnClickListener() {
        /// @Override
        // public void onClick(View v) {

        //  Intent intent = new Intent(home.this,bloodbank.class);
        //  startActivity(intent);
//
        //}
        //  });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}