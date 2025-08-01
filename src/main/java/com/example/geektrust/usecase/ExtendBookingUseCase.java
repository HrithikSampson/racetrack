package com.example.geektrust.usecase;

import com.example.geektrust.domain.Booking;
import com.example.geektrust.repository.BookingRepository;
import com.example.geektrust.service.TimeValidationService;

import java.time.LocalTime;
import java.util.Optional;

public class ExtendBookingUseCase {
    private final BookingRepository bookingRepository;
    private final TimeValidationService timeValidationService;
    
    public ExtendBookingUseCase(BookingRepository bookingRepository, TimeValidationService timeValidationService) {
        this.bookingRepository = bookingRepository;
        this.timeValidationService = timeValidationService;
    }
    
    public String execute(String vehicleNumber, String exitTime) {
        if (!timeValidationService.isValidExitTime(exitTime)) {
            return "INVALID_EXIT_TIME";
        }
        
        Optional<Booking> bookingOpt = bookingRepository.findByVehicleNumber(vehicleNumber);
        if (bookingOpt.isEmpty()) {
            return "INVALID_EXIT_TIME";
        }
        
        Booking booking = bookingOpt.get();
        LocalTime newExitTime = timeValidationService.parseTime(exitTime);
        
        if (newExitTime.isBefore(booking.getEntryTime()) || newExitTime.equals(booking.getEntryTime())) {
            return "INVALID_EXIT_TIME";
        }
        
        booking.setExitTime(newExitTime);
        bookingRepository.update(booking);
        
        return "SUCCESS";
    }
} 