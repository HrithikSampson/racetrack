package com.example.geektrust.usecase;

import com.example.geektrust.domain.Booking;
import com.example.geektrust.domain.TrackType;
import com.example.geektrust.domain.Vehicle;
import com.example.geektrust.repository.BookingRepository;
import com.example.geektrust.service.TimeValidationService;
import com.example.geektrust.service.TrackOccupancyService;
import com.example.geektrust.strategy.TrackAllocationStrategy;

import java.time.LocalTime;

public class BookTrackUseCase {
    private final BookingRepository bookingRepository;
    private final TimeValidationService timeValidationService;
    private final TrackOccupancyService trackOccupancyService;
    private final TrackAllocationStrategy trackAllocationStrategy;
    
    public BookTrackUseCase(BookingRepository bookingRepository, 
                           TimeValidationService timeValidationService,
                           TrackOccupancyService trackOccupancyService,
                           TrackAllocationStrategy trackAllocationStrategy) {
        this.bookingRepository = bookingRepository;
        this.timeValidationService = timeValidationService;
        this.trackOccupancyService = trackOccupancyService;
        this.trackAllocationStrategy = trackAllocationStrategy;
    }
    
    public String execute(String vehicleType, String vehicleNumber, String entryTime) {
        if (!timeValidationService.isValidEntryTime(entryTime)) {
            return "INVALID_ENTRY_TIME";
        }
        
        try {
            Vehicle vehicle = Vehicle.fromString(vehicleType);
            LocalTime parsedEntryTime = timeValidationService.parseTime(entryTime);
            
            TrackType allocatedTrack = trackAllocationStrategy.allocateTrack(vehicle, parsedEntryTime, trackOccupancyService);
            
            Booking booking = new Booking(vehicleNumber, vehicle, allocatedTrack, parsedEntryTime);
            bookingRepository.save(booking);
            
            return "SUCCESS";
        } catch (IllegalArgumentException e) {
            return "INVALID_ENTRY_TIME";
        } catch (RuntimeException e) {
            return "RACETRACK_FULL";
        }
    }
} 