package eu.happycoders.cds.queue;

import java.util.concurrent.*;

public class DelayQueueExample {
  public static void main(String[] args) {
    BlockingQueue<DelayedElement<Integer>> queue = new DelayQueue<>();
    ThreadLocalRandom tlr = ThreadLocalRandom.current();
    long startTime = System.currentTimeMillis();

    // Enqueue random numbers with random initial delays
    for (int i = 0; i < 7; i++) {
      DelayedElement<Integer> e = new DelayedElement<>(
          tlr.nextInt(10, 100),
          tlr.nextInt(100, 1000));
      queue.offer(e);
      System.out.printf("[%3dms] queue.offer(%s)   --> queue = %s%n",
          System.currentTimeMillis() - startTime, e, queue);
    }

    // Dequeue all elements
    while (!queue.isEmpty()) {
      try {
        DelayedElement<Integer> e = queue.take();
        System.out.printf("[%3dms] queue.poll() = %s --> queue = %s%n",
            System.currentTimeMillis() - startTime, e, queue);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}
