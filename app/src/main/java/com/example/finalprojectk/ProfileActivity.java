package com.example.finalprojectk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectk.database.Database;
import com.example.finalprojectk.database.DatabaseHelper;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    TextView profileEmail, profilePhone;
    EditText profileName;
    ImageButton btnEdit, btnDelete;
    Button btnSave, btnOut;
    DatabaseHelper ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        initVar();
        ph = new DatabaseHelper(this);
        btnSave.setVisibility(View.INVISIBLE);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnOut.setOnClickListener(this);
    }

    void initView(){
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profilePhone = findViewById(R.id.profilePhone);
        btnSave = findViewById(R.id.btnSave);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnOut = findViewById(R.id.btnOut);
    }

    void initVar(){
        String email = "Email : " + Database.userLog.getUserEmail();
        String phone = "Phone : " + Database.userLog.getUserPhoneNumber();
        profileName.setText(Database.userLog.getUserUsername());
        profileEmail.setText(email);
        profilePhone.setText(phone);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:

                break;
            case R.id.btnEdit:
                profileName.requestFocus();
                break;
            case R.id.btnDelete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("Delete your account ?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            Boolean check = ph.deleteUser(Database.userLog.getUserID());
                            if(check)
                                Toast.makeText(this, "Account Deleted", Toast.LENGTH_SHORT);
                            else
                                Toast.makeText(this, "Failed to delete", Toast.LENGTH_SHORT).show();
                            Intent toDelete = new Intent(this, LandingActivity.class);
                            startActivity(toDelete);
                            finish();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel()
                        ).show();
                break;
            case R.id.btnOut:
                AlertDialog.Builder logout = new AlertDialog.Builder(this);
                logout.setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("Sign Out ?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            Intent toOut = new Intent(this, LandingActivity.class);
                            Database.userLog = null;
                            startActivity(toOut);
                            finish();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel()
                        ).show();
                break;
        }
    }
}