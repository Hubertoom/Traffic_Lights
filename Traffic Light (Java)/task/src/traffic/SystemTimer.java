package traffic;

public class SystemTimer extends Thread {
    CircularQueue<Road> circularQueue;
    private int timeElapsed = 0;
    private boolean displayStatus = false;
    private final int interval;
    private volatile int time;
    private final int numberOfRoads;


    public SystemTimer(CircularQueue<Road> circularQueue, int numberOfRoads, int interval) {
        this.circularQueue = circularQueue;
        this.interval = interval;
        this.numberOfRoads = numberOfRoads;
        this.time = interval;
    }

    public void setDisplayStatusOn() {
        this.displayStatus = true;
    }

    public void setDisplayStatusOff() {
        this.displayStatus = false;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    Thread.sleep(1000L);
                    timeElapsed++;
                    time--;

                    // TODO: 29.06.2023 logic to calculated remaining time :(

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

    private synchronized void updateRoads() {

    }
}
