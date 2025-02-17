package com.example.pethelp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pethelp.databinding.ActivityUserProfileBinding;

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "UserProfileActivity";
    private DatabaseHelper dbh;
    private EditText txtEmail;
    private EditText txtPassword;
    private String loggedInUserEmail;
    private String loggedInUserPassword;
    ActivityUserProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize views
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        ImageView profile = findViewById(R.id.footerProfileBtn);
        ImageView logout = findViewById(R.id.footerCartBtn); // Assuming logout button is in footer
        ImageView welcomeAddBtn = findViewById(R.id.footerWelcomeBtn);
        ImageView welcomePetfoodBtn = findViewById(R.id.footerFoodBtn);
        ImageView welcomeArticle = findViewById(R.id.footerProfileBtn); // Update with actual ID if different
        ImageView footerFoodBtn = findViewById(R.id.footerFoodBtn);
        ImageView footerCartBtn = findViewById(R.id.footerCartBtn);

        dbh = new DatabaseHelper(this);

        Intent intent = getIntent();
        loggedInUserEmail = intent.getStringExtra("USER_EMAIL");
        loggedInUserPassword = intent.getStringExtra("USER_PASSWORD");

        if (loggedInUserEmail != null && loggedInUserPassword != null) {
            Log.d(TAG, "Logged in user email: " + loggedInUserEmail);
            txtEmail.setText(loggedInUserEmail);
            txtPassword.setText(loggedInUserPassword);
        } else {
            Log.e(TAG, "No email or password found in the intent.");
        }

        // Set up the update button click listener
        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });

        // Set up the profile button click listener
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });


        // Set up the welcome add pet button click listener
        welcomeAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Set up the welcome pet food button click listener
        welcomePetfoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, Productlist.class);
                startActivity(intent);
            }
        });

        // Set up the welcome article button click listener
        welcomeArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, articleTypeActivity.class);
                startActivity(intent);
            }
        });

        // Set up the footer food button click listener
        footerFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, Productlist.class);
                startActivity(intent);
            }
        });

        // Set up the footer cart button click listener
        footerCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, Cart.class);
                startActivity(intent);
            }
        });
    }

    public void update(View v) {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        SQLiteDatabase db = dbh.getWritableDatabase();

        try {
            if (dbh.updateUser(db, loggedInUserEmail, email, password)) {
                Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show();
                loggedInUserEmail = email;
            } else {
                Toast.makeText(this, "Failed to update record", Toast.LENGTH_SHORT).show();
            }
        } finally {
            db.close();
        }
    }
}
