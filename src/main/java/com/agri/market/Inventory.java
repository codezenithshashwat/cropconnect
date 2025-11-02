package com.agri.market;

public class Inventory {
    private int inventory_id;
    private int warehouse_id;
    private int product_id;
    private double qty;
    private String storage_condition;

    // For display
    private String warehouse_name;
    private String product_name;

    // Default constructor
    public Inventory() {}

    // Getters and Setters
    public int getInventory_id() { return inventory_id; }
    public void setInventory_id(int inventory_id) { this.inventory_id = inventory_id; }
    public int getWarehouse_id() { return warehouse_id; }
    public void setWarehouse_id(int warehouse_id) { this.warehouse_id = warehouse_id; }
    public int getProduct_id() { return product_id; }
    public void setProduct_id(int product_id) { this.product_id = product_id; }
    public double getQty() { return qty; }
    public void setQty(double qty) { this.qty = qty; }
    public String getStorage_condition() { return storage_condition; }
    public void setStorage_condition(String storage_condition) { this.storage_condition = storage_condition; }
    public String getWarehouse_name() { return warehouse_name; }
    public void setWarehouse_name(String warehouse_name) { this.warehouse_name = warehouse_name; }
    public String getProduct_name() { return product_name; }
    public void setProduct_name(String product_name) { this.product_name = product_name; }
}