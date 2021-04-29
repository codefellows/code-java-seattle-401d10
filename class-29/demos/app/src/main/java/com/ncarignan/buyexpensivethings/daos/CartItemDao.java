package com.ncarignan.buyexpensivethings.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ncarignan.buyexpensivethings.models.CartItem;

import java.util.List;

@Dao // Dao does not preload methods like, findAll, save
public interface CartItemDao  { // `implements` means we need to write the methods with code blocks

    @Insert
    public void insert(CartItem cartItem);

    @Query("SELECT * FROM CartItem")
    public List<CartItem> findAll();

    @Query("SELECT * FROM CartItem ORDER BY id DESC")
    public List<CartItem> findAllReversed();

}
