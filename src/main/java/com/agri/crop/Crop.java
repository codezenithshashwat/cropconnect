package com.agri.crop;

public class Crop {
    private int crop_id;
    private String name;
    private String type;
    private String season;
    private int duration;

    // Default constructor
    public Crop() {}

    // Getters and Setters
    public int getCrop_id() { return crop_id; }
    public void setCrop_id(int crop_id) { this.crop_id = crop_id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
}