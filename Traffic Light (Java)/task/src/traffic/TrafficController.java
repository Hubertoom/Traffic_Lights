package traffic;

import java.io.IOException;
import java.util.Scanner;

public class TrafficController {
    private int numberOfRoads;
    private int interval;
    private final Scanner scanner = new Scanner(System.in);
    public void run() {
        displayGreetings();
        addRoads();
        addInterval();
        while (true) {
            displayMenu();
            scanner.nextLine();
            try {
                var clearCommand = System.getProperty("os.name").contains("Windows")
                        ? new ProcessBuilder("cmd", "/c", "cls")
                        : new ProcessBuilder("clear");
                clearCommand.inheritIO().start().waitFor();
            }
            catch (InterruptedException | IOException e) {}
        }
    }

    private void addRoads() {
        System.out.print("Input the number of roads:");
        this.numberOfRoads = readData();
    }

    private void addInterval() {
        System.out.print("Input the interval:");
        this.interval = readData();
    }

    private int readData() {
        while (true) {
            String  temp = scanner.nextLine();
            if (temp.matches("[1-9]\\d*")) {
                return Integer.parseInt(temp);
            } else {
                System.out.println("Incorrect input. Try again:");
            }
        }
    }
    private void displayMenu() {
        System.out.println("Menu:\n" +
                "1. Add\n" +
                "2. Delete\n" +
                "3. System\n" +
                "0. Quit");
        String userRequest = scanner.nextLine();
        switch (userRequest.matches("[0-3]") ? Integer.parseInt(userRequest) : -1) {
            case 0 -> {
                System.out.println("Bye!");
                System.exit(0);
            }
            case 1 -> {
                this.numberOfRoads++;
                System.out.println("Road added");
            }
            case 2 -> {
                if (this.numberOfRoads > 1) {
                    System.out.println("Road deleted");
                    this.numberOfRoads--;
                } else {
                    return;
                }
            }
                case 3 -> {
                    openSystem();
                }
            default -> System.out.println("Incorrect option");
        }
    }

    private void displayGreetings() {
        System.out.println("Welcome to the traffic management system!");
    }

    private void openSystem() {
        System.out.println("System opened");
    }
}
