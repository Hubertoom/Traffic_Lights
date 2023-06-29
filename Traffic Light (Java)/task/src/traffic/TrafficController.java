package traffic;

import java.util.Objects;
import java.util.Scanner;

public class TrafficController {

    private final Scanner scanner = new Scanner(System.in);
    private final CircularQueue<Road> circularQueue;
    private final int interval;
    private final SystemTimer systemTimer;

    public TrafficController() {
        displayGreetings();
        int numberOfRoads = addNumberOfRoads();
        this.interval = addInterval();
        this.circularQueue = new CircularQueue<>(numberOfRoads);
        this.systemTimer = new SystemTimer(circularQueue, numberOfRoads, interval);
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

    private int addNumberOfRoads() {
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
        System.out.println("""
                Menu:
                1. Add
                2. Delete
                3. System
                0. Quit""");
        String userRequest = scanner.nextLine();
        switch (userRequest.matches("[0-3]") ? Integer.parseInt(userRequest) : -1) {
            case 0 -> {
                System.out.println("Bye!");
                systemTimer.interrupt();
                System.exit(0);
            }
            case 1 -> addRoad();
            case 2 -> deleteRoad();
            case 3 -> systemTimer.setDisplayStatusOn();
            default -> System.out.println("Incorrect option");
        }
    }

    private synchronized void addRoad() {
        System.out.println("Input road name: ");
        String roadName = scanner.nextLine();

        if (circularQueue.add(new Road(roadName))) {
            System.out.printf("%s Added!\n", roadName);
        } else {
            System.out.println("queue is full");
        }
    }

    private synchronized void deleteRoad() {
        Road result = circularQueue.removeFirst();
        if (Objects.isNull(result)) {
            System.out.println("queue is empty");
            return;
        }
        System.out.printf("%s deleted!\n", result.getName());
    }

    private void displayGreetings() {
        System.out.println("Welcome to the traffic management system!");
    }

}
