package com.example.geektrust.usecase;

import com.example.geektrust.domain.TrackType;
import com.example.geektrust.service.RevenueCalculationService;

public class GetRevenueUseCase {
    private final RevenueCalculationService revenueCalculationService;
    
    public GetRevenueUseCase(RevenueCalculationService revenueCalculationService) {
        this.revenueCalculationService = revenueCalculationService;
    }
    
    public String execute() {
        int regularRevenue = revenueCalculationService.calculateRevenueForTrack(TrackType.REGULAR);
        int vipRevenue = revenueCalculationService.calculateRevenueForTrack(TrackType.VIP);
        
        return regularRevenue + " " + vipRevenue;
    }
} 