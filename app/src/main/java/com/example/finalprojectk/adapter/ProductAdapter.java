package com.example.finalprojectk.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.finalprojectk.DetailActivity;
import com.example.finalprojectk.R;
import com.example.finalprojectk.object.Product;
import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> productList;
    Context context;

    public ProductAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        String name = productList.get(position).getProductName();
        String image = productList.get(position).getProductImage();
        Integer price = productList.get(position).getProductPrice();
        Float rating = productList.get(position).getProductRating();
        String pPrice = "Price : $"+price;
        holder.pName.setText(name);
        holder.pPrice.setText(pPrice);
        holder.pRating.setRating(rating);
        Glide.with(context).load(image).into(holder.pImage);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            Product detail = productList.get(position);
            intent.putExtra("detail",detail);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView pName, pPrice;
        RatingBar pRating;
        ImageView pImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pImage = itemView.findViewById(R.id.pImage);
            pName = itemView.findViewById(R.id.pName);
            pRating = itemView.findViewById(R.id.pRating);
            pPrice = itemView.findViewById(R.id.pPrice);
        }
    }
}
