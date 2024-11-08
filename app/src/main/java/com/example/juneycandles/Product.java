package com.example.juneycandles;

public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private String productImg;

    public Product(int productId, String productName, double productPrice, String productImg) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductImg() {
        return productImg;
    }
}
