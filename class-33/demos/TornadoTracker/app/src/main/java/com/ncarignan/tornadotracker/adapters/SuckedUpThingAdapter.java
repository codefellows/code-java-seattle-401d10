package com.ncarignan.tornadotracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.SuckedUpThing;
import com.ncarignan.tornadotracker.R;

import java.util.List;

public class SuckedUpThingAdapter extends RecyclerView.Adapter<SuckedUpThingAdapter.SuckedUpThingViewHolder> {

    List<SuckedUpThing> suckedUpThingList;

    public SuckedUpThingAdapter(List<SuckedUpThing> suckedUpThingList){
        this.suckedUpThingList = suckedUpThingList;
    }

    @NonNull
    @Override
    public SuckedUpThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fragment = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_sucked_up_thing, parent, false);


        SuckedUpThingViewHolder suckedUpThingViewHolder = new SuckedUpThingViewHolder(fragment);
        return suckedUpThingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuckedUpThingViewHolder holder, int position) {
        holder.suckedUpThing = suckedUpThingList.get(position);
        ((TextView)holder.itemView.findViewById(R.id.textViewSuckedUpThing))
                .setText(holder.suckedUpThing.getName());
    }

    static public class SuckedUpThingViewHolder extends RecyclerView.ViewHolder {
        public SuckedUpThing suckedUpThing;

        public SuckedUpThingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return suckedUpThingList.size();
    }
}
