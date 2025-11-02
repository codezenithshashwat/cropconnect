package com.agri.market;

public class Market {
    private int market_id;
    private String name;
    private String city;
    private String type; // 'wholesale' or 'retail'

    // Default constructor
    public Market() {}

    // Getters and Setters
    public int getMarket_id() { return market_id; }
    public void setMarket_id(int market_id) { this.market_id = market_id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}