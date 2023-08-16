package com.dao.dal;

import com.dao.MarketDataAccess;
import com.domain.Category;
import com.domain.Product;
import com.domain.SubCategory;
import com.util.DBDataSource;

import java.sql.*;
import java.util.ArrayList;

public class StockJDBC implements MarketDataAccess {
    @Override
    public void addProduct(Product newProduct) {

    }

    @Override
    public void printAllProduct() {
        String query = "SELECT * FROM t_product";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Product getProductByBarcode(int barcode) {
        return null;
    }

    @Override
    public ArrayList<Product> getProductsByCategory(Category category) {
        return null;
    }

    @Override
    public ArrayList<Product> getProductsBySubCategory(SubCategory subCategory) {
        return null;
    }

    @Override
    public ArrayList<Product> getExpiredProducts() {
        return null;
    }

    @Override
    public ArrayList<Product> getProductsInStock() {
        return null;
    }

    @Override
    public boolean updateStockQuantity(int productId, int newStockQuantity) {
        return false;
    }

    @Override
    public int getProductCountByCategory(Category category) {
        return 0;
    }

    @Override
    public int getProductCountBySubCategory(SubCategory subCategory) {
        return 0;
    }

    @Override
    public ArrayList<Product> getProductsOutOfStock() {
        return null;
    }

    @Override
    public ArrayList<Product> findProductByName(String productName) {
        return null;
    }

    @Override
    public Product deleteProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product, int id) {
        return null;
    }
}
