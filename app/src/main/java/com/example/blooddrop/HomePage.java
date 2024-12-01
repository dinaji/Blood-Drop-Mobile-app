package com.example.blooddrop;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;

import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.annotations.Nullable;

public class HomePage extends AppCompatActivity {

    Button loutbtn, logoutHeaderBtn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    TextView nametop;
    public static final String SHARED_PREFS = "sharedPrefs";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private TextView notificationBadge;
    private DatabaseReference notificationsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        HorizontalScrollView scrollView = findViewById(R.id.headerbar);
        int[] scrollPositions = {0, 500, 1000, 2500}; // Adjust based on content
        final int delay = 3000; // 3 seconds

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int currentIndex = 0;

            @Override
            public void run() {
                scrollView.smoothScrollTo(scrollPositions[currentIndex], 0);
                currentIndex = (currentIndex + 1) % scrollPositions.length;
                handler.postDelayed(this, delay);
            }
        };
        handler.postDelayed(runnable, delay);


        notificationBadge = findViewById(R.id.notification_badge);
        notificationsRef = FirebaseDatabase.getInstance().getReference("notifications");

        updateNotificationBadge();
        listenForNewNotifications();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ImageView requbtn = findViewById(R.id.rblood);
        LinearLayout dobtn = findViewById(R.id.dblood);
        LinearLayout bank = findViewById(R.id.bloodbank);
        LinearLayout events = findViewById(R.id.Events);
        LinearLayout settings = findViewById(R.id.Settings);
        LinearLayout notification = findViewById(R.id.recycler_view_notifications);
        //Button logoutbt = findViewById(R.id.button12);
        LinearLayout ll = findViewById(R.id.reqblood);
        TextView nametop = findViewById(R.id.textusername);
        CardView before = findViewById(R.id.beforedonation);
        CardView on = findViewById(R.id.indonation);
        CardView after = findViewById(R.id.afterdonation);

        before.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, BeforeDonation.class);
                startActivity(intent);
            }
        });
        on.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, OndayDonation.class);
                startActivity(intent);
            }
        });
        after.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, AfterDonation.class);
                startActivity(intent);
            }
        });


        String username = getIntent().getStringExtra("username");
        nametop.setText(username);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "BloodDropChannel",
                    "BloodDrop Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        notificationBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the user clicks the badge or views the notifications
                markNotificationAsRead();

                // Navigate to the Notifications page or wherever necessary
                Intent intent = new Intent(HomePage.this, Notifications.class);
                startActivity(intent);

            }

        });
        //simulateNewNotifications();

        notification.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Notifications.class);
                startActivity(intent);
            }
        });

        requbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this, Request.class);
                startActivity(intent);

            }
        });

        dobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this, Donor.class);
                startActivity(intent);

            }
        });

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Request.class);
                startActivity(intent);
            }
        });


        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this, bloodbank.class);
                startActivity(intent);

            }
        });

        events.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Events.class);
                startActivity(intent);
            }
        });
        /*menuIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });*/
        DatabaseReference databaseReference = database.getReference("notifications");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    // Fetch message, timestamp, and type from Firebase
                    String message = data.child("message").getValue(String.class);
                    String timestamp = data.child("timestamp").getValue(String.class);
                    String type = data.child("type").getValue(String.class);

                    // Show the notification
                    if (type != null && message != null) {
                        showNotification("Notification - " + type, message);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePage.this, "Failed to load notifications", Toast.LENGTH_SHORT).show();
            }
        });


        settings.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Settings.class);
                startActivity(intent);
            }
        });


        notificationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int unreadCount = 0;

                // Count unread notifications
                for (DataSnapshot data : snapshot.getChildren()) {
                    Boolean isRead = data.child("isRead").getValue(Boolean.class);
                    // Consider unread notifications as those where isRead is false or null
                    if (isRead == null || !isRead) {
                        unreadCount++;
                    }
                }


                // Update the badge
                if (unreadCount > 0) {
                    notificationBadge.setVisibility(View.VISIBLE);
                    notificationBadge.setText(String.valueOf(unreadCount));
                } else {
                    notificationBadge.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePage.this, "Failed to load notifications", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void listenForNewNotifications() {
        notificationsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // A new message has arrived, increment unread count
                int unreadCount = getUnreadCount();
                unreadCount++;  // Increment for the new unread notification
                saveUnreadCount(unreadCount);  // Save the new count
                updateNotificationBadge();  // Update the badge count

                // Optionally, you can display a notification to the user
                String message = snapshot.child("message").getValue(String.class);
                String type = snapshot.child("type").getValue(String.class);
                if (message != null && type != null) {
                    showNotification("New Notification - " + type, message);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Handle any updates to notifications (e.g., marking read)
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // Handle notification removal (if necessary)
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Handle any moved notifications (if necessary)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePage.this, "Failed to load notifications", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // SharedPreferences Helper Methods
    private void saveUnreadCount(int count) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("unreadCount", count);  // Store the unread count
        editor.apply();
    }

    private int getUnreadCount() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getInt("unreadCount", 0);  // Default is 0 if no unread notifications
    }

    private void updateNotificationBadge() {
        int unreadCount = getUnreadCount();

        if (unreadCount > 0) {
            notificationBadge.setVisibility(View.VISIBLE);
            notificationBadge.setText(String.valueOf(unreadCount));
        } else {
            notificationBadge.setVisibility(View.GONE);
        }
    }

    private void markNotificationAsRead() {
        // Get current unread count
        int unreadCount = getUnreadCount();

        if (unreadCount > 0) {
            unreadCount--;  // Decrease count when the user reads the notification
            saveUnreadCount(unreadCount);  // Save the new count
            updateNotificationBadge();  // Update badge
        }
    }

    @SuppressLint("MissingPermission")



    private void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "BloodDropChannel")
                .setSmallIcon(R.drawable.baseline_notifications_24) // Replace with your app icon
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}



