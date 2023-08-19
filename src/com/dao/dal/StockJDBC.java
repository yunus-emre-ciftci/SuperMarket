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
        String addQuery = "INSERT INTO category (categoryid, categoryname, description, creationDate, updateDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)
        ) {
            preparedStatement.setInt(1, newCategory.getCategoryId());
            preparedStatement.setString(2, newCategory.getCategoryName());
            preparedStatement.setString(3, newCategory.getDescription());
            preparedStatement.setTimestamp(4, new Timestamp(newCategory.getCreationDate().getTime()));
            preparedStatement.setTimestamp(5, new Timestamp(newCategory.getUpdateDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSubCategory(SubCategory newSubCategory) {
        String addQuery = "INSERT INTO subcategory (subcategoryid, categoryid, subcategoryname, description, creationDate, updateDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)
        ) {
            preparedStatement.setInt(1, newSubCategory.getSubCategoryId());
            preparedStatement.setInt(2, newSubCategory.getCategory().getCategoryId());
            preparedStatement.setString(3, newSubCategory.getSubCategoryName());
            preparedStatement.setString(4, newSubCategory.getDescription());
            preparedStatement.setTimestamp(5, new Timestamp(newSubCategory.getCreationSubCategoryDate().getTime()));
            preparedStatement.setTimestamp(6, new Timestamp(newSubCategory.getUpdateDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void addProduct(Product newProduct) {
        String addQuery = "INSERT INTO product (productid, subcategoryid, productname, description, price, productiondate, expirationdate, stockquantity, creationproductdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)
        ) {
            preparedStatement.setInt(1, newProduct.getProductId());
            preparedStatement.setInt(2, newProduct.getSubCategory().getSubCategoryId());
            preparedStatement.setString(3, newProduct.getProductName());
            preparedStatement.setString(4, newProduct.getDescription());
            preparedStatement.setDouble(5, newProduct.getPrice());
            preparedStatement.setDate(6, Date.valueOf(newProduct.getProductionDate()));
            preparedStatement.setDate(7, Date.valueOf(newProduct.getExpirationDate()));
            preparedStatement.setInt(8, newProduct.getStockQuantity());
            preparedStatement.setTimestamp(9, newProduct.getCreationProductDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void printAllProduct() {


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
