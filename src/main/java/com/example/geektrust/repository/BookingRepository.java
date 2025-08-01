package com.example.geektrust.repository;

import com.example.geektrust.domain.Booking;
import com.example.geektrust.domain.TrackType;
import com.example.geektrust.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    void save(Booking booking);
    Optional<Booking> findByVehicleNumber(String vehicleNumber);
    List<Booking> findByTrackTypeAndVehicle(TrackType trackType, Vehicle vehicle);
    List<Booking> findAll();
    void update(Booking booking);
} 