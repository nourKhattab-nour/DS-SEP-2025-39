package com.internshiptask.controller;

import com.internshiptask.dto.*;
import com.internshiptask.model.TripStatus;
import com.internshiptask.service.TripService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/trips")
@Tag(name = "Trips", description = "Trip Management APIs")
public class TripController {

    private final TripService service;

    @Autowired
    public TripController(TripService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TripResponse> create(@Valid @RequestBody TripRequest request) {
        TripResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<TripResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "startDate,asc") String sort) {

        String[] parts = sort.split(",");
        String sortBy = parts[0];
        Sort.Direction dir = parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        Page<TripResponse> result = service.getAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> update(@PathVariable Integer id, @Valid @RequestBody TripRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TripResponse>> search(
            @RequestParam String destination,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable p = PageRequest.of(page, size);
        return ResponseEntity.ok(service.searchByDestination(destination, p));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<TripResponse>> filterByStatus(
            @RequestParam TripStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable p = PageRequest.of(page, size);
        return ResponseEntity.ok(service.filterByStatus(status, p));
    }

    @GetMapping("/daterange")
    public ResponseEntity<Page<TripResponse>> betweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable p = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getBetweenDates(start, end, p));
    }

    @GetMapping("/summary")
    public ResponseEntity<TripSummary> summary() {
        return ResponseEntity.ok(service.getSummary());
    }
}
