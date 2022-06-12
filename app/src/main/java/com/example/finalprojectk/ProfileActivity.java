package com.example.finalprojectk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalprojectk.database.Database;

public class ProfileActivity extends AppCompatActivity {
    TextView profileName, profileEmail, profilePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        initVar();
    }

    void initView(){
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profilePhone = findViewById(R.id.profilePhone);
    }

    void initVar(){
        String email = "Email : " + Database.userLog.getUserEmail();
        String phone = "Phone : " + Database.userLog.getUserPhoneNumber();
        profileName.setText(Database.userLog.getUserUsername());
        profileEmail.setText(email);
        profilePhone.setText(phone);
    }
}