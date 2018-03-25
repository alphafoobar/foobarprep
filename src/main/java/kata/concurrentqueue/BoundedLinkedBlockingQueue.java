package kata.concurrentqueue;

import java.util.concurrent.LinkedBlockingQueue;

class BoundedLinkedBlockingQueue<T> {

  private final LinkedBlockingQueue<T> delegate;

  public BoundedLinkedBlockingQueue(int capacity) {
    delegate = new LinkedBlockingQueue<>(3);
  }

  public void push(T object) {
    try {
      delegate.put(object);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public T pop() {
    try {
      return delegate.take();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
  }
}
