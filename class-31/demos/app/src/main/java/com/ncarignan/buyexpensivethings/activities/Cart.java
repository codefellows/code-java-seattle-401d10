package com.ncarignan.buyexpensivethings.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.CartItem;
import com.ncarignan.buyexpensivethings.ExpensiveThingsDatabase;
import com.ncarignan.buyexpensivethings.R;
import com.ncarignan.buyexpensivethings.adapters.CartRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    public static String TAG = "ncarapp.cart";

    ExpensiveThingsDatabase expensiveThingsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        expensiveThingsDatabase = Room.databaseBuilder(getApplicationContext(), ExpensiveThingsDatabase.class, "ncarignan_expensive_things")
//                .allowMainThreadQueries()
//                .build();




    }

    public void onResume(){
        super.onResume();
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
//        List<CartItem> cartItems = expensiveThingsDatabase.cartItemDao().findAll();
        List<CartItem> cartItems = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(CartItem.class),
                response -> {
                    Log.i(TAG, "onCreate: success");

                    for(CartItem item : response.getData().getItems()){
                        System.out.println(item.getName());
                        Log.i(TAG, "items:" + item.getName());
                        cartItems.add(item);
                    }
//                    rv.getAdapter().notifyDataSetChanged();
                },
                r -> Log.i(TAG, "onCreate: failure" + r.toString())
        );
        RecyclerView rv = findViewById(R.id.cartRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CartRecyclerViewAdapter(cartItems));


    }
}