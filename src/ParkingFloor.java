package src;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, int numSpotsPerFloor) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
        initializeSpots(numSpotsPerFloor);
    }

    private void initializeSpots(int numSpotsPerFloor) {
        for (int i = 0; i < numSpotsPerFloor; i++) {
            spots.add(new ParkingSpot(i + 1, VehicleType.CAR)); // Default type, will be checked before parking
        }
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        if (vehicle.getType() == VehicleType.TRUCK) {
            for (int i = 0; i < spots.size() - 1; i++) {
                if (spots.get(i).isAvailable() && spots.get(i + 1).isAvailable()) {
                    return spots.get(i); // Return first spot of the two
                }
            }
        } else {
            for (ParkingSpot spot : spots) {
                if (spot.isAvailable()) {
                    return spot;
                }
            }
        }
        return null;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        ParkingSpot firstSpot = findAvailableSpot(vehicle);
        if (firstSpot == null)
            return false;

        if (vehicle.getType() == VehicleType.TRUCK) {
            // Get the index of the first spot
            int firstSpotIndex = spots.indexOf(firstSpot);
            // Get the next consecutive spot
            if (firstSpotIndex < spots.size() - 1) {
                ParkingSpot secondSpot = spots.get(firstSpotIndex + 1);
                if (secondSpot.isAvailable()) {
                    firstSpot.parkVehicle(vehicle);
                    secondSpot.parkVehicle(vehicle);
                    return true;
                }
            }
            return false;
        } else {
            return firstSpot.parkVehicle(vehicle);
        }
    }

    public boolean removeVehicle(String licensePlate) {
        boolean removed = false;
        for (ParkingSpot spot : spots) {
            if (!spot.isAvailable() &&
                    spot.getCurrentVehicle() != null &&
                    spot.getCurrentVehicle().getLicensePlate().equals(licensePlate)) {
                spot.removeVehicle();
                removed = true;
                // For trucks, we need to remove from both spots
                if (spot.getCurrentVehicle() != null &&
                        spot.getCurrentVehicle().getType() == VehicleType.TRUCK) {
                    // Find and remove from adjacent spot
                    int spotIndex = spots.indexOf(spot);
                    if (spotIndex < spots.size() - 1) {
                        spots.get(spotIndex + 1).removeVehicle();
                    }
                }
            }
        }
        return removed;
    }

    public int getAvailableSpotsCount() {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
