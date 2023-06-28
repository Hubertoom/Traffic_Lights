package traffic;

import java.util.List;

public class SystemStatus {
    private final int numberOfRoads;
    private final int interval;
    private final CircularQueue<String> circularQueue;

    public SystemStatus(int numberOfRoads, int interval) {
        this.numberOfRoads = numberOfRoads;
        this.interval = interval;
        this.circularQueue = new CircularQueue(numberOfRoads);
    }

    public boolean addRoad(String element) {
        return circularQueue.enqueue(element);
    }

    public String  deleteRoad() {
        return circularQueue.dequeue();
    }

    public int getNumberOfRoads() {
        return numberOfRoads;
    }

    public int getInterval() {
        return interval;
    }

    public List<String> getAllRoads() {
        return circularQueue.getAllRoads();
    }
}
