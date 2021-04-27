package com.ncarignan.buyexpensivethings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
    }
}