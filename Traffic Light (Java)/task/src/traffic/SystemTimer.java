package traffic;

import java.util.Comparator;

public class SystemTimer extends Thread {
    CircularQueue<Road> circularQueue;
    private int timeElapsed = 0;
    private boolean displayStatus = false;
    private final int interval;
    private int time;
    private final int numberOfRoads;
    private boolean isActive;


    public SystemTimer(CircularQueue<Road> circularQueue, int numberOfRoads, int interval) {
        this.circularQueue = circularQueue;
        this.interval = interval;
        this.numberOfRoads = numberOfRoads;
        this.time = interval + 1;
        isActive = true;
    }

    public void setDisplayStatusOn() {
        this.displayStatus = true;
    }

    public void setDisplayStatusOff() {
        this.displayStatus = false;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void run() {
        while (isActive) {
            synchronized (this) {
                try {
                    Thread.sleep(1000L);
                    timeElapsed++;
                    time--;
                    roadStatus();
                    if (time == 0) time = interval;
                    if (displayStatus) {
                        TerminalCleaner.cleanTerminal();
                        System.out.printf("! %ds. have passed since system startup !\n", timeElapsed);
                        System.out.printf("! Number of roads: %d !\n", numberOfRoads);
                        System.out.printf("! Interval: %d !\n", interval);

                        if (!circularQueue.getAllElements().isEmpty()) {
                            System.out.println();
                            circularQueue.getAllElements().forEach(System.out::println);
                            System.out.println();
                        }
                        System.out.print("! Press \" Enter \" to open menu ! \n");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void roadStatus() {
        if (circularQueue.isEmpty()) {
            return;
        }
        if (circularQueue.stream().noneMatch(Road::isOpen)) {
            circularQueue.stream().min(Comparator.comparingInt(Road::getTimeToChangeStatus))
                    .ifPresent(road -> road.setOpen(true));
        }

        if (time == 0) {
            circularQueue.peekCurrent().setOpen(false);
            circularQueue.getNext().setOpen(true);
        }
        for (int i = 0; i < circularQueue.size(); i++) {
            circularQueue.peekCurrent().setTimeToChangeStatus((time == 0 ? interval : time ) + (i > 1 ? interval * (i - 1) : 0) );
            circularQueue.getNext();
        }
    }
}
