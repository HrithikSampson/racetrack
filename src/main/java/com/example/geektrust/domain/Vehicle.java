package com.example.geektrust.domain;

public enum Vehicle {
    BIKE("BIKE"),
    CAR("CAR"),
    SUV("SUV");
    
    private final String type;
    
    Vehicle(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public static Vehicle fromString(String type) {
        for (Vehicle vehicle : values()) {
            if (vehicle.type.equals(type)) {
                return vehicle;
            }
        }
        throw new IllegalArgumentException("Invalid vehicle type: " + type);
    }
} 