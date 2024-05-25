//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: My Queue
// Course: CS 300 Spring 2024
//
// Author: Muhammad Naheel
// Email: naheel@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * A generic singly-linked queue implementation, which contains some additional methods to
 * facilitate the workings of the Markov Model.
 * 
 * @param <T> the type of data contained in the queue
 */
public class MyQueue<T> implements QueueADT<T> {

  /**
   * A reference to the LinkedNode currently at the front of the queue, which contains the
   * least-recently added value in the queue.
   */
  private LinkedNode<T> front;

  /**
   * A reference to the LinkedNode currently at the back of the queue, which contains the
   * most-recently added value in the queue.
   */
  private LinkedNode<T> back;

  /**
   * The number of values currently present in the queue.
   */
  private int size;

  /**
   * Add a new element to the back of the queue, assumed to be non-null.
   * 
   * @param value the value to add
   */
  public void enqueue(T data) {
    LinkedNode<T> newNode = new LinkedNode<>(data);
    if (back != null) {
      back.setNext(newNode);
    }
    back = newNode;
    if (front == null) {
      front = back;
    }
    size++;
  }

  /**
   * Removes and returns the value added to this queue least recently
   * 
   * @return the least recently-added value, or null if the queue is empty
   */
  public T dequeue() {
    if (front == null) {
      return null;
    }
    T data = front.getData();
    front = front.getNext();
    if (front == null) {
      back = null; // Also update the back pointer if the queue becomes empty
    }
    size--;
    return data;
  }

  /**
   * Accesses the value added to this queue least recently, without modifying the queue
   * 
   * @return the least recently-added value, or null if the queue is empty
   */
  public T peek() {
    return (front != null) ? front.getData() : null;
  }

  /**
   * Returns true if this queue contains no elements.
   * 
   * @return true if the queue contains no elements, false otherwise
   */
  public boolean isEmpty() {
    return front == null;
  }

  /**
   * Returns the number of elements in the queue.
   * 
   * @return the number of elements in the queue
   */
  public int size() {
    return size;
  }

  /**
   * Enforces a maximum size for this queue. If the queue is already smaller than the requested
   * size, this method does nothing.
   * 
   * @param maxSize the maximum number of elements this queue should contain once the method has run
   */
  public void maintainSize(int maxSize) {
    while (size > maxSize) {
      dequeue();
    }
  }

  /**
   * Creates a copy of the current contents of this queue in the order they are present here, in
   * ArrayList form. This method should traverse the queue without removing any elements, and add
   * the values (not the nodes!) to an ArrayList in the order they appear in the queue.
   * 
   * @return an ArrayList representation of the current state of this queue
   */
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = front;
    while (current != null) {
      list.add(current.getData());
      current = current.getNext();
    }
    return list;
  }

  /**
   * Concatenates the string representation of all values in this queue in order, from the front of
   * the queue to the back. Does not separate values (no whitespace, no commas).
   * 
   * @return the string representation of this queue
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    LinkedNode<T> current = front;
    while (current != null) {
      sb.append(current.getData().toString());
      if (current.getNext() != null) {
        sb.append(", ");
      }
      current = current.getNext();
    }
    sb.append("]");
    return sb.toString();
  }

}
