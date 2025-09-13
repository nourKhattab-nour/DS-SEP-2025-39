package com.internshiptask.repository;

import com.internshiptask.model.Trip;
import com.internshiptask.model.TripStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    Page<Trip> findByDestinationContainingIgnoreCase(String destination, Pageable pageable);
    Page<Trip> findByStatus(TripStatus status, Pageable pageable);
    Page<Trip> findByStartDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
