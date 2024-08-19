package com.example.pethelp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pethelp.databinding.ActivityAddFoodBinding;

public class AddFood extends AppCompatActivity {

    private ActivityAddFoodBinding binding;
    private AddFoodDBOpenHelper addFoodDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize database helper
        addFoodDBOpenHelper = new AddFoodDBOpenHelper(this);

        // Set button click listeners
        binding.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFood.this, SeeProducts.class);
                startActivity(intent);
            }
        });

        binding.btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(v);
            }
        });
    }

    public void addData(View v) {
        SQLiteDatabase db = addFoodDBOpenHelper.getWritableDatabase();

        EditText studentid = findViewById(R.id.studentid);
        EditText s1 = findViewById(R.id.s1);
        EditText s2 = findViewById(R.id.s2);
        EditText s3 = findViewById(R.id.s3);

        String studentIdValue = studentid.getText().toString();
        String s1Text = s1.getText().toString();
        String s2Text = s2.getText().toString();
        String s3Text = s3.getText().toString();

        if (studentIdValue.isEmpty() || s1Text.isEmpty() || s2Text.isEmpty() || s3Text.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
            return;
        }

        addFoodDBOpenHelper.insertFood(db, studentIdValue, s1Text, s2Text, s3Text);

        Toast.makeText(this, "Record Added Successfully", Toast.LENGTH_LONG).show();
        db.close();
    }

}
