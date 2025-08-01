package com.example.geektrust.config;

import com.example.geektrust.controller.RacetrackController;
import com.example.geektrust.repository.BookingRepository;
import com.example.geektrust.repository.InMemoryBookingRepository;
import com.example.geektrust.service.RevenueCalculationService;
import com.example.geektrust.service.TimeValidationService;
import com.example.geektrust.service.TrackOccupancyService;
import com.example.geektrust.strategy.DefaultTrackAllocationStrategy;
import com.example.geektrust.strategy.TrackAllocationStrategy;
import com.example.geektrust.usecase.BookTrackUseCase;
import com.example.geektrust.usecase.ExtendBookingUseCase;
import com.example.geektrust.usecase.GetRevenueUseCase;

public class ApplicationContainer {
    private final BookingRepository bookingRepository;
    private final TimeValidationService timeValidationService;
    private final TrackOccupancyService trackOccupancyService;
    private final RevenueCalculationService revenueCalculationService;
    private final TrackAllocationStrategy trackAllocationStrategy;
    
    private final BookTrackUseCase bookTrackUseCase;
    private final ExtendBookingUseCase extendBookingUseCase;
    private final GetRevenueUseCase getRevenueUseCase;
    
    private final RacetrackController controller;
    
    public ApplicationContainer() {
        this.bookingRepository = new InMemoryBookingRepository();
        this.timeValidationService = new TimeValidationService();
        this.trackOccupancyService = new TrackOccupancyService(bookingRepository);
        this.revenueCalculationService = new RevenueCalculationService(bookingRepository);
        this.trackAllocationStrategy = new DefaultTrackAllocationStrategy();
        
        this.bookTrackUseCase = new BookTrackUseCase(
            bookingRepository, timeValidationService, trackOccupancyService, trackAllocationStrategy);
        this.extendBookingUseCase = new ExtendBookingUseCase(
            bookingRepository, timeValidationService);
        this.getRevenueUseCase = new GetRevenueUseCase(
            revenueCalculationService);
        
        this.controller = new RacetrackController(
            bookTrackUseCase, extendBookingUseCase, getRevenueUseCase);
    }
    
    public RacetrackController getController() {
        return controller;
    }
}