package com.dao;

import com.domain.Category;
import com.domain.Product;
import com.domain.SubCategory;

import java.util.ArrayList;

public interface ProductDataAccess {
    void addProduct(Product newProduct);
    void printAllProduct();
    ArrayList<Product> getProductsByCategory(Category category);
    ArrayList<Product> getProductsBySubCategory(SubCategory subCategory);
    ArrayList<Product> getExpiredProducts();
    ArrayList<Product> getProductsInStock();
    boolean updateStockQuantity(int productId, int newStockQuantity);
    ArrayList<Product> getProductsOutOfStock();
    ArrayList<Product> findProductByName(String productName);
    Product deleteProduct(Product product);
    public boolean deleteAllProducts();
    Product updateProduct(Product product, int id);
    Product addToCart(Product product);
}
