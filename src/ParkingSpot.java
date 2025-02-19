package src;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpot {
    private int spotNumber;
    private VehicleType spotType;
    private boolean isOccupied;
    private Vehicle currentVehicle;
    private Lock spotLock = new ReentrantLock();

    public ParkingSpot(int spotNumber, VehicleType spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
        this.currentVehicle = null;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        spotLock.lock();
        try {
            if (!isOccupied) {
                this.currentVehicle = vehicle;
                this.isOccupied = true;
                return true;
            }
            return false;
        } finally {
            spotLock.unlock();
        }
    }

    public void removeVehicle() {
        spotLock.lock();
        try {
            this.currentVehicle = null;
            this.isOccupied = false;
        } finally {
            spotLock.unlock();
        }
    }

    public boolean isAvailable() {
        spotLock.lock();
        try {
            return !isOccupied;
        } finally {
            spotLock.unlock();
        }
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}
