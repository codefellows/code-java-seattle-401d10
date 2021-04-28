package com.ncarignan.buyexpensivethings.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ncarignan.buyexpensivethings.R;
import com.ncarignan.buyexpensivethings.adapters.SkateboardRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

//TODO: 4-1 implement the clickableInterface
public class RecyclerViewPractice extends AppCompatActivity implements SkateboardRecyclerViewAdapter.ClickOnSkateboardAble {
    SkateboardRecyclerViewAdapter skateboardRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_practice);

        List<String> skateboardDesigns = new ArrayList<>();
        skateboardDesigns.add("Palm Tree");
        skateboardDesigns.add("Sunset");

        skateboardRecyclerViewAdapter =  new SkateboardRecyclerViewAdapter(this, skateboardDesigns);

        // TODO: 1-1 - reference the recyclerView
        RecyclerView recyclerView = findViewById(R.id.skateboardRecyclerView);
        //TODO: 1-1.5  -set the layout manager
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        // TODO: 1-2 - Create and load a recylerViewAdapter

//        TODO: 3-3 pass 'this' into the adapter
        recyclerView.setAdapter(skateboardRecyclerViewAdapter);
//        TODO: ??? Later allow data to be passed (second half of class)

//        TODO: 1-3: design your fragment for the recyclerView

    }


    @Override // TODO: 3-2 implement the clickMethod from the interface
    public void handleClickOnSkateboard(SkateboardRecyclerViewAdapter.SkateBoardViewHolder skateBoardViewHolder) {
        Toast.makeText(this, "Oh no, you kickflipped and broke your " + skateBoardViewHolder.design + " board", Toast.LENGTH_LONG).show();
        skateboardRecyclerViewAdapter.skateboardDesigns.remove(skateBoardViewHolder.position);
//        skateboardRecyclerViewAdapter.notifyDataSetChanged(); // O(n) time: checks each view against the data
        skateboardRecyclerViewAdapter.notifyItemRemoved(skateBoardViewHolder.position);
    }
}