package com.ayaabdelaziz.azshopping.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteModel.class , CartModel.class}, version = 2, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {


    private static FavoriteDatabase database = null;

    public abstract FavoriteDao getFavoriteDao();
    public abstract CartDao getCartDao();

    public static synchronized FavoriteDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class, "favoritedatabase").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }
}
