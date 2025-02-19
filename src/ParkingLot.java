package src;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {
    private List<ParkingFloor> floors;
    private Map<String, ParkingTicket> parkedVehicles;
    private Lock lock = new ReentrantLock();

    public ParkingLot(int numFloors, int spotsPerFloor) {
        this.floors = new ArrayList<>();
        this.parkedVehicles = new HashMap<>();
        for (int i = 1; i <= numFloors; i++) {
            floors.add(new ParkingFloor(i, spotsPerFloor));
        }
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        lock.lock();
        try {
            for (ParkingFloor floor : floors) {
                if (floor.getAvailableSpotsCount() > 0) {
                    ParkingSpot spot = floor.findAvailableSpot(vehicle);
                    if (spot != null) {
                        boolean parked = floor.parkVehicle(vehicle);
                        if (parked) {
                            ParkingTicket ticket = new ParkingTicket(vehicle.getLicensePlate(), floor.getFloorNumber(),
                                    spot.getSpotNumber());
                            parkedVehicles.put(vehicle.getLicensePlate(), ticket);
                            System.out.println("🚗 Vehicle " + vehicle.getLicensePlate() + " parked at Floor "
                                    + floor.getFloorNumber() + ", Spot " + spot.getSpotNumber());
                            return ticket;
                        }
                    }
                }
            }
            System.out.println("❌ Parking Full! No available spots.");
            return null;
        } finally {
            lock.unlock();
        }
    }

    public boolean removeVehicle(String licensePlate) {
        lock.lock();
        try {
            ParkingTicket ticket = parkedVehicles.get(licensePlate);
            if (ticket != null) {
                ParkingFloor floor = floors.get(ticket.getFloorNumber() - 1);
                boolean removed = floor.removeVehicle(licensePlate);
                if (removed) {
                    parkedVehicles.remove(licensePlate);
                    System.out.println("✅ Vehicle " + licensePlate + " exited the parking lot.");
                    return true;
                }
            }
            System.out.println("❌ Vehicle not found!");
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void printAvailableSpots() {
        System.out.println("🚗 Available Spots per Floor:");
        for (ParkingFloor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + ": " + floor.getAvailableSpotsCount() + " spots");
        }
    }

    public void findVehicle(String licensePlate) {
        if (!parkedVehicles.containsKey(licensePlate)) {
            System.out.println("❌ Vehicle not found in the parking lot.");
            return;
        }

        ParkingTicket ticket = parkedVehicles.get(licensePlate);
        System.out.println("✅ Vehicle " + licensePlate + " is parked at Floor " + ticket.getFloorNumber() + ", Spot "
                + ticket.getSpotNumber());
    }

    public boolean isFull() {
        for (ParkingFloor floor : floors) {
            if (!floor.isFull()) {
                return false;
            }
        }
        return true;
    }
}