package com.example.finalprojectk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalprojectk.adapter.ProductAdapter;
import com.example.finalprojectk.object.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    TextView userName;
    RecyclerView productList;
    ArrayList<Product> products = new ArrayList<>();
    FrameLayout cardProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        String url = "https://mocki.io/v1/5f379081-2473-4494-9cc3-9e808772dc54";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(url, response -> {
                try {
                    JSONArray array = response.getJSONArray("furnitures");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jobj = array.getJSONObject(i);
                        String pName = jobj.getString("product_name");
                        Double rating = jobj.getDouble("rating");
                        Float pRating = rating.floatValue();
                        Integer pPrice = jobj.getInt("price");
                        String pImage = jobj.getString("image");
                        String pDescription = jobj.getString("description");
                        products.add(new Product(pName, pRating, pPrice, pImage, pDescription));
                    }
                    configRV();
                } catch (JSONException e){
                    e.printStackTrace();
                }
        }, error -> {Log.wtf("error response", error.toString());});
        requestQueue.add(jor);

        cardProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.icAbout:

            break;
            case R.id.icHistory:

            break;
        }
        return super.onOptionsItemSelected(item);
    }

    void initView() {
        userName = findViewById(R.id.userName);
        productList = findViewById(R.id.productList);
        cardProfile = findViewById(R.id.cardProfile);
    }

    void configRV(){
        ProductAdapter rvAdapter = new ProductAdapter(products, this);
        productList.setAdapter(rvAdapter);
        productList.setLayoutManager(new LinearLayoutManager(this));
    }
}