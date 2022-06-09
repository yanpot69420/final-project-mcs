package com.example.finalprojectk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectk.R;
import com.example.finalprojectk.object.Product;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> productList;

    public ProductAdapter(ArrayList<Product> productList) {
        this.productList = productList;
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

        holder.pName.setText(name);
        holder.pPrice.setText(String.valueOf(price));
        holder.pRating.setRating(rating);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
