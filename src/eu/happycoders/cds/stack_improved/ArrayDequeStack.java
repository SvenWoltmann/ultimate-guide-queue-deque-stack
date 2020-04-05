package eu.happycoders.cds.stack_improved;

import java.util.*;

public class ArrayDequeStack<E> implements Stack<E> {
  private final Deque<E> deque = new ArrayDeque<>();

  @Override
  public void push(E item) {
    deque.addFirst(item);
  }

  @Override
  public E pop() {
    return deque.removeFirst();
  }

  @Override
  public E peek() {
    return deque.peekFirst();
  }

  // All other Collection methods are forwarded
  // to their corresponding methods in deque:
  // [...]

  @Override
  public int size() {
    return deque.size();
  }

  @Override
  public boolean isEmpty() {
    return deque.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return deque.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return deque.iterator();
  }

  @Override
  public Object[] toArray() {
    return deque.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return deque.toArray(a);
  }

  @Override
  public boolean add(E e) {
    return deque.add(e);
  }

  @Override
  public boolean remove(Object o) {
    return deque.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return deque.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return deque.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return deque.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return deque.retainAll(c);
  }

  @Override
  public void clear() {
    deque.clear();
  }
}
