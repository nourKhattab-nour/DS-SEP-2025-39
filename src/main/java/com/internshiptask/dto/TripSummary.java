package com.internshiptask.dto;

public class TripSummary {
    
private long totalTrips;
    private Double minPrice;
    private Double maxPrice;
    private Double averagePrice;

    public TripSummary() {}
    public TripSummary(long totalTrips, Double minPrice, Double maxPrice, Double averagePrice) {
        this.totalTrips = totalTrips; this.minPrice = minPrice; this.maxPrice = maxPrice; this.averagePrice = averagePrice;
    }
    // getters/setters...
    public long getTotalTrips() { return totalTrips; }
    public void setTotalTrips(long totalTrips) { this.totalTrips = totalTrips; }
    public Double getMinPrice() { return minPrice; }
    public void setMinPrice(Double minPrice) { this.minPrice = minPrice; }
    public Double getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Double maxPrice) { this.maxPrice = maxPrice; }
    public Double getAveragePrice() { return averagePrice; }
    public void setAveragePrice(Double averagePrice) { this.averagePrice = averagePrice; }
}
