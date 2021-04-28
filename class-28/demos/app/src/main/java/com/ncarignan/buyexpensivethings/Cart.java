package com.ncarignan.buyexpensivethings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ncarignan.buyexpensivethings.adapters.CartRecyclerViewAdapter;
import com.ncarignan.buyexpensivethings.models.CartItem;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    public static String TAG = "ncarapp.cart";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        if(intent.getStringExtra("productName") == null){
            Log.i(TAG, "nothing needs to be done here, nothing bought");
        } else {
            String name = intent.getStringExtra("productName");
            String price = intent.getStringExtra("price");
            String address = intent.getStringExtra("address");

            Log.i(TAG, "onCreate: " + name);

            String info = name + " " + price + " " + address;

            ((TextView) findViewById(R.id.productInfoTextView)).setText(info);
        }




//       ================== Recyclerview stuff =================
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem("Car", "$1000000"));
        cartItems.add(new CartItem("Bed", "$1000000"));
        cartItems.add(new CartItem("House", "$100000"));
        cartItems.add(new CartItem("Elephant Statue", "$1000000"));
        RecyclerView rv = findViewById(R.id.cartRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CartRecyclerViewAdapter(cartItems));
    }
}