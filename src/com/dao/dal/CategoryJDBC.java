package com.dao.dal;

import com.dao.CategoryDataAccess;
import com.domain.Category;
import com.util.DBDataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Locale;

public class CategoryJDBC implements CategoryDataAccess {
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
}
