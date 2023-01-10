package com.ayaabdelaziz.azshopping.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cart_table ORDER BY id ASC ")
    List<CartModel> getAllCartLists();


    @Insert
    void insertCart(CartModel model);

    @Delete
    void deleteCart(CartModel model);
}
