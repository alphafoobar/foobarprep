package kata.concurrentqueue;

/**
 * This is a queue that has a maximum size that can be accessed concurrently and uses old school
 * Java concurrency.
 *
 * <p>Newer concurrency options that could be used: {@link java.util.concurrent.LinkedBlockingQueue}.
 */
class SynchronizedMethodsBlockingQueue {

    private int size;
    private int[] items;
    private int count; // Doesn't need to be volatile as will be only accessed from synchronized

    SynchronizedMethodsBlockingQueue(int size) {
        this.size = size;
        this.items = new int[size];
    }

    synchronized void push(int item) {
        while (count >= size) {
            try {
                wait(100);
            } catch (InterruptedException interrupt) {
                handle(interrupt);
            }
        }
        items[count++] = item;
        notifyAll();
    }

    synchronized int pop() {
        while (count == 0) {
            try {
                wait(100);
            } catch (InterruptedException interrupt) {
                handle(interrupt);
            }
        }
        try {
            return items[--count];
        } finally {
            notifyAll();
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
