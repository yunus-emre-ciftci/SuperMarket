package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Products {
    Category category;
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


    public Products(Category category, int barcode, String productName, String description, double price, double weight, int stockQuantity, String placeOfProduction, String expirationDate) {
        this.category = category;
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

    public Products(Category category, int barcode, String productName, double price, double weight, int stockQuantity, String placeOfProduction, String expirationDate) {
        this.category = category;
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

    @Override
    public String toString() {
        return "Products{" +
                "category=" + category +
                ", barcode=" + barcode +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", ProductionDate='" + productionDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", placeOfProduction='" + placeOfProduction + '\'' +
                ", creationProductDate='" + creationProductDate + '\'' +
                '}';
    }

    public Category getCategory() {
        return category;
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

    public void setCategory(Category category) {
        this.category = category;
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
        if (!(o instanceof Products)) return false;
        Products products = (Products) o;
        return barcode == products.barcode && Double.compare(price, products.price) == 0 && Double.compare(weight, products.weight) == 0 && stockQuantity == products.stockQuantity && Objects.equals(category, products.category) && Objects.equals(productName, products.productName) && Objects.equals(description, products.description) && Objects.equals(productionDate, products.productionDate) && Objects.equals(expirationDate, products.expirationDate) && Objects.equals(placeOfProduction, products.placeOfProduction) && Objects.equals(creationProductDate, products.creationProductDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, barcode, productName, description, price, weight, productionDate, expirationDate, stockQuantity, placeOfProduction, creationProductDate);
    }
}
