package com.ncarignan.buyexpensivethings.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ncarignan.buyexpensivethings.R;
import com.ncarignan.buyexpensivethings.adapters.SkateboardRecyclerViewAdapter;

public class RecyclerViewPractice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_practice);

        // TODO: 1 - reference the recyclerView
        RecyclerView recyclerView = findViewById(R.id.skateboardRecyclerView);
        //TODO: 1.5  -set the layout manager
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        // TODO: 2 - Create and load a recylerViewAdapter
        recyclerView.setAdapter(new SkateboardRecyclerViewAdapter());
//        TODO: ??? Later allow data to be passed (second half of class)

//        TODO: 3: design your fragment for the recyclerView

    }


}