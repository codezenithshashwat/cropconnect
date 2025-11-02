package com.agri.crop;

import java.sql.Date;

public class Grows {
    private int crop_id;
    private int plot_id;
    private Date start_date;
    private Date end_date;

    // Extra fields for display
    private String crop_name;
    private String plot_location;
    private String farmer_name;

    // Default constructor
    public Grows() {}

    // Getters and Setters
    public int getCrop_id() { return crop_id; }
    public void setCrop_id(int crop_id) { this.crop_id = crop_id; }
    public int getPlot_id() { return plot_id; }
    public void setPlot_id(int plot_id) { this.plot_id = plot_id; }
    public Date getStart_date() { return start_date; }
    public void setStart_date(Date start_date) { this.start_date = start_date; }
    public Date getEnd_date() { return end_date; }
    public void setEnd_date(Date end_date) { this.end_date = end_date; }
    public String getCrop_name() { return crop_name; }
    public void setCrop_name(String crop_name) { this.crop_name = crop_name; }
    public String getPlot_location() { return plot_location; }
    public void setPlot_location(String plot_location) { this.plot_location = plot_location; }
    public String getFarmer_name() { return farmer_name; }
    public void setFarmer_name(String farmer_name) { this.farmer_name = farmer_name; }
}