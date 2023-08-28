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
import java.util.Locale;

public class StockJDBC implements MarketDataAccess {
    @Override
    public void addCategory(Category newCategory) {
        String addQuery = "INSERT INTO category (categoryid, categoryname, description, creationDate, updateDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)
        ) {
            preparedStatement.setInt(1, newCategory.getCategoryId());
            preparedStatement.setString(2, newCategory.getCategoryName().toUpperCase());
            preparedStatement.setString(3, newCategory.getDescription().toUpperCase());
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
            preparedStatement.setString(3, newSubCategory.getSubCategoryName().toUpperCase());
            preparedStatement.setString(4, newSubCategory.getDescription().toUpperCase());
            LocalDateTime localDateTime = newSubCategory.getCreationSubCategoryDate();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            preparedStatement.setTimestamp(5, timestamp);
            LocalDateTime localDateTime1 = newSubCategory.getUpdateDate();
            Timestamp timestamp1 = Timestamp.valueOf(localDateTime1);
            preparedStatement.setTimestamp(6, timestamp1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            preparedStatement.setString(3, newProduct.getProductName().toUpperCase());
            preparedStatement.setString(4, newProduct.getDescription().toUpperCase());
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
            e.printStackTrace();
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
                                ", Product Name: " + productName.toUpperCase(Locale.UK) +
                                ", Description: " + description.toUpperCase(Locale.UK) +
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
                                ", Category Name: " + categoryName.toUpperCase(Locale.UK) +
                                ", Description: " + description.toUpperCase(Locale.UK) +
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
                                ", Subcategory Name: " + subCategoryName.toUpperCase(Locale.UK) +
                                ", Description: " + description.toUpperCase(Locale.UK) +
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
                String subCategoryName = resultSet.getString("subcategoryname");
                String subCategoryDescription = resultSet.getString("description");
                SubCategory subCategory = new SubCategory(category, subCategoryName, subCategoryDescription);
                Product product = new Product(subCategory,
                        description.toUpperCase(Locale.UK),
                        productName.toUpperCase(Locale.UK),
                        price,
                        year,
                        month,
                        day,
                        stockQuantity,
                        year1,
                        month1,
                        day1);
                productList.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }


    @Override
    public ArrayList<Product> getProductsBySubCategory(SubCategory subCategory) {
        ArrayList<Product> productList = new ArrayList<>();
        String query = "SELECT p.*, c.categoryName, c.description AS categoryDescription " +
                "FROM Product p " +
                "INNER JOIN SubCategory sc ON p.subCategoryId = sc.subCategoryId " +
                "INNER JOIN Category c ON sc.categoryId = c.categoryId " +
                "WHERE sc.subCategoryId = ?";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, subCategory.getSubCategoryId());
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
                Product product = new Product(subCategory,
                        description.toUpperCase(Locale.UK),
                        productName.toUpperCase(Locale.UK),
                        price,
                        year,
                        month,
                        day,
                        stockQuantity,
                        year1,
                        month1,
                        day1);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }


    @Override
    public ArrayList<Product> getExpiredProducts() {
        ArrayList<Product> expiredProducts = new ArrayList<>();
        String query = "SELECT p.*, sc.subCategoryId, sc.subCategoryName " +
                "FROM Product p " +
                "INNER JOIN SubCategory sc ON p.subCategoryId = sc.subCategoryId " +
                "WHERE expirationDate < NOW()";
        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
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

                String subCategoryName = resultSet.getString("subCategoryName");
                SubCategory subCategory = new SubCategory(null, subCategoryName, null);

                Product product = new Product(subCategory,
                        description.toUpperCase(Locale.UK),
                        productName.toUpperCase(Locale.UK),
                        price,
                        year,
                        month,
                        day,
                        stockQuantity,
                        year1,
                        month1,
                        day1);
                product.setProductId(productId);
                expiredProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expiredProducts;
    }


