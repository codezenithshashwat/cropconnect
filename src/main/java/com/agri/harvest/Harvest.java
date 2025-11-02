package com.agri.harvest;

import java.sql.Date;

public class Harvest {
    // From Harvest table
    private int harvest_id;
    private Date date;
    private double total_yield;
    private int warehouse_id;
    private Date store_date;

    // From Belongs_To table
    private int crop_id;
    private int plot_id;

    // For display
    private String crop_name;
    private String plot_location;
    private String warehouse_name;

    // Default constructor
    public Harvest() {}

    // Getters and Setters
    public int getHarvest_id() { return harvest_id; }
    public void setHarvest_id(int harvest_id) { this.harvest_id = harvest_id; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public double getTotal_yield() { return total_yield; }
    public void setTotal_yield(double total_yield) { this.total_yield = total_yield; }
    public int getWarehouse_id() { return warehouse_id; }
    public void setWarehouse_id(int warehouse_id) { this.warehouse_id = warehouse_id; }
    public Date getStore_date() { return store_date; }
    public void setStore_date(Date store_date) { this.store_date = store_date; }
    public int getCrop_id() { return crop_id; }
    public void setCrop_id(int crop_id) { this.crop_id = crop_id; }
    public int getPlot_id() { return plot_id; }
    public void setPlot_id(int plot_id) { this.plot_id = plot_id; }
    public String getCrop_name() { return crop_name; }
    public void setCrop_name(String crop_name) { this.crop_name = crop_name; }
    public String getPlot_location() { return plot_location; }
    public void setPlot_location(String plot_location) { this.plot_location = plot_location; }
    public String getWarehouse_name() { return warehouse_name; }
    public void setWarehouse_name(String warehouse_name) { this.warehouse_name = warehouse_name; }
}