package com.ayaabdelaziz.azshopping.local;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM fav_table ORDER BY id ASC ")
    List<FavoriteModel> getAllWishlistItems();


    @Insert
    void insertWishlist(FavoriteModel model);

    @Delete
    void deleteWishlist(FavoriteModel model);

}
