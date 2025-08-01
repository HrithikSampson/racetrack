package com.example.geektrust.service;

import com.example.geektrust.domain.Booking;
import com.example.geektrust.domain.TrackConfiguration;
import com.example.geektrust.domain.TrackType;
import com.example.geektrust.repository.BookingRepository;

import java.time.Duration;
import java.util.List;

public class RevenueCalculationService {
    private final BookingRepository bookingRepository;
    
    public RevenueCalculationService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    
    public int calculateRevenueForTrack(TrackType trackType) {
        List<Booking> bookings = bookingRepository.findAll();
        
        return bookings.stream()
                .filter(booking -> booking.getTrackType() == trackType)
                .mapToInt(this::calculateBookingRevenue)
                .sum();
    }
    
    private int calculateBookingRevenue(Booking booking) {
        TrackConfiguration.TrackInfo trackInfo = TrackConfiguration.getTrackInfo(booking.getTrackType(), booking.getVehicle());
        Duration duration = Duration.between(booking.getEntryTime(), booking.getExitTime());
        long hours = duration.toHours();
        
        if (duration.toMinutes() % 60 > 0) {
            hours++;
        }
        
        return (int) (hours * trackInfo.getCostPerHour());
    }
} 