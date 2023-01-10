package com.ayaabdelaziz.azshopping.home.pojo;

import java.util.ArrayList;

public class ProductsByCategory {
    public String title;
    public int price;
    public String description;
    public ArrayList<String> images;
    public Category category;
    public int id;

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public Category getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }
}
