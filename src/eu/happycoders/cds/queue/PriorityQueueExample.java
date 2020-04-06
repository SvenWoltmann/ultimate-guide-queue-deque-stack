package eu.happycoders.cds.queue;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PriorityQueueExample {
  public static void main(String[] args) {
    Queue<Integer> queue = new PriorityQueue<>();

    // Enqueue random numbers
    for (int i = 0; i < 10; i++) {
      Integer e = ThreadLocalRandom.current().nextInt(100);
      queue.offer(e);
      System.out.printf("queue.offer(%2d)    --> queue = %s%n", e, queue);
    }

    // Dequeue all elements
    while (!queue.isEmpty()) {
      System.out.printf("queue.poll() = %2d  --> queue = %s%n",
          queue.poll(), queue);
    }
  }
}
