package com.agri.harvest;

public class Warehouse {
    private int warehouse_id;
    private String name;
    private String city;
    private double total_capacity;
    private double left_capacity;

    // Default constructor
    public Warehouse() {}

    // Getters and Setters
    public int getWarehouse_id() { return warehouse_id; }
    public void setWarehouse_id(int warehouse_id) { this.warehouse_id = warehouse_id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public double getTotal_capacity() { return total_capacity; }
    public void setTotal_capacity(double total_capacity) { this.total_capacity = total_capacity; }
    public double getLeft_capacity() { return left_capacity; }
    public void setLeft_capacity(double left_capacity) { this.left_capacity = left_capacity; }
}