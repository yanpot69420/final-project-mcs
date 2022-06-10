package com.example.finalprojectk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalprojectk.object.Product;

public class DetailActivity extends AppCompatActivity {
    ImageView detailImage;
    TextView detailName;
    TextView detailPrice;
    TextView detailDescription;
    RatingBar detailRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        Product product = (Product) getIntent().getSerializableExtra("detail");
        if(product!=null){
            initVar(product);
//            Log.wtf("productlist", product.getProductName());
//            Log.wtf("productlist", product.getProductDescription());
//            Log.wtf("productlist", product.getProductImage());

        }
    }

    void initView(){
        detailImage = findViewById(R.id.detailImage);
        detailName = findViewById(R.id.detailName);
        detailRating = findViewById(R.id.detailRating);
        detailPrice = findViewById(R.id.detailPrice);
        detailDescription = findViewById(R.id.detailDescription);
    }

    void initVar(Product product){
        Glide.with(this).load(product.getProductImage()).into(detailImage);
        detailName.setText(product.getProductName());
        detailRating.setRating(product.getProductRating());
        detailPrice.setText("Price : $" +product.getProductPrice().toString());
        detailDescription.setText("Description :\n"+product.getProductDescription());
    }
}