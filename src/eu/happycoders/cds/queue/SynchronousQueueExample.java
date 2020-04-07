package eu.happycoders.cds.queue;

import java.util.Locale;
import java.util.concurrent.*;

public class SynchronousQueueExample {
  private static boolean FAIR = true;

  public static void main(String[] args) {
    BlockingQueue<Integer> queue = new SynchronousQueue<>(FAIR);

    // Start 3 producing threads
    for (int i = 0; i < 3; i++) {
      final int finalI = i;
      new Thread(() -> enqueue(queue, finalI)).start();
      sleep(250);
    }

    // Start 6 consuming threads
    for (int i = 0; i < 6; i++) {
      new Thread(() -> dequeue(queue)).start();
      sleep(250);
    }

    // Start 3 more producing threads
    for (int i = 3; i < 6; i++) {
      final int finalI = i;
      new Thread(() -> enqueue(queue, finalI)).start();
      sleep(250);
    }
  }

  private static void enqueue(BlockingQueue<Integer> queue, int finalI) {
    log("Calling queue.put(%d) (queue = %s)...", finalI, queue);
    try {
      queue.put(finalI);
      log("queue.put(%d) returned (queue = %s)", finalI, queue);
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

  private static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void log(String format, Object... args) {
    System.out.printf(Locale.US, "[%-9s] %s%n",
        Thread.currentThread().getName(),
        String.format(format, args));
  }
}
