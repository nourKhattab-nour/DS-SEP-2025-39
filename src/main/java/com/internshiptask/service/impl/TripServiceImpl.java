package com.internshiptask.service.impl;

import com.internshiptask.dto.*;
import com.internshiptask.exception.ResourceNotFoundException;
import com.internshiptask.model.*;
import com.internshiptask.repository.TripRepository;
import com.internshiptask.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository repo;

    @Autowired
    public TripServiceImpl(TripRepository repo) {
        this.repo = repo;
    }

    private TripResponse toResponse(Trip t) {
        return new TripResponse(t.getId(), t.getDestination(), t.getStartDate(), t.getEndDate(), t.getPrice(), t.getStatus());
    }

    private Trip toEntity(TripRequest r) {
        return new Trip(r.getDestination(), r.getStartDate(), r.getEndDate(), r.getPrice(), r.getStatus());
    }

    @Override
    public TripResponse create(TripRequest request) {
        Trip saved = repo.save(toEntity(request));
        return toResponse(saved);
    }

    @Override
    public Page<TripResponse> getAll(Pageable pageable) {
        return repo.findAll(pageable).map(this::toResponse);
    }

    @Override
    public TripResponse getById(Integer id) {
        Trip t = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));
        return toResponse(t);
    }

    @Override
    public TripResponse update(Integer id, TripRequest request) {
        Trip existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));
        existing.setDestination(request.getDestination());
        existing.setStartDate(request.getStartDate());
        existing.setEndDate(request.getEndDate());
        existing.setPrice(request.getPrice());
        existing.setStatus(request.getStatus());
        Trip saved = repo.save(existing);
        return toResponse(saved);
    }

    @Override
    public void delete(Integer id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Trip not found with id " + id);
        repo.deleteById(id);
    }

    @Override
    public Page<TripResponse> searchByDestination(String destination, Pageable pageable) {
        return repo.findByDestinationContainingIgnoreCase(destination, pageable).map(this::toResponse);
    }

    @Override
    public Page<TripResponse> filterByStatus(TripStatus status, Pageable pageable) {
        return repo.findByStatus(status, pageable).map(this::toResponse);
    }

    @Override
    public Page<TripResponse> getBetweenDates(LocalDate start, LocalDate end, Pageable pageable) {
        return repo.findByStartDateBetween(start, end, pageable).map(this::toResponse);
    }

    @Override
    public TripSummary getSummary() {
        var all = repo.findAll();
        DoubleSummaryStatistics stats = all.stream().collect(Collectors.summarizingDouble(Trip::getPrice));
        return new TripSummary(all.size(),
                all.isEmpty() ? null : stats.getMin(),
                all.isEmpty() ? null : stats.getMax(),
                all.isEmpty() ? null : stats.getAverage());
    }
}
