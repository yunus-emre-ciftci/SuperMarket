package com.main;

import com.model.Category;
import com.model.Products;

public class MarketDriver {
    public static void main(String[] args) {
        Category snack = new Category(1, "Snack", "Snacks with the most beautiful brands are in this category.");
        Products lays = new Products(snack, 1, "Lays", 17.3, 94.3, 100, "Turkey", "12/08/2025");
        System.out.println(lays.toString());
    }

}
