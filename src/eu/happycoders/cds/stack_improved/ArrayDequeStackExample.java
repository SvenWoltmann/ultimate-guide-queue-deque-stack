package eu.happycoders.cds.stack_improved;

public class ArrayDequeStackExample {
  public static void main(String[] args) {
    Stack<Integer> stack = new ArrayDequeStack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    System.out.println("stack = " + stack);
    System.out.println("stack.peek() = " + stack.peek());
    System.out.println("stack.pop() = " + stack.pop());
    System.out.println("stack.pop() = " + stack.pop());
    System.out.println("stack.pop() = " + stack.pop());
    System.out.println("stack.pop() = " + stack.pop());
  }
}
