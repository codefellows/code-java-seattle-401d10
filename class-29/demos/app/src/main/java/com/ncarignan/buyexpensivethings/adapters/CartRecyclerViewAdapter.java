package com.ncarignan.buyexpensivethings.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ncarignan.buyexpensivethings.R;
import com.ncarignan.buyexpensivethings.models.CartItem;

import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter {

    List<CartItem> cartItemList;

    public CartRecyclerViewAdapter(List<CartItem> cartItemList){
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fragment = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cart_item, parent, false);


        return new CartItemViewHolder(fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TextView)holder.itemView.findViewById(R.id.textViewCartItem))
                .setText(cartItemList.get(position).name + " " + cartItemList.get(position).price);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
