package com.example.finalprojectk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.finalprojectk.adapter.HistoryAdapter;
import com.example.finalprojectk.database.Database;
import com.example.finalprojectk.object.Transaction;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView historyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyList = findViewById(R.id.historyList);

        if(Database.getTransactionData(this, Database.userLog.getUserID())!=null) {
            HistoryAdapter adapter = new HistoryAdapter(Database.getTransactionData(this, Database.userLog.getUserID()));
            historyList.setAdapter(adapter);
            historyList.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}