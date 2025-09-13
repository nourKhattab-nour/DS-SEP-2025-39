package com.internshiptask.service;

import com.internshiptask.dto.*;
import com.internshiptask.model.TripStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface TripService {
    TripResponse create(TripRequest request);
    Page<TripResponse> getAll(Pageable pageable);
    TripResponse getById(Integer id);
    TripResponse update(Integer id, TripRequest request);
    void delete(Integer id);
    Page<TripResponse> searchByDestination(String destination, Pageable pageable);
    Page<TripResponse> filterByStatus(TripStatus status, Pageable pageable);
    Page<TripResponse> getBetweenDates(LocalDate start, LocalDate end, Pageable pageable);
    TripSummary getSummary();
}
