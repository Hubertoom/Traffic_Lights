package traffic;

import java.util.Objects;
import java.util.Scanner;

public class TrafficController {
    private final Scanner scanner = new Scanner(System.in);
    private final SystemStatus systemStatus;
    private final SystemTimer systemTimer;
    public TrafficController() {
        displayGreetings();
        int numberOfRoads = addRoads();
        int interval = addInterval();
        this.systemStatus = new SystemStatus(numberOfRoads, interval);
        this.systemTimer = new SystemTimer(systemStatus);
        this.systemTimer.setName("QueueThread");
        this.systemTimer.start();
    }

    public void run() {

        while (true) {
            displayMenu();
            scanner.nextLine();
            systemTimer.setDisplayStatusOff();
        }
    }

    private int addRoads() {
        System.out.print("Input the number of roads:");
        return readData();
    }

    private int addInterval() {
        System.out.print("Input the interval:");
        return readData();
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
            case 1 -> addRoad();
            case 2 -> deleteRoad();
            case 3 -> {
                systemTimer.setDisplayStatusOn();
            }
            default -> System.out.println("Incorrect option");
        }
    }

    private void addRoad() {
        System.out.println("Input road name: ");
        String roadName = scanner.nextLine();
        if (systemStatus.addRoad(roadName)) {
            System.out.printf("%s Added!\n", roadName);
        } else {
            System.out.println("queue is full");
        }
    }

    private void deleteRoad() {
        String result = systemStatus.deleteRoad();
        if (Objects.isNull(result)) {
            System.out.println("queue is empty");
        } else {
            System.out.printf("%s deleted!\n", result);
        }
    }

    private void displayGreetings() {
        System.out.println("Welcome to the traffic management system!");
    }

}
