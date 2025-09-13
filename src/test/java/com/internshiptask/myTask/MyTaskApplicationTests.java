package com.internshiptask.myTask;

import com.internshiptask.dto.TripRequest;
import com.internshiptask.model.Trip;
import com.internshiptask.model.TripStatus;
import com.internshiptask.repository.TripRepository;
import com.internshiptask.service.impl.TripServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MyTaskApplicationTests {

    @Mock
    private TripRepository repo;

    @InjectMocks
    private TripServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_and_getById() {
        // Arrange
        TripRequest req = new TripRequest();
        req.setDestination("Paris");
        req.setStartDate(LocalDate.of(2025, 9, 10));
        req.setEndDate(LocalDate.of(2025, 9, 20));
        req.setPrice(1500.0);
        req.setStatus(TripStatus.PLANNED);

        Trip saved = new Trip("Paris", req.getStartDate(), req.getEndDate(), 1500.0, TripStatus.PLANNED);
        saved.setId(1);

        when(repo.save(any(Trip.class))).thenReturn(saved);
        when(repo.findById(1)).thenReturn(Optional.of(saved));

        // Act
        var created = service.create(req);
        var fetched = service.getById(1);

        // Assert
        assertEquals("Paris", created.getDestination());
        assertEquals(1, created.getId());
        assertEquals("Paris", fetched.getDestination());
    }
}
