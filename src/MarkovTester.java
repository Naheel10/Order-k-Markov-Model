//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Markov Tester
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
import java.util.Arrays;
import java.util.Collections;

/**
 * This class tests the MarkovModel class and it's methods.
 */
public class MarkovTester {

  /**
   * Test adding elements to the stack and verifies the order of elements after addition and
   * popping.
   */
  public static boolean testStackAdd() {
    MyStack<Integer> stack = new MyStack<>();
    stack.push(10);
    stack.push(50);
    stack.push(100);

    // Peek the top element (should be 100)
    boolean topIsCorrect = (stack.peek() == 100);

    // Now pop elements and verify the order
    ArrayList<Integer> currentList = new ArrayList<>();
    currentList.add(stack.pop());
    currentList.add(stack.pop());
    currentList.add(stack.pop());

    ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(100, 50, 10)); // Expected order

    // Verify both the top element and the order of elements
    return topIsCorrect && currentList.equals(expectedList);
  }

  /**
   * Test removing elements from the stack and verifies the order of elements after removal.
   */
  public static boolean testStackRemove() {
    MyStack<Integer> stack = new MyStack<>();
    stack.push(10);
    stack.push(50);
    stack.push(100);

    // Pop elements and verify the order
    ArrayList<Integer> currentList = new ArrayList<>();
    currentList.add(stack.pop());
    currentList.add(stack.pop());
    currentList.add(stack.pop());

    ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(100, 50, 10)); // Expected order

    // Verify the order of elements
    return currentList.equals(expectedList) && stack.isEmpty();
  }


  /**
   * Test shuffling elements in the stack.
   */
  public static boolean testStackShuffle() {
    MyStack<Integer> stack = new MyStack<>();
    stack.push(10);
    stack.push(50);
    stack.push(100);
    ArrayList<Integer> originalList = new ArrayList<>(stack.getList());
    stack.shuffle();
    ArrayList<Integer> shuffledList = stack.getList();

    return !shuffledList.equals(originalList) && shuffledList.containsAll(originalList);
  }

  /**
   * Test adding elements to the queue and verifies the order of elements after addition.
   */
  public static boolean testQueueAdd() {
    MyQueue<Integer> queue = new MyQueue<>();
    queue.enqueue(10);
    queue.enqueue(50);
    queue.enqueue(100);
    ArrayList<Integer> currentList = queue.getList();
    ArrayList<Integer> expectedList = new ArrayList<>();
    Collections.addAll(expectedList, 10, 50, 100);

    return currentList.equals(expectedList) && queue.peek() == 10;
  }

  /**
   * Test removing elements from the queue and verifies the order of elements after removal.
   */
  public static boolean testQueueRemove() {
    MyQueue<Integer> queue = new MyQueue<>();
    queue.enqueue(10);
    queue.enqueue(50);
    queue.enqueue(100);
    queue.dequeue(); // Should remove 10

    ArrayList<Integer> currentList = queue.getList();
    ArrayList<Integer> expectedList = new ArrayList<>(Arrays.asList(50, 100));

    boolean correctSizeAfterDequeue = queue.size() == 2; // Correct use of size()

    // Ensure the correct size after dequeuing
    if (!correctSizeAfterDequeue)
      return false;

    queue.maintainSize(1); // Adjust the size to 1, removing element 50

    boolean correctSizeAfterMaintain = queue.size() == 1;
    ArrayList<Integer> listAfterMaintain = queue.getList();
    ArrayList<Integer> expectedListAfterMaintain = new ArrayList<>(Arrays.asList(100));

    // Ensure the correct size and list contents after maintaining size
    return correctSizeAfterMaintain && listAfterMaintain.equals(expectedListAfterMaintain);
  }


  /**
   * Test the peek method for both stack and queue and verifies the top/front element.
   */
  public static boolean testPeek() {
    MyStack<Integer> stack = new MyStack<>();
    stack.push(10);
    stack.push(50);
    stack.push(100); // Top element should be 100
    Integer peekStack = stack.peek(); // Should be 100

    ArrayList<Integer> stackList = stack.getList();
    ArrayList<Integer> expectedStackList = new ArrayList<>(Arrays.asList(100, 50, 10));

    boolean stackTest = peekStack.equals(100) && stackList.equals(expectedStackList);

    MyQueue<Integer> queue = new MyQueue<>();
    queue.enqueue(10); // Front element
    queue.enqueue(50);
    queue.enqueue(100);
    Integer peekQueue = queue.peek(); // Should be 10

    ArrayList<Integer> queueList = queue.getList();
    ArrayList<Integer> expectedQueueList = new ArrayList<>(Arrays.asList(10, 50, 100));

    boolean queueTest = peekQueue.equals(10) && queueList.equals(expectedQueueList);

    return stackTest && queueTest;
  }


  public static void main(String[] args) {
    System.out.println("Test Stack Add: " + (testStackAdd() ? "PASSED" : "FAILED"));
    System.out.println("Test Stack Remove: " + (testStackRemove() ? "PASSED" : "FAILED"));
    System.out.println("Test Stack Shuffle: " + (testStackShuffle() ? "PASSED" : "FAILED"));
    System.out.println("Test Queue Add: " + (testQueueAdd() ? "PASSED" : "FAILED"));
    System.out.println("Test Queue Remove: " + (testQueueRemove() ? "PASSED" : "FAILED"));
    System.out.println("Test Peek: " + (testPeek() ? "PASSED" : "FAILED"));
  }
}
