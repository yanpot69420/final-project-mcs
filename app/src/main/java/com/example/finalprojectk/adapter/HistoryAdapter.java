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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    ArrayList<Transaction> transactions;

    public HistoryAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        String productname = transactions.get(position).getProductID();
        String transactionId = transactions.get(position).getTransactionID().toString();
        String transactionDate = transactions.get(position).getTransactionDate();
        String quantity = transactions.get(position).getQuantity().toString();
        holder.trName.setText(productname);
        holder.trId.setText(transactionId);
        holder.trDate.setText(transactionDate);
        holder.trQuantity.setText(quantity);
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
