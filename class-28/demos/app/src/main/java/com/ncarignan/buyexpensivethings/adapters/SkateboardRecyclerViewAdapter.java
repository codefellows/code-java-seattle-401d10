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

    public List<String> skateboardDesigns;



//    TODO: 3-4 save the activity we are in so we can trigger its click
    public ClickOnSkateboardAble clickOnSkateboardAble;

//TODO: 3-4 save the activity we are in through the constructor so we can trigger its click
    public SkateboardRecyclerViewAdapter(ClickOnSkateboardAble clickOnSkateboardAble, List<String> skateboardDesigns){
        this.clickOnSkateboardAble = clickOnSkateboardAble;
        this.skateboardDesigns = skateboardDesigns;
    }

    @NonNull
    @Override // chooses the fragment and  creates the viewHolder
    public SkateBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        TODO: 1-4 - choose the fragment and `inflate` it (fragment == list item in this case)
        View fragment = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_blank, parent, false);

//        TODO: 1-5 - define a viewholder for the data
//        TODO: 1-6 - attach this fragment to a ViewHolder which will manage its data
        SkateBoardViewHolder skateboardViewHolder = new SkateBoardViewHolder(fragment);
        return skateboardViewHolder;
    }

    @Override // this function runs when an element first loads or cycles across the top to bottom of the adapter and vice versa
    public void onBindViewHolder(@NonNull SkateBoardViewHolder holder, int position) {
        // TODO: change the data when fragments cycle
        String thisSkateBoardDesign = skateboardDesigns.get(position);
        holder.design = thisSkateBoardDesign; // this line passes the data to SkateboardViewHolder
        holder.position = position;
        //TODO: 2-3 modify the textviews or anything else in the fragment with the current data
        ((TextView)holder.itemView.findViewById(R.id.textViewSkateboardDesign)).setText(thisSkateBoardDesign);

//        TODO: 3-0
        holder.itemView.setOnClickListener(v -> {
            Log.i(TAG, "clicked on the fragment");

//            TODO: 3-5 use the activity we saved in the constructor and trigger its click
            clickOnSkateboardAble.handleClickOnSkateboard(holder);
        });
    }

    @Override
    public int getItemCount() { // TODO: make sure this is not 0
        Log.i(TAG, "getItemCount: " + skateboardDesigns.size() + skateboardDesigns.get(0));
        return skateboardDesigns.size();
    }

    public static class SkateBoardViewHolder extends RecyclerView.ViewHolder {
        public String design; // Dragon
        public int position;
//        All ViewHolders have access to a variable View itemView
        // TODO: 2-2 Create a instance variable for each dynamic piece of data
        public SkateBoardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

//    TODO: 3-1 define an interface for clicking on things
    public interface ClickOnSkateboardAble {
        public void handleClickOnSkateboard(SkateBoardViewHolder skateBoardViewHolder);
    }
}
