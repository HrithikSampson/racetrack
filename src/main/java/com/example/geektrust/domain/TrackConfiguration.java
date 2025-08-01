package com.example.geektrust.domain;

import java.util.HashMap;
import java.util.Map;

public class TrackConfiguration {
    private static final Map<TrackType, Map<Vehicle, TrackInfo>> trackConfigurations = new HashMap<>();
    
    static {
        Map<Vehicle, TrackInfo> regularTrack = new HashMap<>();
        regularTrack.put(Vehicle.BIKE, new TrackInfo(4, 60));
        regularTrack.put(Vehicle.CAR, new TrackInfo(2, 120));
        regularTrack.put(Vehicle.SUV, new TrackInfo(2, 200));
        trackConfigurations.put(TrackType.REGULAR, regularTrack);
        
        Map<Vehicle, TrackInfo> vipTrack = new HashMap<>();
        vipTrack.put(Vehicle.CAR, new TrackInfo(1, 250));
        vipTrack.put(Vehicle.SUV, new TrackInfo(1, 300));
        trackConfigurations.put(TrackType.VIP, vipTrack);
    }
    
    public static TrackInfo getTrackInfo(TrackType trackType, Vehicle vehicle) {
        Map<Vehicle, TrackInfo> trackInfo = trackConfigurations.get(trackType);
        if (trackInfo == null || !trackInfo.containsKey(vehicle)) {
            throw new IllegalArgumentException("Vehicle " + vehicle + " not allowed on " + trackType + " track");
        }
        return trackInfo.get(vehicle);
    }
    
    public static boolean isVehicleAllowedOnTrack(Vehicle vehicle, TrackType trackType) {
        Map<Vehicle, TrackInfo> trackInfo = trackConfigurations.get(trackType);
        return trackInfo != null && trackInfo.containsKey(vehicle);
    }
    
    public static class TrackInfo {
        private final int maxVehicles;
        private final int costPerHour;
        
        public TrackInfo(int maxVehicles, int costPerHour) {
            this.maxVehicles = maxVehicles;
            this.costPerHour = costPerHour;
        }
        
        public int getMaxVehicles() {
            return maxVehicles;
        }
        
        public int getCostPerHour() {
            return costPerHour;
        }
    }
} 