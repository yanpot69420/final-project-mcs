package com.example.finalprojectk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectk.R;
import com.example.finalprojectk.object.Users;
import java.util.ArrayList;

public class TempAdapter extends RecyclerView.Adapter<TempAdapter.ViewHolder> {
    ArrayList<Users> customers;

    public TempAdapter(ArrayList<Users> customers) {
        this.customers = customers;
    }

    @NonNull
    @Override
    public TempAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempAdapter.ViewHolder holder, int position) {
        Integer id = customers.get(position).getUserID();
        String email = customers.get(position).getUserEmail();
        String username = customers.get(position).getUserUsername();
        String phone = customers.get(position).getUserPhoneNumber();

        holder.tvId.setText(Integer.toString(id));
        holder.tvEmail.setText(email);
        holder.tvUsername.setText(username);
        holder.tvPhone.setText(phone);

    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvEmail, tvUsername, tvPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }
}
