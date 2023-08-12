package com.main;

import com.model.Category;
import com.model.Product;
import com.model.SubCategory;

public class MarketDriver {
    public static void main(String[] args) {
        Category category = new Category(1, "Snack", "Chips, wafer style products");
        SubCategory subCategory = new SubCategory(category, 1, "Chips", "Patato Chips");
        Product product = new Product(subCategory, 1, "Lays", 17.2, 94.3, 100, "Turkey", "11/08/2023");
    }

}
