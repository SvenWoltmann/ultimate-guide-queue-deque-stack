package eu.happycoders.cds.deque;

import java.util.Deque;
import java.util.concurrent.*;

public class ConcurrentLinkedDequeExample {
  private static final Deque<Integer> deque = new ConcurrentLinkedDeque<>();

  public static void main(String[] args) {
    // Start 4 writing threads
    for (int i = 0; i < 4; i++)
      new ModifyingThread(true).start();

    // Start 3 reading threads
    for (int i = 0; i < 3; i++)
      new ModifyingThread(false).start();
  }

  private static final class ModifyingThread extends Thread {
    private final boolean write;

    private ModifyingThread(boolean write) {
      this.write = write;
    }

    @Override
    public void run() {
      for (; ; ) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        try {
          Thread.sleep(random.nextInt(500, 2000));
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        boolean first = random.nextBoolean();
        if (write) {
          Integer e = random.nextInt(1000);
          if (first) {
            deque.offerFirst(e);
            System.out.printf("deque.offerFirst(%3d)        --> deque = %s%n",
                e, deque);
          } else {
            deque.offerLast(e);
            System.out.printf("deque.offerLast(%3d)         --> deque = %s%n",
                e, deque);
          }
        } else {
          if (first)
            System.out.printf("    deque.pollFirst() = %4d --> deque = %s%n",
                deque.pollFirst(), deque);
          else
            System.out.printf("    deque.pollLast()  = %4d --> deque = %s%n",
                deque.pollLast(), deque);
        }
      }
    }
  }
}
