package com.agri.plot;

public class FarmPlot {
    private int plot_id;
    private double size;
    private String location;
    private String status;
    private int owned_by; // Foreign Key to Farmer
    private String farmer_name; // For display (a 'derived' attribute from a JOIN)

    // Default constructor
    public FarmPlot() {}

    // Getters and Setters
    public int getPlot_id() { return plot_id; }
    public void setPlot_id(int plot_id) { this.plot_id = plot_id; }
    public double getSize() { return size; }
    public void setSize(double size) { this.size = size; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getOwned_by() { return owned_by; }
    public void setOwned_by(int owned_by) { this.owned_by = owned_by; }
    public String getFarmer_name() { return farmer_name; }
    public void setFarmer_name(String farmer_name) { this.farmer_name = farmer_name; }
}