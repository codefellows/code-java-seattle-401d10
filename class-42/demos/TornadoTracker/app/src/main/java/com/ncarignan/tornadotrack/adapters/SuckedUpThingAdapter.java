package com.ncarignan.tornadotrack.adapters;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.SuckedUpThing;
import com.ncarignan.tornadotrack.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuckedUpThingAdapter extends RecyclerView.Adapter<SuckedUpThingAdapter.SuckedUpThingViewHolder> {

    List<SuckedUpThing> suckedUpThingList;

    public SuckedUpThingAdapter(List<SuckedUpThing> suckedUpThingList){
        this.suckedUpThingList = suckedUpThingList;
//        Collections.sort(this.suckedUpThingList,
//                new Comparator<SuckedUpThing>() {
//                    @Override
//                    public int compare(SuckedUpThing o1, SuckedUpThing o2) {
//                        return 0;
//                    }
//                });

        Collections.sort(this.suckedUpThingList,
                (a, b) -> {
                    double first = a.getLatitude() + a.getLongitude();
                    double second = b.getLatitude() + b.getLongitude();

                    if(first > second) {
                        return 1;
                    } else if(first < second){
                        return -1;
                    } else {
                        return 0;

                    }
                });
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
                .setText(
                        holder.suckedUpThing.getName() +
                        " " +
                        holder.suckedUpThing.getLatitude() +
                        " " +
                        holder.suckedUpThing.getLongitude()
                );

        ImageView i = holder.itemView.findViewById(R.id.imageViewSut);
        i.setImageBitmap(holder.suckedUpThing.bitmap);
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