    @Override
    public ArrayList<Product> getProductsInStock() {
        ArrayList<Product> productList = new ArrayList<>();
        String query = "SELECT p.*, sc.subCategoryName, c.categoryName, c.description AS categoryDescription " +
                "FROM Product p " +
                "INNER JOIN SubCategory sc ON p.subCategoryId = sc.subCategoryId " +
                "INNER JOIN Category c ON sc.categoryId = c.categoryId " +
                "WHERE p.stockQuantity > 0";

        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
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

                String subCategoryName = resultSet.getString("subCategoryName");
                String subCategoryDescription = resultSet.getString("description");
                SubCategory subCategory = new SubCategory(null, subCategoryName, subCategoryDescription);

                Product product = new Product(subCategory,
                        description.toUpperCase(Locale.UK),
                        productName.toUpperCase(Locale.UK),
                        price,
                        year,
                        month,
                        day,
                        stockQuantity,
                        year1,
                        month1,
                        day1);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }


    @Override
    public boolean updateStockQuantity(int productId, int newStockQuantity) {
        String query = "UPDATE Product SET stockQuantity = ? WHERE productId = ?";

        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, newStockQuantity);
            preparedStatement.setInt(2, productId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
        ArrayList<Product> outOfStockProducts = new ArrayList<>();
        String query = "SELECT p.*, sc.subCategoryId, sc.subCategoryName, c.categoryId, c.categoryName, c.description AS categoryDescription " +
                "FROM Product p " +
                "INNER JOIN SubCategory sc ON p.subCategoryId = sc.subCategoryId " +
                "INNER JOIN Category c ON sc.categoryId = c.categoryId " +
                "WHERE p.stockQuantity = 0";

        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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

                String subCategoryName = resultSet.getString("subcategoryname");
                String subCategoryDescription = resultSet.getString("description");
                SubCategory subCategory = new SubCategory(null, subCategoryName, subCategoryDescription);

                Product product = new Product(subCategory,
                        description.toUpperCase(Locale.UK),
                        productName.toUpperCase(Locale.UK),
                        price,
                        year,
                        month,
                        day,
                        stockQuantity,
                        year1,
                        month1,
                        day1);
                outOfStockProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return outOfStockProducts;
    }

    @Override
    public ArrayList<Product> findProductByName(String productName) {
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT p.*, sc.subCategoryId, sc.subCategoryName, c.categoryId, c.categoryName, c.description AS categoryDescription " +
                "FROM Product p " +
                "INNER JOIN SubCategory sc ON p.subCategoryId = sc.subCategoryId " +
                "INNER JOIN Category c ON sc.categoryId = c.categoryId " +
                "WHERE p.productName LIKE ?";

        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + productName.toUpperCase() + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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

                String subCategoryName = resultSet.getString("subcategoryname");
                String subCategoryDescription = resultSet.getString("description");
                SubCategory subCategory = new SubCategory(null, subCategoryName, subCategoryDescription);

                Product product = new Product(subCategory,
                        description.toUpperCase(Locale.UK),
                        productName.toUpperCase(Locale.UK),
                        price,
                        year,
                        month,
                        day,
                        stockQuantity,
                        year1,
                        month1,
                        day1);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product deleteProduct(Product product) {
        String query = "DELETE FROM Product WHERE productId = ?";

        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, product.getProductId());
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                return product;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Product updateProduct(Product product, int id) {
        String query = "UPDATE Product " +
                "SET subCategoryId = ?, " +
                "    description = ?, " +
                "    productName = ?, " +
                "    price = ?, " +
                "    productionDate = ?, " +
                "    stockQuantity = ?, " +
                "    expirationDate = ? " +
                "WHERE productId = ?";

        try (Connection connection = DBDataSource.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, product.getSubCategory().getSubCategoryId());
            preparedStatement.setString(2, product.getDescription().toUpperCase(Locale.UK));
            preparedStatement.setString(3, product.getProductName().toUpperCase(Locale.UK));
            preparedStatement.setDouble(4, product.getPrice());
            LocalDate localDate = product.getProductionDate();
            Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
            preparedStatement.setTimestamp(5, timestamp);
            preparedStatement.setInt(6, product.getStockQuantity());
            LocalDate localDate1 = product.getExpirationDate();
            Timestamp timestamp1 = Timestamp.valueOf(localDate1.atStartOfDay());
            preparedStatement.setTimestamp(7, timestamp1);

            preparedStatement.setInt(8, id);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return product;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
