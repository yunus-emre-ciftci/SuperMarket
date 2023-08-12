package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Product {
    SubCategory subCategory;
    private int barcode;
    private String productName;
    private String description;
    private double price;
    private double weight;
    private String productionDate;
    private String expirationDate;
    private int stockQuantity;
    private String placeOfProduction;
    private String creationProductDate;


    public Product(SubCategory subCategory, int barcode, String productName, String description, double price, double weight, int stockQuantity, String placeOfProduction, String expirationDate) {
        this.subCategory = subCategory;
        this.barcode = barcode;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.stockQuantity = stockQuantity;
        this.placeOfProduction = placeOfProduction;
        this.expirationDate = expirationDate;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.creationProductDate = now.format(dateTimeFormatter);
    }

    public Product(SubCategory subCategory, int barcode, String productName, double price, double weight, int stockQuantity, String placeOfProduction, String expirationDate) {
        this.subCategory = subCategory;
        this.barcode = barcode;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.stockQuantity = stockQuantity;
        this.placeOfProduction = placeOfProduction;
        this.expirationDate = expirationDate;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.creationProductDate = now.format(dateTimeFormatter);
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public int getBarcode() {
        return barcode;
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

    public String getCreationProductDate() {
        return creationProductDate;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
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

    public void setCreationProductDate(String creationProductDate) {
        this.creationProductDate = creationProductDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return barcode == product.barcode && Double.compare(price, product.price) == 0 && Double.compare(weight, product.weight) == 0 && stockQuantity == product.stockQuantity && Objects.equals(subCategory, product.subCategory) && Objects.equals(productName, product.productName) && Objects.equals(description, product.description) && Objects.equals(productionDate, product.productionDate) && Objects.equals(expirationDate, product.expirationDate) && Objects.equals(placeOfProduction, product.placeOfProduction) && Objects.equals(creationProductDate, product.creationProductDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategory, barcode, productName, description, price, weight, productionDate, expirationDate, stockQuantity, placeOfProduction, creationProductDate);
    }
}
