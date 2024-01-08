package com.example.Iprwcbackend.module.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    @NotBlank(message = "Product naam is verplicht")
    private String productName;
    @NotBlank(message = "Categorie is verplicht")
    private String category;
    @Min(value = 0, message = "Hoeveelheid moet groter of gelijk aan 0 zijn")
    private int amount;
    @Min(value = 0, message = "Prijs moet groter of gelijk aan 0 zijn")
    private double price;
    @NotBlank(message = "Link is verplicht")
    private String imagePath;

    public Product() {
    }

    public Product(String productName, String category, int amount, double price, String imagePath) {
        this.productName = productName;
        this.category = category;
        this.amount = amount;
        this.price = price;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
