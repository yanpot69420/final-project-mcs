package com.example.finalprojectk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.finalprojectk.database.Database;
import com.example.finalprojectk.database.DatabaseHelper;
import com.example.finalprojectk.object.Product;
import com.example.finalprojectk.object.Transaction;

import java.text.DateFormat;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    TextView detailName, detailPrice, detailDescription, detailQuantity;
    Button btnIncrease, btnDecrease, btnBuy;
    AlertDialog.Builder buyNotification;
    SmsManager smsManager;
    Integer sendSmsPermission;
    DatabaseHelper dhDetail;
    Calendar calendar = Calendar.getInstance();
    String currentDate;
    RatingBar detailRating;
    ImageView detailImage;
    Integer quantity = 0;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        permitSms();
        dhDetail = new DatabaseHelper(DetailActivity.this);
        currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        product = (Product) getIntent().getSerializableExtra("detail");
        buyNotification = new AlertDialog.Builder(this);
        if(product!=null){
            initVar(product);
        }

        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
    }


    void initView(){
        detailImage = findViewById(R.id.detailImage);
        detailName = findViewById(R.id.detailName);
        detailRating = findViewById(R.id.detailRating);
        detailPrice = findViewById(R.id.detailPrice);
        detailDescription = findViewById(R.id.detailDescription);
        btnBuy = findViewById(R.id.btnBuy);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        detailQuantity = findViewById(R.id.detailQuantity);
    }

    void initVar(Product product){
        Glide.with(this).load(product.getProductImage()).into(detailImage);
        detailName.setText(product.getProductName());
        detailRating.setRating(product.getProductRating());
        String dtPrice = "Price : $" +product.getProductPrice().toString();
        String dtDesc = "Description :\n"+product.getProductDescription();
        detailPrice.setText(dtPrice);
        detailDescription.setText(dtDesc);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIncrease:
                quantity++;
                detailQuantity.setText(String.valueOf(quantity));
                break;
            case R.id.btnDecrease:
                if(quantity>0)
                quantity--;
                detailQuantity.setText(String.valueOf(quantity));
                break;
            case R.id.btnBuy:
                if(quantity<1)
                    Toast.makeText(this, "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
                else {
                    initBuyBuilder(buyNotification);
                    buyNotification.show();
                }
                break;
        }
    }

    void initBuyBuilder(AlertDialog.Builder builder){
        int totalPrice = quantity * product.getProductPrice();
        builder.setIcon(R.drawable.black_cart)
                .setTitle("Buy "+ quantity + "x "+ product.getProductName() + " for $" + totalPrice+ " ?")
                .setCancelable(true)
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    Transaction transaction = new Transaction(-1, Database.userLog.getUserID(), product.getProductName(), currentDate, quantity);
                    boolean checkInsert = dhDetail.addTransaction(transaction);
                    if(!checkInsert){
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String message = "Thanks for purchasing "+ quantity + "x "+ product.getProductName();
                        String phone = "5554";
                        Toast.makeText(this, "Item Purchased", Toast.LENGTH_SHORT).show();
                        smsManager.sendTextMessage(phone, null, message, null,  null);
                    }
                    quantity = 0;
                    detailQuantity.setText(String.valueOf(quantity));
                })
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
    }

    void permitSms(){
        smsManager = SmsManager.getDefault();
        sendSmsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(sendSmsPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},1 );
        }
    }
}