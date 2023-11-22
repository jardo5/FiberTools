package com.fibertools.models;

public class Inventory {

    private int id;
    private String serialNumber;
    private String name;
    private String type;
    private String description;
    private int quantity;
    private double price;
    private String assignedJob;
    private String lastUpdated;

    public Inventory(int id, String serialNumber, String name, String type, String description, int quantity, double price, String assignedJob, String lastUpdated) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.name = name;
        this.type = type;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.assignedJob = assignedJob;
        this.lastUpdated = lastUpdated;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public String getAssignedJob() {
        return assignedJob;
    }
    public String getLastUpdated() {
        return lastUpdated;
    }
    //Getters End

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setAssignedJob(String assignedJob) {
        this.assignedJob = assignedJob;
    }
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    //Setters End

}
