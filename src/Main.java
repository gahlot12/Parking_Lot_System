package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of floors: ");
        int numFloors = scanner.nextInt();
        System.out.print("Enter spots per floor: ");
        int spotsPerFloor = scanner.nextInt();

        ParkingLot parkingLot = new ParkingLot(numFloors, spotsPerFloor);
        System.out.println(
                "✅ Parking Lot Created with " + numFloors + " floors and " + spotsPerFloor + " spots per floor.\n");

        while (true) {
            System.out.println("\n🅿️  Parking Lot System - Choose an action:");
            System.out.println("1️⃣  Park a Vehicle");
            System.out.println("2️⃣  Remove a Vehicle");
            System.out.println("3️⃣  Check Available Spots per Floor");
            System.out.println("4️⃣  Find Vehicle Location");
            System.out.println("5️⃣  Check if Parking Lot is Full");
            System.out.println("6️⃣  Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Park Vehicle
                    System.out.print("Enter Vehicle Number: ");
                    String licensePlate = scanner.nextLine();
                    System.out.print("Enter Vehicle Type (Bike/Car/Truck): ");
                    String type = scanner.nextLine().toUpperCase();

                    Vehicle vehicle;
                    if (type.equals("BIKE")) {
                        vehicle = new Bike(licensePlate);
                    } else if (type.equals("CAR")) {
                        vehicle = new Car(licensePlate);
                    } else if (type.equals("TRUCK")) {
                        vehicle = new Truck(licensePlate);
                    } else {
                        System.out.println("❌ Invalid vehicle type! Try again.");
                        break;
                    }

                    parkingLot.parkVehicle(vehicle);
                    break;

                case 2: // Remove Vehicle
                    System.out.print("Enter Vehicle Number to Remove: ");
                    String removePlate = scanner.nextLine();
                    parkingLot.removeVehicle(removePlate);
                    break;

                case 3: // Check Available Spots
                    parkingLot.printAvailableSpots();
                    break;

                case 4: // Find Vehicle
                    System.out.print("Enter Vehicle Number to Locate: ");
                    String searchPlate = scanner.nextLine();
                    parkingLot.findVehicle(searchPlate);
                    break;

                case 5: // Check if Parking Lot is Full
                    if (parkingLot.isFull()) {
                        System.out.println("🚗🅿️ The parking lot is FULL!");
                    } else {
                        System.out.println("✅ Parking spaces are available.");
                    }
                    break;

                case 6: // Exit
                    System.out.println("🚪 Exiting Parking Lot System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }
}
