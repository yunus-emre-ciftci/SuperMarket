package com.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Category {
    private int categoryId;
    private String categoryName;
    private String description;
    private Date creationDate;
    private Date updateDate;
    private static int counter = 1;


    public Category(String categoryName, String description) {
        this.categoryId = counter++;
        this.categoryName = categoryName;
        this.description = description;
        this.creationDate = new Date();
        this.updateDate = new Date();
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


    public Date getCreationDate() {
        return creationDate;
    }


    public Date getUpdateDate() {
        return updateDate;
    }



    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
