package com.agri.market;

public class Product {
    private int product_id;
    private String name;
    private String type; // 'raw' or 'processed'
    private double unit_price;

    // Default constructor
    public Product() {}

    // Getters and Setters
    public int getProduct_id() { return product_id; }
    public void setProduct_id(int product_id) { this.product_id = product_id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getUnit_price() { return unit_price; }
    public void setUnit_price(double unit_price) { this.unit_price = unit_price; }
}