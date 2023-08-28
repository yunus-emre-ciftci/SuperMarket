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
        Product product1 = new Product(subCategory1, "Good", "Patos", 2.3, 2023, 5, 25, 100, 2024, 5, 25);
        Product product2 = new Product(subCategory, "Bad", "Ruffles", 2.5, 2023, 8, 1, 110, 2025, 1, 1);
        Product product3 = new Product(subCategory, "Bad", "Doritos", 2.3, 2022, 12, 31, 110, 2027, 11, 5);
        Product product4 = new Product(subCategory, "Good", "Alaturka", 2.9, 2021, 12, 31, 110, 2022, 11, 5);
        Product product5 = new Product(subCategory, "Good", "Pringles", 4.0, 2023, 12, 31, 10, 2025, 11, 5);
        StockJDBC stockJDBC = new StockJDBC();
        //stockJDBC.addCategory(category1);
        //stockJDBC.addSubCategory(subCategory1);
        //stockJDBC.addProduct(product5);
        stockJDBC.updateProduct(product1,5);
        stockJDBC.printAllProduct();
        //stockJDBC.printAllCategory();
        //stockJDBC.printAllSubCategory();
        //stockJDBC.updateStockQuantity(1,0);
        //stockJDBC.deleteProduct(product1);
        /*for (Product product : stockJDBC.findProductByName("pringles")) {
            System.out.println(product);
        }*/
    }

}
