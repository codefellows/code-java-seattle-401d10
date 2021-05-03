package com.ncarignan.buyexpensivethings;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ncarignan.buyexpensivethings.daos.CartItemDao;
import com.ncarignan.buyexpensivethings.models.CartItem;

@Database(entities = {CartItem.class}, version=1)
public abstract class ExpensiveThingsDatabase extends RoomDatabase {
    public abstract CartItemDao cartItemDao();
}
