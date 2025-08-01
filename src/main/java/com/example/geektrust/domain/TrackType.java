package com.example.geektrust.domain;

public enum TrackType {
    REGULAR("REGULAR"),
    VIP("VIP");
    
    private final String type;
    
    TrackType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public static TrackType fromString(String type) {
        for (TrackType trackType : values()) {
            if (trackType.type.equals(type)) {
                return trackType;
            }
        }
        throw new IllegalArgumentException("Invalid track type: " + type);
    }
} 