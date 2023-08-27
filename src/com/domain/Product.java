package com.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    private SubCategory subCategory;
    private int productId;
    private String productName;
    private String description;
    private double price;
    private LocalDate productionDate;
    private LocalDate expirationDate;
    private int stockQuantity;
    private LocalDateTime creationProductDate;
    private static int counter = 1;

    public Product(SubCategory subCategory, String description, String productName, double price, int productionDateYear, int productionDateMonths,int productionDateDays, int stockQuantity, int expirationDateYear, int expirationDateMonths,int expirationDateDays) {
        this.description = description;
        this.subCategory = subCategory;
        this.productId = counter++;
        this.productName = productName;
        this.price = price;
        this.productionDate = LocalDate.of(productionDateYear,productionDateMonths,productionDateDays);
        this.expirationDate = LocalDate.of(expirationDateYear,expirationDateMonths,expirationDateDays);
        this.stockQuantity = stockQuantity;
        this.creationProductDate = LocalDateTime.now();

    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }


    public LocalDateTime getCreationProductDate() {
        return creationProductDate;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "subCategory=" + subCategory +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productionDate='" + productionDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", creationProductDate=" + creationProductDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productId == product.productId && Double.compare(price, product.price) == 0 && stockQuantity == product.stockQuantity && Objects.equals(subCategory, product.subCategory) && Objects.equals(productName, product.productName) && Objects.equals(description, product.description) && Objects.equals(productionDate, product.productionDate) && Objects.equals(expirationDate, product.expirationDate) && Objects.equals(creationProductDate, product.creationProductDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategory, productId, productName, description, price, productionDate, expirationDate, stockQuantity, creationProductDate);
    }
}
