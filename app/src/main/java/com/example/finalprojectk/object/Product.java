package com.example.finalprojectk.object;

import android.os.Parcelable;

import java.util.ArrayList;

public class Product extends ArrayList<Parcelable> {
    private String productName;
    private Float productRating;
    private Integer productPrice;
    private String productImage;
    private String productDescription;

    public Product(String productName, Float productRating, Integer productPrice, String productImage, String productDescription) {
        this.productName = productName;
        this.productRating = productRating;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }
    public Float getProductRating() {
        return productRating;
    }
    public Integer getProductPrice() {
        return productPrice;
    }
    public String getProductImage() {
        return productImage;
    }
    public String getProductDescription() {
        return productDescription;
    }


}
