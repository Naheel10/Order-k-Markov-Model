//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Markov Model
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

import java.util.HashMap;

/**
 * A class that represents a Markov model for generating random text based on a sample text. The
 * model uses a sliding window approach to analyze the occurrence of characters following a sequence
 * of characters of length k.
 */
public class MarkovModel {

  /**
   * AA map of substrings of length windowWidth to stacks containing the observed characters which
   * follow that substring of characters.
   */
  private HashMap<String, MyStack<Character>> model;

  /**
   * The current windowWidth number of characters that the model will use to predict the next
   * character. Should always be maintained at length windowWidth using methods from MyQueue.
   */
  private MyQueue<Character> currentQueue;

  /**
   * The number of characters to consider in a substring when generating new text.
   */
  private int windowWidth;

  /**
   * A boolean value indicating whether to shuffle the stacks during text generation.
   */
  private boolean shuffleStacks;

  /**
   * Constructs a MarkovModel with a specified order. This model will predict the next character in
   * the generated text based on strings of length k.
   * 
   * @param k       the order of the Markov model (length of substrings to consider).
   * @param shuffle whether this model should shuffle the stacks during the text generation phase.
   */
  public MarkovModel(int k, boolean shuffle) {
    windowWidth = k;
    shuffleStacks = shuffle;
    model = new HashMap<>();
    currentQueue = new MyQueue<>();
  }

  /**
   * Reads in the provided text and builds a model, which maps each k-length substring of the text
   * to a stack containing all of the characters that follow that substring in the text. (See the
   * writeup for more details.)
   * 
   * @param text the text to be processed to build the model.
   */
  public void processText(String text) {
    for (int i = 0; i <= text.length() - windowWidth - 1; i++) {
      String substr = text.substring(i, i + windowWidth);
      if (i + windowWidth < text.length()) {
        char nextChar = text.charAt(i + windowWidth);
        model.computeIfAbsent(substr, k -> new MyStack<>()).push(nextChar);
      }
    }
  }

  /**
   * Initializes the current queue with the first k-letter substring from the text, setting the
   * initial state for text generation.
   * 
   * @param text the text from which to derive the initial queue state.
   */
  public void initializeQueue(String text) {
    for (int i = 0; i < windowWidth && i < text.length(); i++) {
      currentQueue.enqueue(text.charAt(i));
    }
  }

  /**
   * Generates text of a specified length based on the model
   * 
   * @param length   the desired length of the generated text.
   * @param seedText the text to use for re-seeding the model if necessary.
   * @return
   */
  public String generateText(int length, String seedText) {
    if (currentQueue.isEmpty()) {
      initializeQueue(seedText);
    }
    StringBuilder output = new StringBuilder();
    while (output.length() < length) {
      String currentSubstr = queueToString(currentQueue);
      if (!model.containsKey(currentSubstr) || model.get(currentSubstr).isEmpty()) {
        output.append('\n'); // Re-seed if necessary
        initializeQueue(seedText);
        continue;
      }
      MyStack<Character> stack = model.get(currentSubstr);
      if (shuffleStacks) {
        stack.shuffle();
      }
      if (!stack.isEmpty()) {
        Character nextChar = stack.pop();
        output.append(nextChar);
        currentQueue.enqueue(nextChar);
        currentQueue.maintainSize(windowWidth);
      }
    }
    return output.toString();
  }

  /**
   * Converts the elements of a given queue to a string.
   * 
   * @param queue the queue containing characters to be converted to a string
   * @return a string representation of the elements in the queue
   */
  private String queueToString(MyQueue<Character> queue) {
    StringBuilder builder = new StringBuilder();
    for (Character ch : queue.getList()) {
      builder.append(ch);
    }
    return builder.toString();
  }



}
