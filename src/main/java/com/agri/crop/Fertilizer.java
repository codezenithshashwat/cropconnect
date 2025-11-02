package com.agri.crop;

public class Fertilizer {
    private int fertilizer_id;
    private String name;
    private String composition;

    // Default constructor
    public Fertilizer() {}

    // Getters and Setters
    public int getFertilizer_id() { return fertilizer_id; }
    public void setFertilizer_id(int fertilizer_id) { this.fertilizer_id = fertilizer_id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getComposition() { return composition; }
    public void setComposition(String composition) { this.composition = composition; }
}