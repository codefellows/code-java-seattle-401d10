package com.ncarignan.buyexpensivethings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncarignan.buyexpensivethings.activities.RecyclerViewPractice;
import com.ncarignan.buyexpensivethings.activities.UserProfile;
import com.ncarignan.buyexpensivethings.adapters.SkateboardRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "ncarapp.main";

    static int percentOff = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// TODO: add second recyclerview
        RecyclerView rv = findViewById(R.id.skateboardRecyclerView2);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new SkateboardRecyclerViewAdapter());

        Button saleButtonPotato = findViewById(R.id.saleButton);
        saleButtonPotato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("this is a system out, time for a sale");
                Log.i(TAG, "this is a log, also time for a sale has been clicked");

//                Edit the text of another element
//                1. find it by id, change the text
                ((TextView) findViewById(R.id.saleTextView)).setText("Sale Sale Sale!");

                double rando = Math.random();
                double randoPercent = rando * 100;
                percentOff = (int) randoPercent;
//                TextView saleTextView = findViewById(R.id.saleTextView);
//                saleTextView.setText("Sale Sale Sale!");
            }
        });

        Button goToShoppingButton = findViewById(R.id.goToShoppingButton);

        goToShoppingButton.setOnClickListener(view -> {

            Intent goToShoppingPageIntent = new Intent(MainActivity.this, OrderForm.class);

//            adds a key of percentOff and a value of some random int to the intent
            goToShoppingPageIntent.putExtra("percentOff", percentOff);

            startActivity(goToShoppingPageIntent);

        });

    findViewById(R.id.userProfileButton).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, UserProfile.class);
            startActivity(intent);
        }
    });

        findViewById(R.id.buttonRecyclerViewPractice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewPractice.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String username = preferences.getString("username", null);
        if(username != null){
            ((TextView) findViewById(R.id.textViewUsername)).setText(username);
        }
    }
}

