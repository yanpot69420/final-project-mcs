package com.example.finalprojectk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.finalprojectk.adapter.HistoryAdapter;
import com.example.finalprojectk.database.Database;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView historyList;
    TextView tvNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyList = findViewById(R.id.historyList);
        tvNoData = findViewById(R.id.tvNoData);
        tvNoData.setVisibility(View.INVISIBLE);
        if(Database.getTransactionData(this, Database.userLog.getUserID()).size()>0) {
            HistoryAdapter adapter = new HistoryAdapter(Database.getTransactionData(this, Database.userLog.getUserID()));
            historyList.setAdapter(adapter);
            historyList.setLayoutManager(new LinearLayoutManager(this));
        }
        else {
            tvNoData.setVisibility(View.VISIBLE);
        }


    }
}