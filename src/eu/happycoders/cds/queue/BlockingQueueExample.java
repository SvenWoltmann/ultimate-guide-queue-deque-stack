package eu.happycoders.cds.queue;

import java.util.Locale;
import java.util.concurrent.*;

public class BlockingQueueExample {
  private static final long startTime = System.currentTimeMillis();

  public static void main(String[] args) {
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);
    ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);

    // Start reading from the queue immediately, every 3 seconds
    for (int i = 0; i < 10; i++) {
      pool.schedule(() -> dequeue(queue), i * 3, TimeUnit.SECONDS);
    }

    // Start writing to the queue after 3.5 seconds (so there are already 2
    // threads waiting), every 1 seconds (so that the queue fills faster than
    // it's emptied, so that we see a full queue soon)
    for (int i = 0; i < 10; i++) {
      int finalI = i;
      pool.schedule(() -> enqueue(queue, finalI),
          3500 + i * 1000, TimeUnit.MILLISECONDS);
    }

    pool.shutdown();
    try {
      pool.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void dequeue(BlockingQueue<Integer> queue) {
    log("    Calling queue.take() (queue = %s)...", queue);
    try {
      Integer e = queue.take();
      log("    queue.take() returned %d (queue = %s)", e, queue);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void enqueue(BlockingQueue<Integer> queue, int i) {
    log("Calling queue.put(%d) (queue = %s)...", i, queue);
    try {
      queue.put(i);
      log("queue.put(%d) returned (queue = %s)", i, queue);
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
