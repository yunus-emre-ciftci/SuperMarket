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
    public void addCategory(Category newCategory) {

    }

    @Override
    public void addSubCategory(SubCategory newSubCategory) {

    }

    @Override
    public void addProduct(Product newProduct) {
        String addQuery = "INSERT INTO t_product (product_id, subcategory_id, product_name, description,\n" +
                "    price, expiration_date, stock_quantity,\n" +
                "     creation_product_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connect = DBDataSource.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(addQuery);
        ) {
            preparedStatement.setInt(1, newProduct.getProductId());
            preparedStatement.setInt(2, newProduct.getSubCategory().getSubCategoryId());
            preparedStatement.setString(3, newProduct.getProductName());
            preparedStatement.setString(4, newProduct.getDescription());
            preparedStatement.setDouble(5, newProduct.getPrice());
            preparedStatement.setDate(6, Date.valueOf(newProduct.getExpirationDate()));
            preparedStatement.setInt(7, newProduct.getStockQuantity());
            preparedStatement.setTimestamp(8, newProduct.getCreationProductDate());

            preparedStatement.executeUpdate(); // Bu satır eklendi
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void printAllProduct() {
        String query = "SELECT * FROM t_product";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                int subcategoryId = resultSet.getInt("subcategory_id");
                String product_name = resultSet.getString("product_name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String productionDate = resultSet.getString("production_date");
                String expirationDate = resultSet.getString("expiration_date");
                int stockQuantity = resultSet.getInt("stock_quantity");
                Timestamp creationProductDate = resultSet.getTimestamp("creation_product_date");
                System.out.println(
                        "Product ID: " + productId +
                                ", Subcategory ID: " + subcategoryId +
                                ", Product Name: " + product_name +
                                ", Description: " + description +
                                ", Price: " + price +
                                ", Production Date: " + productionDate +
                                ", Expiration Date: " + expirationDate +
                                ", Stock Quantity: " + stockQuantity +
                                ", Creation Product Date: " + creationProductDate
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product getIdByBarcode(int productId) {
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
