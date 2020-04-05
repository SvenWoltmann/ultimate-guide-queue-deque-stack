package eu.happycoders.cds.stack_improved;

import java.util.Collection;

public interface Stack<E> extends Collection<E> {
  void push(E item);
  E pop();
  E peek();
}
