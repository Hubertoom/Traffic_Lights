package traffic;

import java.util.*;

public class CircularQueue<E> extends LinkedList<E> {
    private final int maxSize;
    private int currentSize = 0;
    private int head = 0;

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
    }
    @Override
    public boolean add(E e) {
        if (size() == maxSize) {
            return false;
        }
        currentSize++;
        head = (head + 1) % currentSize;
        return super.add(e);
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        head %= currentSize;
        currentSize--;
        return super.removeFirst();
    }

        /**
         * Method returns maximal number of elements which can be storek in queue.
         * @return maximal number of elements in queue
         */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Inserts specified element at the end of this circular queue.
     * This method is not equivalent to others method like offer(), add()
     * because insertion of the element is deterimnated by head of this queue.
     * Maximum number of elements is specified when queue is created.
     * It doesn't change its size automatically.
     * @param element - element to be appened to the queue
     * @return true if element has been inserted or false if queue is full
     */
    public boolean enqueue(E element) {
        if (size() == maxSize) {
            return false;
        }
        currentSize++;
        if (head == 0) {
            addLast(element);
        } else {
            add(head, element);
            head = (head + 1) % currentSize;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        currentSize += c.size();
        return super.addAll(c);
    }

    /**
     * Retrieves and remove head of the circular queue represented by this circular queue
     * (in other words, the current element pointed by the cursor of this circular queue),
     * or returns null if this circular queue is empty.
     * * @return Current element pointed as a head of this circular queue or null if
     * circular queue is empty
     */
    public E dequeue () {
        if (isEmpty()) {
            return null;
        }
        E element = remove(head);
        currentSize--;
        if (currentSize == 0) {
            head = 0;
        } else {
            head %= currentSize;
        }
        return element;
    }

    /**
     * Retrieves but not remove head of the circular queue represented by this circular queue
     * (in other words, the current element pointed by the coursor of this queue),
     * or returns null if this queue is empty.
     * Every invokes of this method returns an element and moves the pointer to the next element.
     * There is no first or last element, the method works in a circle.
     *
     * @return Current element pointed as a head of this queue or null is queue is empty.
     */
    public E getNext() {
        if (isEmpty()) {
            return null;
        }
        E element = get(head);
        head = (head + 1) % currentSize;
        return element;
    }

    public E peekCurrent() {
        if (isEmpty()) {
            return null;
        }
        return get(head);
    }

    public List<E> getAllElements() {
        List<E> list = new ArrayList<>();
        Iterator<E> iterator = this.iterator();
        iterator.forEachRemaining(list::add);
        return list;
    }
}
