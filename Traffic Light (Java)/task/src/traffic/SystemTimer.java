package traffic;

public class SystemTimer extends Thread {
    private final SystemStatus systemStatus;
    private int timeElapsed = 0;
    private boolean displayStatus = false;

    public SystemTimer(SystemStatus systemStatus) {
        this.systemStatus = systemStatus;
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
            try {
                Thread.sleep(1000L);
                timeElapsed++;
                if (displayStatus) {
                    TerminalCleaner.cleanTerminal();
                    System.out.printf("! %ds. have passsed since system startup !\n", timeElapsed);
                    System.out.printf("! Number of roads: %d !\n", systemStatus.getNumberOfRoads());
                    System.out.printf("! Interval: %d !\n", systemStatus.getInterval());

                    if (!systemStatus.getAllRoads().isEmpty()) {
                        System.out.println();
                        systemStatus.getAllRoads().forEach(System.out::println);
                        System.out.println();
                    }
                    System.out.printf("! Press \" Enter \" to open menu ! \n");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
