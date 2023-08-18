package com.main;

import com.dao.dal.StockJDBC;
import com.domain.Category;
import com.domain.Product;
import com.domain.SubCategory;

public class MarketDriver {
    public static void main(String[] args) {
        Category category = new Category(1, "Snack", "Chips, wafer style products");
        SubCategory subCategory = new SubCategory(category, 1, "Chips", "Patato Chips");
        Product product = new Product(subCategory,1,"Lays","Patato Chips",2.3,100,"2023-2-11");
        StockJDBC stockJDBC = new StockJDBC();
        stockJDBC.addProduct(product);
        stockJDBC.printAllProduct();
    }

}
