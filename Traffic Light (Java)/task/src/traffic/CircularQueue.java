package traffic;

import java.util.ArrayDeque;
import java.util.Queue;

public class CircularQueue {
    private final Queue<String> circularQueue;

    public CircularQueue(int maxSize) {
        this.circularQueue = new ArrayDeque<>(maxSize);
    }

    public boolean enqueue(String element) {
        return circularQueue.offer(element);
    }

    public String dequeue () {
        return circularQueue.poll();
    }
}
