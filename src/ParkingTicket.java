package src;

public class ParkingTicket {
    private final String licensePlate;
    private final int floorNumber;
    private final int spotNumber;

    public ParkingTicket(String licensePlate, int floorNumber, int spotNumber) {
        this.licensePlate = licensePlate;
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    @Override
    public String toString() {
        return "Ticket[Vehicle: " + licensePlate + ", Floor: " + floorNumber + ", Spot: " + spotNumber + "]";
    }
}
