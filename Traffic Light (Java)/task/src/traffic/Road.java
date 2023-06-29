package traffic;

public class Road {
    private final String name;
    private boolean isOpen;
    private int timeToChangeStatus;

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    public Road(String name) {
        this.name = name;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setTimeToChangeStatus(int timeToChangeStatus) {
        this.timeToChangeStatus = timeToChangeStatus;
    }

    public void decreaseTime() {
        timeToChangeStatus--;
    }
    @Override
    public String toString() {
        return String.format("%s will be %s for %ds.",
                name, isOpen ? ANSI_GREEN + "open" + ANSI_RESET: ANSI_RED + "closed" + ANSI_RESET, timeToChangeStatus);
    }

    public String getName() {
        return name;
    }
}
