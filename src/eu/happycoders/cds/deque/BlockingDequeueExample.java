package eu.happycoders.cds.deque;

import java.util.Locale;
import java.util.concurrent.*;

public class BlockingDequeueExample {
  private static final long startTime = System.currentTimeMillis();

  public static void main(String[] args) {
    BlockingDeque<Integer> deque = new LinkedBlockingDeque<>(3);
    ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);

    // Start reading from the queue immediately, every 3 seconds
    for (int i = 0; i < 10; i++) {
      pool.schedule(() -> dequeue(deque), i * 3, TimeUnit.SECONDS);
    }

    // Start writing to the queue after 3.5 seconds (so there are already 2
    // threads waiting), every 1 seconds (so that the queue fills faster than
    // it's emptied, so that we see a full queue soon)
    for (int i = 0; i < 10; i++) {
      int finalI = i;
      pool.schedule(() -> enqueue(deque, finalI),
          3500 + i * 1000, TimeUnit.MILLISECONDS);
    }

    pool.shutdown();
    try {
      pool.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void dequeue(BlockingDeque<Integer> deque) {
    if (ThreadLocalRandom.current().nextBoolean())
      dequeueAtFront(deque);
    else
      dequeueAtBack(deque);
  }

  private static void dequeueAtFront(BlockingDeque<Integer> deque) {
    log("    Calling dedeque.takeFirst() (queue = %s)...", deque);
    try {
      Integer e = deque.takeFirst();
      log("    deque.takeFirst() returned %d (queue = %s)", e, deque);
    } catch (
        InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void dequeueAtBack(BlockingDeque<Integer> deque) {
    log("    Calling dedeque.takeLast() (queue = %s)...", deque);
    try {
      Integer e = deque.takeLast();
      log("    deque.takeLast() returned %d (queue = %s)", e, deque);
    } catch (
        InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void enqueue(BlockingDeque<Integer> deque, int i) {
    if (ThreadLocalRandom.current().nextBoolean())
      enqueueAtFront(deque, i);
    else
      enqueueAtBack(deque, i);
  }

  private static void enqueueAtFront(BlockingDeque<Integer> deque, int i) {
    log("Calling deque.putFirst(%d) (queue = %s)...", i, deque);
    try {
      deque.putFirst(i);
      log("deque.putFirst(%d) returned (queue = %s)", i, deque);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void enqueueAtBack(BlockingDeque<Integer> deque, int i) {
    log("Calling deque.putLast(%d) (queue = %s)...", i, deque);
    try {
      deque.putFirst(i);
      log("deque.putLast(%d) returned (queue = %s)", i, deque);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void log(String format, Object... args) {
    System.out.printf(Locale.US, "[%4.1fs] [%-16s] %s%n",
        (System.currentTimeMillis() - startTime) / 1000.0,
        Thread.currentThread().getName(),
        String.format(format, args));
  }
}
