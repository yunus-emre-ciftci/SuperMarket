package com.dao.dal;

import com.dao.MarketDataAccess;
import com.domain.Category;
import com.domain.Product;
import com.domain.SubCategory;
import com.util.DBDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
            LocalDateTime creationDate = newCategory.getCreationDate();
            Timestamp creationTimestamp = Timestamp.valueOf(creationDate);
            preparedStatement.setTimestamp(4, creationTimestamp);
            LocalDateTime updateDate = newCategory.getUpdateDate();
            Timestamp updateTimestamp = Timestamp.valueOf(updateDate);
            preparedStatement.setTimestamp(5, updateTimestamp);
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
            LocalDateTime localDateTime = newSubCategory.getCreationSubCategoryDate();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            preparedStatement.setTimestamp(5, timestamp);
            LocalDateTime localDateTime1 = newSubCategory.getUpdateDate();
            Timestamp timestamp1 = Timestamp.valueOf(localDateTime1);
            preparedStatement.setTimestamp(6, timestamp1);
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
            LocalDate localDate = newProduct.getProductionDate();
            Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
            preparedStatement.setTimestamp(6, timestamp);
            LocalDate localDate1 = newProduct.getExpirationDate();
            Timestamp timestamp1 = Timestamp.valueOf(localDate1.atStartOfDay());
            preparedStatement.setTimestamp(7, timestamp1);
            preparedStatement.setInt(8, newProduct.getStockQuantity());
            LocalDateTime creationProductDate = newProduct.getCreationProductDate();
            Timestamp creationTimestamp = Timestamp.valueOf(creationProductDate);
            preparedStatement.setTimestamp(9, creationTimestamp);
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

                Timestamp productionDateTimestamp = resultSet.getTimestamp("productionDate");
                java.sql.Date productionDateSqlDate = new java.sql.Date(productionDateTimestamp.getTime());
                LocalDate productionLocalDate = productionDateSqlDate.toLocalDate();

                Timestamp expirationDateTimestamp = resultSet.getTimestamp("expirationDate");
                java.sql.Date expirationDateSqlDate = new java.sql.Date(expirationDateTimestamp.getTime());
                LocalDate expirationLocalDate = expirationDateSqlDate.toLocalDate();

                int stockQuantity = resultSet.getInt("stockQuantity");
                Timestamp creationProductDate = resultSet.getTimestamp("creationProductDate");

                System.out.println(
                        "Product ID: " + productId +
                                ", Subcategory ID: " + subCategoryId +
                                ", Product Name: " + productName +
                                ", Description: " + description +
                                ", Price: " + price +
                                ", Production Date: " + productionLocalDate +
                                ", Expiration Date: " + expirationLocalDate +
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
                Timestamp creationDate = resultSet.getTimestamp("creationDate");
                Timestamp updateDate = resultSet.getTimestamp("updateDate");

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
                Timestamp creationDate = resultSet.getTimestamp("creationDate");
                Timestamp updateDate = resultSet.getTimestamp("updateDate");

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
        ArrayList<Product> productList = new ArrayList<>();
        String query = "SELECT p.*, sc.subCategoryId, sc.subCategoryName, c.categoryId, c.categoryName, c.description AS categoryDescription " +
                "FROM Product p " +
                "INNER JOIN SubCategory sc ON p.subCategoryId = sc.subCategoryId " +
                "INNER JOIN Category c ON sc.categoryId = c.categoryId " +
                "WHERE c.categoryId = ?";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, category.getCategoryId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productName = resultSet.getString("productName");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dateString = resultSet.getString("productiondate");
                LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

                int year = dateTime.getYear();
                int month = dateTime.getMonthValue();
                int day = dateTime.getDayOfMonth();

                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dateString1 = resultSet.getString("expirationdate");
                LocalDateTime dateTime1 = LocalDateTime.parse(dateString1, formatter1);
                int year1 = dateTime1.getYear();
                int month1 = dateTime1.getMonthValue();
                int day1 = dateTime1.getDayOfMonth();

                int stockQuantity = resultSet.getInt("stockQuantity");
                Timestamp creationProductDate = resultSet.getTimestamp("creationProductDate");
                String subCategoryName = resultSet.getString("subcategoryname");
                String subCategoryDescription = resultSet.getString("description");
                SubCategory subCategory = new SubCategory(category, subCategoryName, subCategoryDescription);

                Product product = new Product(subCategory, description, productName, price, year, month, day, stockQuantity, year1, month1, day1);
                productList.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
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
