package kata.concurrentqueue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create a locking situation that would lead to deadlock.
 */
public class BrokenBlockingQueue<T> {

    private final LinkedBlockingQueue<T> delegate;
    private final ReentrantLock lock = new ReentrantLock();

    public BrokenBlockingQueue(int capacity) {
        delegate = new LinkedBlockingQueue<>(capacity);
    }

    public void push(T object) {
        try {
            synchronized (delegate) {
                lock.lock();
                try {
                    delegate.put(object);
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            handleInterupt(e);
        }
    }

    public T pop() {
        try {
            lock.lock();
            synchronized (delegate) {
                return delegate.take();
            }
        } catch (InterruptedException e) {
            return handleInterupt(e);
        } finally {
            lock.unlock();
        }
    }

    private T handleInterupt(InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
    }

    public boolean isEmpty() {
        return delegate.isEmpty();
    }
}
