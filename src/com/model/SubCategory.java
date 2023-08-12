package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubCategory {
    Category category;
    private int subCategoryId;
    private String subCategoryName;
    private String description;
    private static List<SubCategory> subCategories = new ArrayList<>();

    public SubCategory(Category category, int subCategoryId, String subCategoryName, String description) {
        this.category = category;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.description = description;
        subCategories.add(this);
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "category=" + category +
                ", subCategoryId=" + subCategoryId +
                ", subCategoryName=" + subCategoryName +
                ", description='" + description + '\'' +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubCategory)) return false;
        SubCategory that = (SubCategory) o;
        return subCategoryId == that.subCategoryId && subCategoryName == that.subCategoryName && Objects.equals(category, that.category) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, subCategoryId, subCategoryName, description);
    }
}
