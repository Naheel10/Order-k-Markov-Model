//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: My Stack
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
import java.util.Collections;

/**
 * A generic singly-linked stack implementation, which contains some additional methods to
 * facilitate the workings of the Markov Model. As such, this stack may NOT always strictly adhere
 * to the last-in-first-out protocols of standard stacks!
 * 
 * @param <T> the type of data contained in the stack
 */
public class MyStack<T> implements StackADT<T> {

  /**
   * A reference to the LinkedNode currently at the top of the stack, which is null when the stack
   * is empty.
   */
  private LinkedNode<T> top;

  /**
   * Add a new element to the top of this stack, assumed to be non-null.
   * 
   * @param value the value to add
   */
  public void push(T data) {
    LinkedNode<T> newNode = new LinkedNode<>(data, top);
    top = newNode;
  }

  /**
   * Removes and returns the value added to this stack most recently
   * 
   * @return the most recently-added value, or null if the stack is empty
   */
  public T pop() {
    if (top == null) {
      return null; // Ensure this matches your expectation in the test
    }
    T result = top.getData();
    top = top.getNext();
    return result;
  }

  /**
   * Accesses the value added to this stack most recently, without modifying the stack
   * 
   * @return the most recently-added value, or null if the stack is empty
   */
  public T peek() {
    return (top != null) ? top.getData() : null;
  }

  /**
   * Returns true if this stack contains no elements.
   * 
   * @return true if the stack contains no elements, false otherwise
   */
  public boolean isEmpty() {
    return top == null;
  }


  /**
   * Creates a copy of the current contents of this stack in the order they are present here, in
   * ArrayList form. This method should traverse the stack without removing any elements, and add
   * the values (not the nodes!) to an ArrayList in the order they appear in the stack, with the top
   * of the stack at index 0.
   * 
   * @return an ArrayList representation of the current state of this stack
   */
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = top;
    while (current != null) {
      list.add(current.getData());
      current = current.getNext();
    }
    return list;
  }

  /**
   * Randomly reorder the contents of this stack
   */
  public void shuffle() {
    ArrayList<T> elements = new ArrayList<>();
    LinkedNode<T> current = top;
    while (current != null) {
      elements.add(current.getData());
      current = current.getNext();
    }
    Collections.shuffle(elements);

    top = null;
    for (T element : elements) {
      push(element); // Note: This re-reverses the order
    }
  }

}
