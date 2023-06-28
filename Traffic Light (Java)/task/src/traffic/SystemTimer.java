package traffic;

public class SystemTimer extends Thread {
    private final TrafficController trafficController;
    private int timeElapsed = 0;
    private boolean displayStatus = false;

    public SystemTimer(TrafficController trafficController) {
        this.trafficController = trafficController;
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
                    System.out.printf("! Number of roads: %d !\n", trafficController.getNumberOfRoads());
                    System.out.printf("! Interval: %d !\n", trafficController.getInterval());
                    System.out.printf("! Press \" Enter \" to open menu ! \n");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
