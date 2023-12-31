package com.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class SubCategory {
    private Category category;
    private int subCategoryId;
    private String subCategoryName;
    private String description;
    private LocalDateTime creationSubCategoryDate;
    private LocalDateTime updateSubCategoryDate;
    private static int counter = 1;


    public SubCategory(Category category, String subCategoryName, String description) {
        this.category = category;
        this.subCategoryId = counter++;
        this.subCategoryName = subCategoryName;
        this.description = description;
        this.creationSubCategoryDate = LocalDateTime.now();
        this.updateSubCategoryDate = LocalDateTime.now();

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

    public LocalDateTime getUpdateDate() {
        return updateSubCategoryDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateSubCategoryDate = updateDate;
    }

    public LocalDateTime getCreationSubCategoryDate() {
        return creationSubCategoryDate;
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
