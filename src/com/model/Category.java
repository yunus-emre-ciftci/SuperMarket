package com.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Category {
    private int categoryID;
    private String categoryName;
    private String description;
    private String creationDate;
    private String updateDate;
    private static int IDcounter = 1;

    public Category(int categoryID, String categoryName, String description) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.creationDate = now.format(dateTimeFormatter);
    }

    public Category(String categoryName, String description) {
        this.categoryID = IDcounter++;
        this.categoryName = categoryName;
        this.description = description;

    }


    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

    public int getCategoryID() {
        return categoryID;
    }


    public String getCategoryName() {
        return categoryName;
    }


    public String getDescription() {
        return description;
    }


    public String getCreationDate() {
        return creationDate;
    }


    public String getUpdateDate() {
        return updateDate;
    }


    public static int getIDcounter() {
        return IDcounter;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return categoryID == category.categoryID && Objects.equals(categoryName, category.categoryName) && Objects.equals(description, category.description) && Objects.equals(creationDate, category.creationDate) && Objects.equals(updateDate, category.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, categoryName, description, creationDate, updateDate);
    }
}
