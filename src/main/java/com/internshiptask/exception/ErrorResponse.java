package com.internshiptask.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private List<String> details;

    public ErrorResponse() {}
    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }
    // getters/setters...
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public List<String> getDetails() { return details; }
    public void setDetails(List<String> details) { this.details = details; }
}
