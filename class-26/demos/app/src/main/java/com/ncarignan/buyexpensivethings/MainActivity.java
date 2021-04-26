package com.ncarignan.buyexpensivethings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "ncarapp.main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Something happen when we click on a button
        // 1. get by id
        // 2. add event listener
        // 3. attack a callback class with a onClick method
        // 4. do stuff in the callback

        Button saleButtonPotato = findViewById(R.id.saleButton);
        saleButtonPotato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("this is a system out, time for a sale");
                Log.i(TAG, "this is a log, also time for a sale has been clicked");

//                Edit the text of another element
//                1. find it by id, change the text
                ((TextView) findViewById(R.id.saleTextView)).setText("Sale Sale Sale!");
//                TextView saleTextView = findViewById(R.id.saleTextView);
//                saleTextView.setText("Sale Sale Sale!");
            }
        });

        Button goToShoppingButton = findViewById(R.id.goToShoppingButton);

//         Lambda function in java. It is just syntactic sugar exactly the same thing as is happening to the saleButton
        goToShoppingButton.setOnClickListener(view -> {

//            Intent is like an anchor tag -> it can contain data
            Intent goToShoppingPageIntent = new Intent(MainActivity.this, OrderForm.class);
            // Class context we are coming from (view we are coming from), class we are going to (orderForm)
//            MainActivity.this.startActivity(goToShoppingPageIntent);
            startActivity(goToShoppingPageIntent);

        });

    }
}

