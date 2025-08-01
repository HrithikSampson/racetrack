package com.example.geektrust.service;

import com.example.geektrust.domain.Booking;
import com.example.geektrust.domain.TrackConfiguration;
import com.example.geektrust.domain.TrackType;
import com.example.geektrust.domain.Vehicle;
import com.example.geektrust.repository.BookingRepository;

import java.time.LocalTime;
import java.util.List;

public class TrackOccupancyService {
    private final BookingRepository bookingRepository;
    
    public TrackOccupancyService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    
    public boolean isTrackAvailable(TrackType trackType, Vehicle vehicle, LocalTime entryTime) {
        if (!TrackConfiguration.isVehicleAllowedOnTrack(vehicle, trackType)) {
            return false;
        }
        
        TrackConfiguration.TrackInfo trackInfo = TrackConfiguration.getTrackInfo(trackType, vehicle);
        List<Booking> existingBookings = bookingRepository.findByTrackTypeAndVehicle(trackType, vehicle);
        
        long overlappingBookings = existingBookings.stream()
                .filter(booking -> hasTimeOverlap(booking, entryTime))
                .count();
        
        return overlappingBookings < trackInfo.getMaxVehicles();
    }
    
    private boolean hasTimeOverlap(Booking booking, LocalTime newEntryTime) {
        LocalTime newExitTime = newEntryTime.plusHours(booking.getDefaultBookingHours());
        LocalTime existingEntryTime = booking.getEntryTime();
        LocalTime existingExitTime = booking.getExitTime();
        
        return !(newExitTime.isBefore(existingEntryTime) || newEntryTime.isAfter(existingExitTime));
    }
} 