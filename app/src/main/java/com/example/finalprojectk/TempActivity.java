package com.example.finalprojectk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.finalprojectk.adapter.TempAdapter;
import com.example.finalprojectk.database.Database;
import com.example.finalprojectk.database.DatabaseHelper;
import com.example.finalprojectk.object.Users;

import java.util.ArrayList;

public class TempActivity extends AppCompatActivity {
    RecyclerView rvTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        rvTemp = findViewById(R.id.rvTemp);
        TempAdapter adapter = new TempAdapter(Database.getUserData(this));
        rvTemp.setAdapter(adapter);
        rvTemp.setLayoutManager(new LinearLayoutManager(this));
    }
}