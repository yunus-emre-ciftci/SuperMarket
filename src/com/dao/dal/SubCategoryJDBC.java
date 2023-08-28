package com.dao.dal;

import com.dao.SubCategoryDataAccess;
import com.domain.SubCategory;
import com.util.DBDataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Locale;

public class SubCategoryJDBC implements SubCategoryDataAccess {
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
}
