package com.example.geektrust.controller;

import com.example.geektrust.usecase.BookTrackUseCase;
import com.example.geektrust.usecase.ExtendBookingUseCase;
import com.example.geektrust.usecase.GetRevenueUseCase;

public class RacetrackController {
    private final BookTrackUseCase bookTrackUseCase;
    private final ExtendBookingUseCase extendBookingUseCase;
    private final GetRevenueUseCase getRevenueUseCase;
    
    public RacetrackController(BookTrackUseCase bookTrackUseCase,
                             ExtendBookingUseCase extendBookingUseCase,
                             GetRevenueUseCase getRevenueUseCase) {
        this.bookTrackUseCase = bookTrackUseCase;
        this.extendBookingUseCase = extendBookingUseCase;
        this.getRevenueUseCase = getRevenueUseCase;
    }
    
    public String processCommand(String command) {
        String[] parts = command.trim().split("\\s+");
        
        if (parts.length == 0) {
            return "INVALID_COMMAND";
        }
        
        String action = parts[0].toUpperCase();
        
        switch (action) {
            case "BOOK":
                if (parts.length != 4) {
                    return "INVALID_COMMAND";
                }
                return bookTrackUseCase.execute(parts[1], parts[2], parts[3]);
                
            case "ADDITIONAL":
                if (parts.length != 3) {
                    return "INVALID_COMMAND";
                }
                return extendBookingUseCase.execute(parts[1], parts[2]);
                
            case "REVENUE":
                if (parts.length != 1) {
                    return "INVALID_COMMAND";
                }
                return getRevenueUseCase.execute();
                
            default:
                return "INVALID_COMMAND";
        }
    }
} 