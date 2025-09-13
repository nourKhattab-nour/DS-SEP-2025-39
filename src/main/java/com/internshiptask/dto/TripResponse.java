package com.internshiptask.dto;

import com.internshiptask.model.TripStatus;
import java.time.LocalDate;

public class TripResponse {
    private Integer id;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private TripStatus status;

    public TripResponse() {}
    public TripResponse(Integer id, String destination, LocalDate startDate, LocalDate endDate, Double price, TripStatus status) {
        this.id = id; this.destination = destination; this.startDate = startDate; this.endDate = endDate; this.price = price; this.status = status;
    }
    // getters/setters...
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public TripStatus getStatus() { return status; }
    public void setStatus(TripStatus status) { this.status = status; }
}
