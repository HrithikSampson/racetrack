# Race Track Booking System

A maintainable Java application implementing a race track booking system using SOLID principles and design patterns.

## Architecture Overview

The application follows Clean Architecture principles with the following layers:

### Domain Layer
- **Vehicle**: Enum representing vehicle types (BIKE, CAR, SUV)
- **TrackType**: Enum representing track types (REGULAR, VIP)
- **Booking**: Entity representing a booking with vehicle, track, entry/exit times
- **TrackConfiguration**: Configuration class with track capacities and pricing

### Repository Layer
- **BookingRepository**: Interface for data access
- **InMemoryBookingRepository**: In-memory implementation

### Service Layer
- **TimeValidationService**: Validates entry/exit times (13:00-20:00)
- **TrackOccupancyService**: Checks track availability and capacity
- **RevenueCalculationService**: Calculates revenue for different track types

### Strategy Layer
- **TrackAllocationStrategy**: Interface for track allocation strategies
- **DefaultTrackAllocationStrategy**: Prioritizes VIP tracks for eligible vehicles

### Use Case Layer
- **BookTrackUseCase**: Handles booking requests
- **ExtendBookingUseCase**: Handles additional time requests
- **GetRevenueUseCase**: Calculates and returns revenue

### Controller Layer
- **RacetrackController**: Processes commands and delegates to use cases

### Configuration Layer
- **ApplicationContainer**: Dependency injection container

## Design Patterns Used

1. **Repository Pattern**: Abstract data access layer
2. **Strategy Pattern**: Flexible track allocation strategies
3. **Dependency Injection**: Loose coupling between components
4. **Use Case Pattern**: Business logic encapsulation
5. **Factory Pattern**: Object creation in ApplicationContainer

## SOLID Principles Implementation

- **Single Responsibility**: Each class has one clear responsibility
- **Open/Closed**: New track allocation strategies can be added without modifying existing code
- **Liskov Substitution**: Repository implementations are interchangeable
- **Interface Segregation**: Small, focused interfaces
- **Dependency Inversion**: High-level modules don't depend on low-level modules

## Track Configuration

### Regular Track
- BIKE: 4 vehicles, $60/hour
- CAR: 2 vehicles, $120/hour
- SUV: 2 vehicles, $200/hour

### VIP Track
- CAR: 1 vehicle, $250/hour
- SUV: 1 vehicle, $300/hour

## Commands

### BOOK
```
BOOK <VEHICLE_TYPE> <VEHICLE_NUMBER> <ENTRY_TIME>
```
- Books a track for a vehicle
- Returns: SUCCESS, RACETRACK_FULL, or INVALID_ENTRY_TIME

### ADDITIONAL
```
ADDITIONAL <VEHICLE_NUMBER> <EXIT_TIME>
```
- Extends booking beyond default 3-hour period
- Returns: SUCCESS, INVALID_EXIT_TIME

### REVENUE
```
REVENUE
```
- Returns total revenue in format: "REGULAR_REVENUE VIP_REVENUE"

## Time Validation

- Valid entry/exit times: 13:00-20:00
- Default booking duration: 3 hours
- Additional time extends beyond default period

## Building and Running

### Using Gradle
```bash
gradle build
gradle run
```

### Using Java directly
```bash
javac -cp src/main/java com.example.geektrust.Main
java -cp src/main/java com.example.geektrust.Main
```

## Testing

The application includes comprehensive unit tests covering:
- Successful bookings
- Invalid entry/exit times
- Track capacity limits
- Revenue calculations
- VIP track allocations

## Sample Input/Output

### Input 1
```
BOOK SUV A56 13:10
BOOK CAR AB1 14:20
BOOK BIKE BIK1 13:00
BOOK BIKE BIK2 14:00
ADDITIONAL BIK2 17:50
REVENUE
```

### Expected Output 1
```
SUCCESS
SUCCESS
SUCCESS
SUCCESS
SUCCESS
1370 0
```

### Input 2
```
BOOK SUV M40 14:00
BOOK SUV O34 15:00
BOOK SUV XY4 13:00
BOOK SUV A56 13:10
BOOK SUV AB1 14:20
BOOK SUV S45 15:30
BOOK SUV XY22 17:00
BOOK SUV B56 18:00
REVENUE
```

### Expected Output 2
```
SUCCESS
SUCCESS
SUCCESS
RACETRACK_FULL
RACETRACK_FULL
RACETRACK_FULL
SUCCESS
INVALID_ENTRY_TIME
1800 900
```

## Project Structure

```
src/
├── main/java/com/example/geektrust/
│   ├── domain/
│   │   ├── Vehicle.java
│   │   ├── TrackType.java
│   │   ├── Booking.java
│   │   └── TrackConfiguration.java
│   ├── repository/
│   │   ├── BookingRepository.java
│   │   └── InMemoryBookingRepository.java
│   ├── service/
│   │   ├── TimeValidationService.java
│   │   ├── TrackOccupancyService.java
│   │   └── RevenueCalculationService.java
│   ├── strategy/
│   │   ├── TrackAllocationStrategy.java
│   │   └── DefaultTrackAllocationStrategy.java
│   ├── usecase/
│   │   ├── BookTrackUseCase.java
│   │   ├── ExtendBookingUseCase.java
│   │   └── GetRevenueUseCase.java
│   ├── controller/
│   │   └── RacetrackController.java
│   ├── config/
│   │   └── ApplicationContainer.java
│   └── Main.java
└── test/java/com/example/geektrust/
    └── MainTest.java
```

## Key Features

1. **Maintainable Code**: Clear separation of concerns and SOLID principles
2. **Extensible Design**: Easy to add new track types, vehicle types, or allocation strategies
3. **Testable**: Each component can be unit tested independently
4. **Type Safety**: Strong typing with enums and proper validation
5. **Error Handling**: Comprehensive error handling for invalid inputs
6. **Performance**: Efficient in-memory storage with O(1) lookups