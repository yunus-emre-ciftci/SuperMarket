package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private static List<Category> categories = new ArrayList<>();
    private int categoryId;
    private String categoryName;
    private String description;
    private String creationDate;
    private String updateDate;
    private static int IDcounter = 1;

    public Category(int categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.creationDate = now.format(dateTimeFormatter);
        categories.add(this);
    }

    public Category(String categoryName, String description) {
        this.categoryId = IDcounter++;
        this.categoryName = categoryName;
        this.description = description;
        categories.add(this);
    }


    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

    public int getCategoryId() {
        return categoryId;
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
        return categoryId == category.categoryId && Objects.equals(categoryName, category.categoryName) && Objects.equals(description, category.description) && Objects.equals(creationDate, category.creationDate) && Objects.equals(updateDate, category.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, description, creationDate, updateDate);
    }
}
