package eu.happycoders.cds.queue;

import java.util.Locale;
import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueueExample {
  public static void main(String[] args) {
    LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();

    // Start 5 threads calling queue.transfer()
    for (int i = 0; i < 5; i++) {
      final int finalI = i;
      new Thread(() -> {
        log("Calling queue.transfer(%d) (queue = %s)...", finalI, queue);
        try {
          queue.transfer(finalI);
          log("queue.transfer(%d) returned (queue = %s)", finalI, queue);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }).start();

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    log("queue = " + queue);

    // Now take all elements until the queue is empty
    while (!queue.isEmpty()) {
      log("    Calling queue.take() (queue = %s)...", queue);
      try {
        Integer e = queue.take();
        log("    queue.take() returned %d (queue = %s)", e, queue);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  private static void log(String format, Object... args) {
    System.out.printf(Locale.US, "[%-8s] %s%n",
        Thread.currentThread().getName(),
        String.format(format, args));
  }
}
