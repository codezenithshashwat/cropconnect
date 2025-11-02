package com.agri.farmer;

public class Farmer {
    private int farmer_id;
    private String name;
    private String contact;
    private int reg_no;
    private double size_owned;

    // Default constructor
    public Farmer() {
    }

    // Constructor for creating a new farmer (reg_no is auto-generated)
    public Farmer(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    // Full constructor
    public Farmer(int farmer_id, String name, String contact, int reg_no, double size_owned) {
        this.farmer_id = farmer_id;
        this.name = name;
        this.contact = contact;
        this.reg_no = reg_no;
        this.size_owned = size_owned;
    }

    // Getters and Setters
    public int getFarmer_id() { return farmer_id; }
    public void setFarmer_id(int farmer_id) { this.farmer_id = farmer_id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public int getReg_no() { return reg_no; }
    public void setReg_no(int reg_no) { this.reg_no = reg_no; }
    public double getSize_owned() { return size_owned; }
    public void setSize_owned(double size_owned) { this.size_owned = size_owned; }
}