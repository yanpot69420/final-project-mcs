package com.example.finalprojectk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.finalprojectk.R;
import com.example.finalprojectk.database.Database;
import com.example.finalprojectk.object.Transaction;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    ArrayList<Transaction> transactions;
    Context context;
    public HistoryAdapter(ArrayList<Transaction> transactions, Context context) {
        this.transactions = transactions;
        this.context = context;
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
        String image = "";
        Integer transactionPrice = 0;
        for (int i = 0; i < Database.productList.size(); i++) {
            if(productname.equals(Database.productList.get(i).getProductName())){
                transactionPrice = Database.productList.get(i).getProductPrice() * transactions.get(position).getQuantity();
                image = Database.productList.get(i).getProductImage();
                break;
            }
        }
        Glide.with(context).load(image).into(holder.trImage);
        holder.trName.setText("Product : " + productname);
        holder.trId.setText("ID : " + transactionId);
        holder.trDate.setText("Date : " + transactionDate);
        holder.trPrice.setText("Total Price : $" + transactionPrice);
        holder.trQuantity.setText("Quantity : " + quantity);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView trImage;
        TextView trId, trPrice,trQuantity, trName, trDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trImage = itemView.findViewById(R.id.trImage);
            trId = itemView.findViewById(R.id.trId);
            trName = itemView.findViewById(R.id.trName);
            trPrice = itemView.findViewById(R.id.trPrice);
            trQuantity = itemView.findViewById(R.id.trQuantity);
            trDate = itemView.findViewById(R.id.trDate);
        }
    }
}
