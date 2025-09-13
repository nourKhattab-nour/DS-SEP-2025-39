package com.internshiptask.dto;


import java.time.LocalDate;

import com.internshiptask.model.TripStatus;
import com.internshiptask.validation.ValidDateRange;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.*;

@ValidDateRange
public class TripRequest {
    @NotBlank(message = "destination cannot be empty")
    private String destination;

    @NotNull(message = "startDate cannot be null")
    private LocalDate startDate;

    @NotNull(message = "endDate cannot be null")
    private LocalDate endDate;

    @NotNull(message = "price is required")
    @Positive(message = "price must be positive")
    private Double price;

    @NotNull(message = "status is required")
    private TripStatus status;

    // getters/setters
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
