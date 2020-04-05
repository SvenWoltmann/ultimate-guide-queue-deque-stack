package eu.happycoders.cds.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueExample {
  public static void main(String[] args) {
    Queue<Integer> queue = new ConcurrentLinkedQueue<>();
    queue.offer(1);
    queue.offer(2);
    queue.offer(3);
    System.out.println("queue = " + queue);
    System.out.println("queue.peek() = " + queue.peek());
    System.out.println("queue.poll() = " + queue.poll());
    System.out.println("queue.poll() = " + queue.poll());
    System.out.println("queue.poll() = " + queue.poll());
    System.out.println("queue.poll() = " + queue.poll());
    System.out.println("queue.remove() = " + queue.remove());
  }
}
