package com.dao;

import com.domain.Category;
import com.domain.Product;
import com.domain.SubCategory;

import java.util.ArrayList;

public interface MarketDataAccess {
    void addCategory(Category newCategory);
    void addSubCategory(SubCategory newSubCategory);
    void addProduct(Product newProduct);
    void printAllProduct();
    void printAllCategory();
    void printAllSubCategory();
    public Product getIdByBarcode(int productId);

    ArrayList<Product> getProductsByCategory(Category category);
    ArrayList<Product> getProductsBySubCategory(SubCategory subCategory);
    ArrayList<Product> getExpiredProducts();
    ArrayList<Product> getProductsInStock();
    boolean updateStockQuantity(int productId, int newStockQuantity);
    int getProductCountByCategory(Category category);
    int getProductCountBySubCategory(SubCategory subCategory);
    ArrayList<Product> getProductsOutOfStock();
    ArrayList<Product> findProductByName(String productName);
    Product deleteProduct(Product product);
    Product updateProduct(Product product, int id);
}
