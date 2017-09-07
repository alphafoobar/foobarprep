package kata.concurrentqueue;

/**
 * This is a queue that has a maximum size that can be accessed concurrently and uses old school
 * Java concurrency.
 *
 * <p>Newer concurrency options that could be used: {@link java.util.concurrent.LinkedBlockingQueue}.
 */
class ConcurrentRestrictedQueue {

    private final Object lock = new Object();
    private int size;
    private int[] items;
    private int count; // Doesn't need to be volatile as will be only accessed from synchronized

    ConcurrentRestrictedQueue(int size) {
        this.size = size;
        this.items = new int[size];
    }

    void push(int item) {
        synchronized (lock) {
            while (count >= size) {
                try {
                    lock.wait(100);
                } catch (InterruptedException interrupt) {
                    handle(interrupt);
                }
            }
            items[count++] = item;
            lock.notifyAll();
        }
    }

    int pop() {
        synchronized (lock) {
            while (count == 0) {
                try {
                    lock.wait(100);
                } catch (InterruptedException interrupt) {
                    handle(interrupt);
                }
            }
            try {
                return items[--count];
            } finally {
                lock.notifyAll();
            }
        }
    }

    private void handle(InterruptedException interrupt) {
        interrupt.printStackTrace();
        Thread.currentThread().interrupt();
    }

    boolean isEmpty() {
        return count == 0;
    }
}
