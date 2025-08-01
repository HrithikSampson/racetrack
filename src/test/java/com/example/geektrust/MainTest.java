package com.example.geektrust;

import com.example.geektrust.config.ApplicationContainer;
import com.example.geektrust.controller.RacetrackController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private RacetrackController controller;
    
    @BeforeEach
    void setUp() {
        ApplicationContainer container = new ApplicationContainer();
        controller = container.getController();
    }
    
    @Test
    void testSuccessfulBooking() {
        String result = controller.processCommand("BOOK BIKE M40 14:00");
        assertEquals("SUCCESS", result);
        
        result = controller.processCommand("BOOK CAR O34 15:00");
        assertEquals("SUCCESS", result);
        
        result = controller.processCommand("BOOK SUV A66 13:10");
        assertEquals("SUCCESS", result);
    }
    
    @Test
    void testInvalidEntryTime() {
        String result = controller.processCommand("BOOK SUV A66 11:00");
        assertEquals("INVALID_ENTRY_TIME", result);
        
        result = controller.processCommand("BOOK CAR XY4 21:00");
        assertEquals("INVALID_ENTRY_TIME", result);
    }
    
    @Test
    void testTrackFull() {
        controller.processCommand("BOOK SUV M40 14:00");
        controller.processCommand("BOOK SUV O34 15:00");
        
        String result = controller.processCommand("BOOK SUV A56 13:10");
        assertEquals("RACETRACK_FULL", result);
    }
    
    @Test
    void testAdditionalBooking() {
        controller.processCommand("BOOK BIKE M40 14:00");
        
        String result = controller.processCommand("ADDITIONAL M40 17:40");
        assertEquals("SUCCESS", result);
    }
    
    @Test
    void testInvalidExitTime() {
        controller.processCommand("BOOK CAR O34 15:00");
        
        String result = controller.processCommand("ADDITIONAL O34 20:50");
        assertEquals("INVALID_EXIT_TIME", result);
    }
    
    @Test
    void testRevenueCalculation() {
        controller.processCommand("BOOK SUV A56 13:10");
        controller.processCommand("BOOK CAR AB1 14:20");
        controller.processCommand("BOOK BIKE BIK1 13:00");
        controller.processCommand("BOOK BIKE BIK2 14:00");
        controller.processCommand("ADDITIONAL BIK2 17:50");
        
        String result = controller.processCommand("REVENUE");
        assertEquals("1370 0", result);
    }
    
    @Test
    void testVIPTrackBooking() {
        controller.processCommand("BOOK SUV M40 14:00");
        controller.processCommand("BOOK SUV O34 15:00");
        
        String result = controller.processCommand("BOOK SUV XY4 13:00");
        assertEquals("SUCCESS", result);
    }
    
    @Test
    void testInvalidCommands() {
        String result = controller.processCommand("INVALID_COMMAND");
        assertEquals("INVALID_COMMAND", result);
        
        result = controller.processCommand("BOOK BIKE");
        assertEquals("INVALID_COMMAND", result);
        
        result = controller.processCommand("ADDITIONAL");
        assertEquals("INVALID_COMMAND", result);
    }
}