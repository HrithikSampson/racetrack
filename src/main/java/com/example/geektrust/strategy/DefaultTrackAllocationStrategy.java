package com.example.geektrust.strategy;

import com.example.geektrust.domain.TrackConfiguration;
import com.example.geektrust.domain.TrackType;
import com.example.geektrust.domain.Vehicle;
import com.example.geektrust.service.TrackOccupancyService;

import java.time.LocalTime;

public class DefaultTrackAllocationStrategy implements TrackAllocationStrategy {
    
    @Override
    public TrackType allocateTrack(Vehicle vehicle, LocalTime entryTime, TrackOccupancyService trackOccupancyService) {
        if (TrackConfiguration.isVehicleAllowedOnTrack(vehicle, TrackType.VIP)) {
            if (trackOccupancyService.isTrackAvailable(TrackType.VIP, vehicle, entryTime)) {
                return TrackType.VIP;
            }
        }
        
        if (trackOccupancyService.isTrackAvailable(TrackType.REGULAR, vehicle, entryTime)) {
            return TrackType.REGULAR;
        }
        
        throw new RuntimeException("No available tracks for vehicle: " + vehicle);
    }
} 