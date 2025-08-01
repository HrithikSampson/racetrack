package com.example.geektrust.strategy;

import com.example.geektrust.domain.TrackType;
import com.example.geektrust.domain.Vehicle;
import com.example.geektrust.service.TrackOccupancyService;

import java.time.LocalTime;

public interface TrackAllocationStrategy {
    TrackType allocateTrack(Vehicle vehicle, LocalTime entryTime, TrackOccupancyService trackOccupancyService);
} 