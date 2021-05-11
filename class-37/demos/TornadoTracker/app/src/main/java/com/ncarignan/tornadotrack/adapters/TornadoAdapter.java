package com.ncarignan.tornadotrack.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Tornado;
import com.ncarignan.tornadotrack.R;

import java.util.Collections;
import java.util.List;

public class TornadoAdapter extends RecyclerView.Adapter<TornadoAdapter.TornadoViewHolder> {
    String TAG = "ntornado.tornadoadapter";
    List<Tornado> tornados;
    ClicksOnTornadoAble clicksOnTornadoAble;

    public TornadoAdapter(List<Tornado> tornados, ClicksOnTornadoAble clicksOnTornadoAble){
        this.tornados = tornados;
        this.clicksOnTornadoAble = clicksOnTornadoAble;


    }

    @NonNull
    @Override
    public TornadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Create the fragment, attach it to the view holder, return the view holder
        View fragment = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tornado, parent, false);




        TornadoViewHolder vh = new TornadoViewHolder(fragment);
        fragment.setOnClickListener(v -> {
            clicksOnTornadoAble.handleClickOnTornado(vh);
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TornadoViewHolder holder, int position) {
        //edit the view with the data from the holder, attach the data to the hold
        holder.tornado = tornados.get(position);
//        holder.id = tornados.get(position).getId();
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

    public static class TornadoViewHolder extends RecyclerView.ViewHolder {
        public Tornado tornado;
//        String id;

        public TornadoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static interface ClicksOnTornadoAble {
        public void handleClickOnTornado(TornadoViewHolder vh);
    }
}
