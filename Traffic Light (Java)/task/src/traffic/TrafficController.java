package traffic;

import java.util.Scanner;

public class TrafficController {
    private int numberOfRoads;
    private int interval;
    private final Scanner scanner = new Scanner(System.in);
    private final SystemTimer systemTimer;

    public TrafficController() {
        this.systemTimer = new SystemTimer(this);
        this.systemTimer.setName("QueueThread");
        this.systemTimer.start();
    }

    public int getNumberOfRoads() {
        return numberOfRoads;
    }

    public int getInterval() {
        return interval;
    }

    public void run() {
        displayGreetings();
        addRoads();
        addInterval();
        while (true) {
            displayMenu();
            scanner.nextLine();
            systemTimer.setDisplayStatusOff();
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
            String temp = scanner.nextLine();
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
                systemTimer.interrupt();
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
                systemTimer.setDisplayStatusOn();
            }
            default -> System.out.println("Incorrect option");
        }
    }

    private void displayGreetings() {
        System.out.println("Welcome to the traffic management system!");
    }

}
