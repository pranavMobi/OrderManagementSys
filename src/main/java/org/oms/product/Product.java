package org.oms.product;

public class Product {
    private int productId;
    private String productDescription;
    private int productClassCode;
    private Double productPrice;
    private int productQuantityAvailable;
    private int length;
    private int width;
    private int height;
    private Double weight;

    public Product(int productId, String productDescription, int productClassCode, Double productPrice, int productQuantityAvailable, int length, int width, int height, Double weight) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.productClassCode = productClassCode;
        this.productPrice = productPrice;
        this.productQuantityAvailable = productQuantityAvailable;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductClassCode() {
        return productClassCode;
    }

    public void setProductClassCode(int productClassCode) {
        this.productClassCode = productClassCode;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantityAvailable() {
        return productQuantityAvailable;
    }

    public void setProductQuantityAvailable(int productQuantityAvailable) {
        this.productQuantityAvailable = productQuantityAvailable;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
