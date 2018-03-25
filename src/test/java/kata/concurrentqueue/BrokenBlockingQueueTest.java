package kata.concurrentqueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.Thread.State;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class BrokenBlockingQueueTest {

    @Test
    public void empty() {
        assertTrue(new BrokenBlockingQueue(1).isEmpty());
    }

    @Test
    public void onlyOne() {
        BrokenBlockingQueue<Integer> queue = new BrokenBlockingQueue<>(1);
        queue.push(1);

        assertFalse(queue.isEmpty());
        assertThat(queue.pop(), is(1));
    }

    @Test
    public void inOutInOut() {
        BrokenBlockingQueue<Integer> queue = new BrokenBlockingQueue<>(1);
        queue.push(1);

        assertFalse(queue.isEmpty());
        assertThat(queue.pop(), is(1));

        assertTrue(queue.isEmpty());
        queue.push(2);
        assertThat(queue.pop(), is(2));
    }

    @Test
    public void biggerQueue() {
        BrokenBlockingQueue<Integer> queue = new BrokenBlockingQueue<>(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);

        assertFalse(queue.isEmpty());
        assertThat(queue.pop(), is(1));
        assertThat(queue.pop(), is(2));
    }

    @Test
    public void withTwoThreads()
        throws InterruptedException { // Shouldn't do this, multiple threads is prone to flashing tests
        BrokenBlockingQueue<Integer> queue = new BrokenBlockingQueue<>(1);
        final int tests = 5;
        CountDownLatch count = new CountDownLatch(tests);

        Thread consumer = new Thread("consumer") {
            @Override
            public void run() {
                for (int i = 0; i < tests; i++) {
                    if (queue.pop() == i) {
                        count.countDown();
                    }
                }
            }
        };
        consumer.setDaemon(true);
        consumer.start();

        Thread producer = new Thread("producer") {
            @Override
            public void run() {
                for (int i = 0; i < tests; i++) {
                    queue.push(i);
                }
            }
        };
        producer.setDaemon(true);
        producer.start();

        assertTrue(consumer.getState().equals(State.BLOCKED) || State.BLOCKED.equals(producer.getState()));
        assertFalse(count.await(100 * tests, TimeUnit.MILLISECONDS));
    }
}