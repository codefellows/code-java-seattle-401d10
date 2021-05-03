package com.ncarignan.buyexpensivethings.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;
    public String price;

    public CartItem(String name, String price){
        this.name = name;
        this.price = price;
    };
}
