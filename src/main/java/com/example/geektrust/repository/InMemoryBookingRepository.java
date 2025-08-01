package com.example.geektrust.repository;

import com.example.geektrust.domain.Booking;
import com.example.geektrust.domain.TrackType;
import com.example.geektrust.domain.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryBookingRepository implements BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();
    
    @Override
    public void save(Booking booking) {
        bookings.put(booking.getVehicleNumber(), booking);
    }
    
    @Override
    public Optional<Booking> findByVehicleNumber(String vehicleNumber) {
        return Optional.ofNullable(bookings.get(vehicleNumber));
    }
    
    @Override
    public List<Booking> findByTrackTypeAndVehicle(TrackType trackType, Vehicle vehicle) {
        return bookings.values().stream()
                .filter(booking -> booking.getTrackType() == trackType && booking.getVehicle() == vehicle)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings.values());
    }
    
    @Override
    public void update(Booking booking) {
        bookings.put(booking.getVehicleNumber(), booking);
    }
} 