package com.example.finalprojectk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectk.R;
import com.example.finalprojectk.object.Transaction;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    ArrayList<Transaction> transactions;

    public HistoryAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.trName.setText(transactions.get(position).getProductID());
        holder.trId.setText(transactions.get(position).getTransactionID());
        holder.trDate.setText(transactions.get(position).getTransactionDate());
        holder.trQuantity.setText(transactions.get(position).getQuantity().toString());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView trImage;
        TextView trId, trQuantity, trName, trDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trImage = itemView.findViewById(R.id.trImage);
            trId = itemView.findViewById(R.id.trId);
            trName = itemView.findViewById(R.id.trName);
            trQuantity = itemView.findViewById(R.id.trQuantity);
            trDate = itemView.findViewById(R.id.trDate);
        }
    }
}
