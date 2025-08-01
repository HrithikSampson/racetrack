package com.example.geektrust.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeValidationService {
    private static final LocalTime OPENING_TIME = LocalTime.of(13, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(20, 0);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    public boolean isValidEntryTime(String timeString) {
        try {
            LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
            return !time.isBefore(OPENING_TIME) && !time.isAfter(CLOSING_TIME);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public boolean isValidExitTime(String timeString) {
        try {
            LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
            return !time.isBefore(OPENING_TIME) && !time.isAfter(CLOSING_TIME);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public LocalTime parseTime(String timeString) {
        return LocalTime.parse(timeString, TIME_FORMATTER);
    }
} 