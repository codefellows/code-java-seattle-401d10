package com.ncarignan.buyexpensivethings.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.ncarignan.buyexpensivethings.ExpensiveThingsDatabase;
import com.ncarignan.buyexpensivethings.R;
import com.ncarignan.buyexpensivethings.models.CartItem;

public class OrderForm extends AppCompatActivity {

    ExpensiveThingsDatabase expensiveThingsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        // Setup database
        expensiveThingsDatabase = Room.databaseBuilder(getApplicationContext(), ExpensiveThingsDatabase.class, "ncarignan_expensive_things")
                .allowMainThreadQueries()
                .build();

//        Retrieving intent info
        Intent loadedIntent = getIntent(); // gets the intent that was used to open this activity
        int percentOff = loadedIntent.getIntExtra("percentOff", 0); // retrieves an extra from that intent

        Button button = findViewById(R.id.buyButton);
        button.setOnClickListener(view -> {
//            name of item
            String name = ((EditText)findViewById(R.id.editTextTextProductName)).getText().toString();
//            EditText
//            price
            String price = ((EditText)findViewById(R.id.editTextPrice)).getText().toString();

            price = "" + (Integer.parseInt(price) * (100 - percentOff) / 100);

//            address
            String address = ((EditText)findViewById(R.id.editTextTextPostalAddress2)).getText().toString();

            // TODO: Save a CartItem
            CartItem cartItem = new CartItem(name, price);
            expensiveThingsDatabase.cartItemDao().insert(cartItem);


            Intent intent = new Intent(OrderForm.this, Cart.class);
            intent.putExtra("productName", name);
            intent.putExtra("price", price);
            intent.putExtra("address", address);
            startActivity(intent);
        });

//         How to pass data in a url: key value pairs from the form in the body of the post. GET: query string parameters: request params
//        In android we will add key value pairs that go with the data : to Extras
    }
}