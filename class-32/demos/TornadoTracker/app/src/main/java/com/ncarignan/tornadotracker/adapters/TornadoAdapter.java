package com.ncarignan.tornadotracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Tornado;
import com.ncarignan.tornadotracker.R;

import java.util.List;

public class TornadoAdapter extends RecyclerView.Adapter<TornadoAdapter.TornadoViewHolder> {

    List<Tornado> tornados;

    public TornadoAdapter(List<Tornado> tornados){
        this.tornados = tornados;
    }

    @NonNull
    @Override
    public TornadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Create the fragment, attach it to the view holder, return the view holder
        View fragment = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tornado, parent, false);

        TornadoViewHolder vh = new TornadoViewHolder(fragment);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TornadoViewHolder holder, int position) {
        //edit the view with the data from the holder, attach the data to the hold
        holder.tornado = tornados.get(position);
//        modify the text in it
        ((TextView) holder.itemView.findViewById(R.id.tornadoName)).setText(holder.tornado.getName());
        ((TextView) holder.itemView.findViewById(R.id.tornadoLatitude)).setText(holder.tornado.getLatitude().toString());
        ((TextView) holder.itemView.findViewById(R.id.tornadoLongitude)).setText(holder.tornado.getLongitude().toString());
        ((TextView) holder.itemView.findViewById(R.id.tornadoCategory)).setText(holder.tornado.getCategory());
    }

    @Override
    public int getItemCount() {
        return tornados.size();
    }

    static class TornadoViewHolder extends RecyclerView.ViewHolder {
        public Tornado tornado;

        public TornadoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
