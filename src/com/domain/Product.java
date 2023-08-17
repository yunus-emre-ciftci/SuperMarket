package com.domain;

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
    private double weight;
    private String productionDate;
    private String expirationDate;
    private int stockQuantity;
    private String placeOfProduction;
    private LocalDateTime creationProductDate;
    private LocalDateTime updateDate;
    private static List<Product> allProducts = new ArrayList<>();


    public Product(SubCategory subCategory, int productId, String productName, double price, double weight, int stockQuantity, String placeOfProduction, String expirationDate) {
        this.subCategory = subCategory;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.stockQuantity = stockQuantity;
        this.placeOfProduction = placeOfProduction;
        this.expirationDate = expirationDate;
        this.creationProductDate = LocalDateTime.now();
        allProducts.add(this);

    }

    public Product(SubCategory subCategory, int productId, String productName, String description, double price, double weight, int stockQuantity, String placeOfProduction, String expirationDate) {
        this(subCategory, productId, productName, price, weight, stockQuantity, placeOfProduction, expirationDate);
        this.description = description;
        this.creationProductDate = LocalDateTime.now();
        allProducts.add(this);
    }

    public List<Product> getAllProducts() {
        return allProducts;
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

    public double getWeight() {
        return weight;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getPlaceOfProduction() {
        return placeOfProduction;
    }

    public LocalDateTime getCreationProductDate() {
        return creationProductDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
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

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setPlaceOfProduction(String placeOfProduction) {
        this.placeOfProduction = placeOfProduction;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "subCategory=" + subCategory +
                ", barcode=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", productionDate='" + productionDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", placeOfProduction='" + placeOfProduction + '\'' +
                ", creationProductDate=" + creationProductDate +
                ", updateDate=" + updateDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productId == product.productId && Double.compare(price, product.price) == 0 && Double.compare(weight, product.weight) == 0 && stockQuantity == product.stockQuantity && Objects.equals(subCategory, product.subCategory) && Objects.equals(productName, product.productName) && Objects.equals(description, product.description) && Objects.equals(productionDate, product.productionDate) && Objects.equals(expirationDate, product.expirationDate) && Objects.equals(placeOfProduction, product.placeOfProduction) && Objects.equals(creationProductDate, product.creationProductDate) && Objects.equals(updateDate, product.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategory, productId, productName, description, price, weight, productionDate, expirationDate, stockQuantity, placeOfProduction, creationProductDate, updateDate);
    }
}
