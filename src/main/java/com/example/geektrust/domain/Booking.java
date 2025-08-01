package com.example.geektrust.domain;

import java.time.LocalTime;

public class Booking {
    private final String vehicleNumber;
    private final Vehicle vehicle;
    private final TrackType trackType;
    private final LocalTime entryTime;
    private LocalTime exitTime;
    private final int defaultBookingHours = 3;
    
    public Booking(String vehicleNumber, Vehicle vehicle, TrackType trackType, LocalTime entryTime) {
        this.vehicleNumber = vehicleNumber;
        this.vehicle = vehicle;
        this.trackType = trackType;
        this.entryTime = entryTime;
        this.exitTime = entryTime.plusHours(defaultBookingHours);
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public TrackType getTrackType() {
        return trackType;
    }
    
    public LocalTime getEntryTime() {
        return entryTime;
    }
    
    public LocalTime getExitTime() {
        return exitTime;
    }
    
    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }
    
    public int getDefaultBookingHours() {
        return defaultBookingHours;
    }
} 