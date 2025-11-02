package com.agri.market;

import java.sql.Date;

public class Shipment {
    private int product_id;
    private int market_id;
    private String vehicle_id;
    private Date date;

    // For display
    private String product_name;
    private String market_name;

    // Default constructor
    public Shipment() {}

    // Getters and Setters
    public int getProduct_id() { return product_id; }
    public void setProduct_id(int product_id) { this.product_id = product_id; }
    public int getMarket_id() { return market_id; }
    public void setMarket_id(int market_id) { this.market_id = market_id; }
    public String getVehicle_id() { return vehicle_id; }
    public void setVehicle_id(String vehicle_id) { this.vehicle_id = vehicle_id; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getProduct_name() { return product_name; }
    public void setProduct_name(String product_name) { this.product_name = product_name; }
    public String getMarket_name() { return market_name; }
    public void setMarket_name(String market_name) { this.market_name = market_name; }
}