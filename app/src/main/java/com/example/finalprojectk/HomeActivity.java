package com.example.finalprojectk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        String url = "https://mocki.io/v1/5f379081-2473-4494-9cc3-9e808772dc54";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
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
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("error response", error.toString());
            }
        });
        requestQueue.add(jor);
    }

    void initView() {
        userName = findViewById(R.id.userName);
        productList = findViewById(R.id.productList);
    }

    void configRV(){
        ProductAdapter rvAdapter = new ProductAdapter(products, this);
        productList.setAdapter(rvAdapter);
        productList.setLayoutManager(new LinearLayoutManager(this));
    }
}