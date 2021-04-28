package com.ncarignan.buyexpensivethings.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ncarignan.buyexpensivethings.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartItemFragment extends Fragment {

        public CartItemFragment() {
        // Required empty public constructor
    }


    public static CartItemFragment newInstance(String param1, String param2) {
        CartItemFragment fragment = new CartItemFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_item, container, false);
    }
}