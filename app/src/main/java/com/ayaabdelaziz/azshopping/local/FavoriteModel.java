package com.ayaabdelaziz.azshopping.local;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_table")
public class FavoriteModel {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    int price;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] images;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }
}
