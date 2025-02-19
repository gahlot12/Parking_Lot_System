# Parking Lot System

## Overview

The **Parking Lot System** is a **command-line-based parking management system** designed to handle multiple floors and vehicle types efficiently.

### Features:

-   Supports parking for **Bikes, Cars, and Trucks**.
-   Finds the **nearest available spot** for parking.
-   Allows **removal of parked vehicles**.
-   Provides **real-time availability** of parking spots.
-   **Locates vehicles** within the parking lot.
-   Checks if the parking lot is **full or has space left**.

This project follows **Object-Oriented Design (OOD)** principles and ensures **thread safety** using `ReentrantLock` for handling concurrent parking operations.

---

## Low-Level Design (LLD)

### Class Structure

#### **Vehicle (Abstract Class)**

-   `licensePlate: String`
-   `vehicleType: VehicleType`
-   Methods:
    -   `getLicensePlate()`
    -   `getVehicleType()`

#### **Bike, Car, Truck (Inherit from Vehicle)**

#### **ParkingSpot**

-   `spotNumber: int`
-   `spotType: VehicleType`
-   `isOccupied: boolean`
-   `currentVehicle: Vehicle`
-   `spotLock: Lock` (for concurrency)
-   Methods:
    -   `parkVehicle(Vehicle)`
    -   `removeVehicle()`
    -   `isAvailable()`
    -   `getSpotNumber()`
    -   `getSpotType()`
    -   `getCurrentVehicle()`

#### **ParkingFloor**

-   `floorNumber: int`
-   `spots: List<ParkingSpot>`
-   Methods:
    -   `initializeSpots()`
    -   `findAvailableSpot(Vehicle)`
    -   `parkVehicle(Vehicle)`
    -   `removeVehicle(String licensePlate)`
    -   `findVehicle(String licensePlate)`
    -   `getFloorNumber()`
    -   `isFull()`

#### **ParkingLot**

-   `floors: List<ParkingFloor>`
-   `parkedVehicles: ConcurrentHashMap<String, ParkingTicket>`
-   `lock: ReentrantReadWriteLock`
-   Methods:
    -   `parkVehicle(Vehicle vehicle): ParkingTicket`
    -   `removeVehicle(String licensePlate): boolean`
    -   `findVehicle(String licensePlate): void`
    -   `printAvailableSpots(): void`
    -   `isFull(): boolean`

#### **ParkingTicket**

-   `licensePlate: String`
-   `floorNumber: int`
-   `spotNumber: int`
-   Methods:
    -   `getLicensePlate(): String`
    -   `getFloorNumber(): int`
    -   `getSpotNumber(): int`
    -   `toString(): String`

#### **Main (Handles User Input and Operations)**

-   Contains the `main` method to interact with the system.

---

## Functional Flow

1.  **Initialize the Parking Lot** with floors and spots per floor.
2.  **Park a Vehicle** by finding the nearest available spot.
3.  **Generate a Parking Ticket** upon successful parking.
4.  **Check Availability** of spots at any time.
5.  **Find a Vehicle** using its license plate.
6.  **Remove a Vehicle** when it leaves.
7.  **Check if the Parking Lot is Full**.
8.  **Exit the System** when operations are complete.

---

## Setup & Installation

### Prerequisites

-   Java 8 or higher installed

### Steps to Run

1.  Clone the repository:
    ```sh
    git clone https://github.com/your-username/Parking_Lot_System.git
    ```
2.  Navigate to the project directory:
    ```sh
    cd ParkingLotSystem
    ```
3.  Compile the Java files:
    ```sh
    javac src/*.java
    ```
4.  Run the application:
    ```sh
    java src.Main
    ```

---
