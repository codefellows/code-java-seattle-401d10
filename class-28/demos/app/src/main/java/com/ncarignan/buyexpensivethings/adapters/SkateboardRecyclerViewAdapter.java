package com.ncarignan.buyexpensivethings.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ncarignan.buyexpensivethings.R;

import java.util.ArrayList;
import java.util.List;

// TODO: 2-1: change the type of ViewHolder to THIS RecyclerView's ViewHolder( SkateBoardViewHolder)
public class SkateboardRecyclerViewAdapter extends RecyclerView.Adapter<SkateboardRecyclerViewAdapter.SkateBoardViewHolder>{
    static String TAG = "ncarign.Adapter";

    static List<String> skateboardDesigns;

    static {
        skateboardDesigns = new ArrayList<>();
        skateboardDesigns.add("Palm Tree");
        skateboardDesigns.add("Sunset");
        skateboardDesigns.add("Beach");
        skateboardDesigns.add("Gray Sky");
        skateboardDesigns.add("Mountains");
        skateboardDesigns.add("Monsters");
        skateboardDesigns.add("Dragons");
        skateboardDesigns.add("Spongebob");
        skateboardDesigns.add("Patrick");
        skateboardDesigns.add("Skull");
        skateboardDesigns.add("Ghost");
        skateboardDesigns.add("Bowser");
        skateboardDesigns.add("Dog");
        skateboardDesigns.add("Ginger");
        skateboardDesigns.add("Snowdrop");
        skateboardDesigns.add("Trash Can");
        skateboardDesigns.add("Soda");
        skateboardDesigns.add("Snowflake");
    }


    @NonNull
    @Override
    public SkateBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        TODO: 4 - choose the fragment and `inflate` it (fragment == list item in this case)
        View fragment = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_blank, parent, false);

//        TODO: 5 - define a viewholder for the data
//        TODO: 6 - attach this fragment to a ViewHolder which will manage its data
        SkateBoardViewHolder skateboardViewHolder = new SkateBoardViewHolder(fragment);
        return skateboardViewHolder;
    }

    @Override // this function runs when an element first loads or cycles across the top to bottom of the adapter and vice versa
    public void onBindViewHolder(@NonNull SkateBoardViewHolder holder, int position) {
        // TODO: change the data when fragments cycle
        String thisSkateBoardDesign = skateboardDesigns.get(position);
        holder.design = thisSkateBoardDesign; // this line passes the data to SkateboardViewHolder

        //TODO: 2-3 modify the textviews or anything else in the fragment with the current data
        ((TextView)holder.itemView.findViewById(R.id.textViewSkateboardDesign)).setText(thisSkateBoardDesign);
    }

    @Override
    public int getItemCount() { // TODO: make sure this is not 0
        Log.i(TAG, "getItemCount: " + skateboardDesigns.size() + skateboardDesigns.get(0));
        return skateboardDesigns.size();
    }

    public static class SkateBoardViewHolder extends RecyclerView.ViewHolder {
        String design; // Dragon
        // TODO: 2-2 Create a instance variable for each dynamic piece of data
        public SkateBoardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
