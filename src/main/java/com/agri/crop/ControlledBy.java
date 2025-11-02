package com.agri.crop;

import java.sql.Date;

public class ControlledBy {
    private int crop_id;
    private int fertilizer_id;
    private Date date_app;

    // Extra fields for display
    private String crop_name;
    private String fertilizer_name;

    // Default constructor
    public ControlledBy() {}

    // Getters and Setters
    public int getCrop_id() { return crop_id; }
    public void setCrop_id(int crop_id) { this.crop_id = crop_id; }
    public int getFertilizer_id() { return fertilizer_id; }
    public void setFertilizer_id(int fertilizer_id) { this.fertilizer_id = fertilizer_id; }
    public Date getDate_app() { return date_app; }
    public void setDate_app(Date date_app) { this.date_app = date_app; }
    public String getCrop_name() { return crop_name; }
    public void setCrop_name(String crop_name) { this.crop_name = crop_name; }
    public String getFertilizer_name() { return fertilizer_name; }
    public void setFertilizer_name(String fertilizer_name) { this.fertilizer_name = fertilizer_name; }
}