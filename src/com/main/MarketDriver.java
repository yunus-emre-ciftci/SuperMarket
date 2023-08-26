package com.main;

import com.dao.dal.StockJDBC;
import com.domain.Category;
import com.domain.Product;
import com.domain.SubCategory;

public class MarketDriver {
    public static void main(String[] args) {
        Category category = new Category("Snack", "Chips, wafer style products");
        Category category1 = new Category("Computer", "Computers and computer equipment.");
        SubCategory subCategory = new SubCategory(category, "Chips", "Patato Chips");
        SubCategory subCategory1 = new SubCategory(category, "Chocolate", "Cacao");
        Product product1 = new Product(subCategory, "Good", "Patos", 2.3, "2023-2-11", 100, "2023-2-11");
        Product product2 = new Product(subCategory, "Bad", "Ruffles", 2.5, "2022-2-11", 110, "2023-2-11");
        Product product3 = new Product(subCategory, "Bad", "Doritos", 2.3, "2022-2-11", 110, "2023-2-11");
        StockJDBC stockJDBC = new StockJDBC();
        stockJDBC.addCategory(category1);
        //stockJDBC.addSubCategory(subCategory1);
        //stockJDBC.addProduct(product2);
        //stockJDBC.printAllProduct();
        stockJDBC.printAllCategory();
        //stockJDBC.printAllSubCategory();
        /*for (Product product : stockJDBC.getProductsByCategory(category)) {
            System.out.println(product);
        }*/
    }

}
