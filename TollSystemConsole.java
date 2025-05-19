import java.util.ArrayList;
import java.util.Scanner;

class Vehicle {
    String number, type;
    double balance;

    Vehicle(String number, String type, double balance) {
        this.number = number;
        this.type = type;
        this.balance = balance;
    }

    double getTollFee() {
        switch (type.toLowerCase()) {
            case "car": return 50;
            case "bus": return 100;
            case "truck": return 150;
            default: return 70;
        }
    }

    boolean payToll() {
        double fee = getTollFee();
        if (balance >= fee) {
            balance -= fee;
            return true;
        }
        return false;
    }
}

public class TollSystemConsole {
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static double totalTollCollected = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nToll Booth System");
            System.out.println("1. Register Vehicle");
            System.out.println("2. Pay Toll");
            System.out.println("3. View Vehicles");
            System.out.println("4. View Total Toll Collected");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: registerVehicle(); break;
                case 2: payToll(); break;
                case 3: viewVehicles(); break;
                case 4: showTollCollected(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    private static void registerVehicle() {
        System.out.print("Enter Vehicle Number: ");
        String number = scanner.nextLine();

        System.out.print("Enter Vehicle Type (car/bus/truck): ");
        String type = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = Double.parseDouble(scanner.nextLine());

        vehicles.add(new Vehicle(number, type, balance));
        System.out.println("Vehicle Registered!");
    }

    private static void payToll() {
        System.out.print("Enter Vehicle Number: ");
        String number = scanner.nextLine();

        for (Vehicle v : vehicles) {
            if (v.number.equalsIgnoreCase(number)) {
                double fee = v.getTollFee();
                if (v.payToll()) {
                    totalTollCollected += fee;
                    System.out.println("Toll Paid: Rs. " + fee);
                } else {
                    System.out.println("Insufficient Balance!");
                }
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }

    private static void viewVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println("Number: " + v.number + ", Type: " + v.type + ", Balance: Rs. " + v.balance);
            }
        }
    }

    private static void showTollCollected() {
        System.out.println("Total Toll Collected: Rs. " + totalTollCollected);
    }
}
