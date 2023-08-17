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
        String addQuery = "INSERT INTO t_product (product_id, subcategory_id, product_name, description,\n" +
                "    price, weight, production_date, expiration_date, stock_quantity,\n" +
                "    place_of_production, creation_product_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connect = DBDataSource.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(addQuery);
        ) {
            preparedStatement.setInt(1,newProduct.getProductId());
            preparedStatement.setInt(2,newProduct.getSubCategory().getSubCategoryId());
            preparedStatement.setString(3,newProduct.getProductName());
            preparedStatement.setString(4, newProduct.getDescription());
            preparedStatement.setDouble(5,newProduct.getPrice());
            preparedStatement.setDouble(6,newProduct.getWeight());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(newProduct.getProductionDate()));
            preparedStatement.setTimestamp(8, Timestamp.valueOf(newProduct.getExpirationDate()));
            preparedStatement.setInt(9,newProduct.getStockQuantity());
            preparedStatement.setString(10,newProduct.getPlaceOfProduction());
            preparedStatement.setTimestamp(11, Timestamp.valueOf(newProduct.getCreationProductDate()));
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
                double weight = resultSet.getDouble("weight");
                Timestamp productionDate = resultSet.getTimestamp("production_date");
                Timestamp expirationDate = resultSet.getTimestamp("expiration_date");
                int stockQuantity = resultSet.getInt("stock_quantity");
                String placeOfProduction = resultSet.getString("place_of_production");
                Timestamp creationProductDate = resultSet.getTimestamp("creation_product_date");
                System.out.println(
                        "Product ID: " + productId +
                                ", Subcategory ID: " + subcategoryId +
                                ", Product Name: " + product_name +
                                ", Description: " + description +
                                ", Price: " + price +
                                ", Weight: " + weight +
                                ", Production Date: " + productionDate +
                                ", Expiration Date: " + expirationDate +
                                ", Stock Quantity: " + stockQuantity +
                                ", Place of Production: " + placeOfProduction +
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
