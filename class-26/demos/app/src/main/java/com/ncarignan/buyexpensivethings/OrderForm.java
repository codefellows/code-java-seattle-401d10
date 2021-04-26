package com.ncarignan.buyexpensivethings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class OrderForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

//        Submitting a form
//        set a click event on something
//        set an event to the button we want to submit on
//        get the data from the form
//        selecting it by id and readnig it
//        do what we want with the data
//        Specifically: take the item info, and be taken to a Cart activity with that info

        Button button = findViewById(R.id.buyButton);
        button.setOnClickListener(view -> {
//            name of item
            String name = ((EditText)findViewById(R.id.editTextTextProductName)).getText().toString();
//            EditText
//            price
            String price = ((EditText)findViewById(R.id.editTextPrice)).getText().toString();

//            address
            String address = ((EditText)findViewById(R.id.editTextTextPostalAddress2)).getText().toString();

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