package traffic;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CircularQueue<E> {
    private final Queue<E> circularQueue;
    private int maxSize;

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        this.circularQueue = new ArrayDeque<>(maxSize);
    }

    public boolean enqueue(E element) {
        if (circularQueue.size() == maxSize) {
            return false;
        }
        return circularQueue.offer(element);
    }

    public E dequeue () {
        return circularQueue.poll();
    }

    public List<E> getAllRoads() {
        return circularQueue.stream().toList();
    }
}
