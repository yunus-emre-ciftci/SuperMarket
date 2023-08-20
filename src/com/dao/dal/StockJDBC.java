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
        String query = "SELECT * FROM product";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                int subCategoryId = resultSet.getInt("subCategoryId");
                String productName = resultSet.getString("productName");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String productionDate = resultSet.getString("productionDate");
                String expirationDate = resultSet.getString("expirationDate");
                int stockQuantity = resultSet.getInt("stockQuantity");
                Timestamp creationProductDate = resultSet.getTimestamp("creationProductDate");

                System.out.println(
                        "Product ID: " + productId +
                                ", Subcategory ID: " + subCategoryId +
                                ", Product Name: " + productName +
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
    public void printAllCategory() {
        String query = "SELECT * FROM category";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");
                String description = resultSet.getString("description");
                Date creationDate = resultSet.getDate("creationDate");
                Date updateDate = resultSet.getDate("updateDate");

                System.out.println(
                        "Category ID: " + categoryId +
                                ", Category Name: " + categoryName +
                                ", Description: " + description +
                                ", Creation Date: " + creationDate +
                                ", Update Date: " + updateDate
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAllSubCategory() {
        String query = "SELECT * FROM subcategory";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int subCategoryId = resultSet.getInt("subCategoryId");
                int categoryId = resultSet.getInt("categoryId");
                String subCategoryName = resultSet.getString("subCategoryName");
                String description = resultSet.getString("description");
                Date creationDate = resultSet.getDate("creationDate");
                Date updateDate = resultSet.getDate("updateDate");

                System.out.println(
                        "Subcategory ID: " + subCategoryId +
                                ", Category ID: " + categoryId +
                                ", Subcategory Name: " + subCategoryName +
                                ", Description: " + description +
                                ", Creation Date: " + creationDate +
                                ", Update Date: " + updateDate
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Product> getProductsByCategory(Category category) {
        return getExpiredProducts();
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
