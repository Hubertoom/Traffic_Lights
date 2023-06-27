package traffic;

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
        }
    }

    private void addRoads() {
        System.out.println("Input the number of roads:");
        this.numberOfRoads = Integer.parseInt(scanner.nextLine());
    }

    private void addInterval() {
        System.out.println("Input the interval:");
        this.interval = Integer.parseInt(scanner.nextLine());
    }
    private void displayMenu() {
        System.out.println("Menu:\n" +
                "1. Add\n" +
                "2. Delete\n" +
                "3. System\n" +
                "0. Quit");

        int userRequest = Integer.parseInt(scanner.nextLine());
        switch (userRequest) {
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
            default -> throw new IllegalStateException("Unexpected value: 0 - 3");
        }
    }

    private void displayGreetings() {
        System.out.println("Welcome to the traffic management system!");
    }

    private void openSystem() {
        System.out.println("System opened");
    }
}
