package lt.urbontaitis.adventofcode.day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CrabCupsSimulation {

  private final List<Integer> inputData;

  public CrabCupsSimulation(String inputData) {
    this.inputData = Arrays.stream(inputData.split("")).map(Integer::parseInt).collect(toList());
  }

  Integer runSimulationOneHundredTimes() {
    int max = Collections.max(inputData);

    for(int i = 0; i < 100; i++) {
      int current = inputData.remove(0);
      inputData.add(current);
      var taken = new ArrayList<Integer>();
      for(int j = 0; j < 3; j++) {
        taken.add(inputData.remove(0));
      }

      int currentLabel = current -1;
      if( currentLabel == 0) {
        currentLabel = max;
      }
      while(taken.contains(currentLabel)) {
        currentLabel = currentLabel - 1;
        if (currentLabel == 0) {
          currentLabel = max;
        }
      }

      int currentCupIndex = inputData.indexOf(currentLabel);
      Collections.rotate(inputData, -1 * currentCupIndex - 1);
      inputData.addAll(taken);
      Collections.rotate(inputData, currentCupIndex + 4);
    }

    Collections.rotate(inputData, -inputData.indexOf(1));
    inputData.remove(0);
    return Integer.valueOf(inputData.stream().map(String::valueOf).collect(Collectors.joining()));
  }

  Long runSimulationTenMillionsTime() {
    Map<Integer, Node> cups = new HashMap<>();

    Node previous = null;
    for(var number : inputData) {
      var cup = new Node(number);
      if (previous != null) {
        previous.add(cup);
      }
      previous = cup;
      cups.put(number, cup);
    }
    for(int number = inputData.size() + 1; number <= 1000000; number++) {
      var cup = new Node(number);
      previous.add(cup);
      previous = cup;
      cups.put(number, cup);
    }

    int currentLabel = inputData.get(0);
    int max = Collections.max(cups.keySet());
    for(int i = 0; i < 10000000; i++) {
      var currentCup = cups.get(currentLabel);
      var taken = new ArrayList<Node>();
      for(int j = 0; j < 3; j++) {
        taken.add(currentCup.next.remove());
      }

      int destinationLabel = currentLabel - 1;
      if(destinationLabel < 1) {
        destinationLabel = max;
      }

      var destinationCup = cups.get(destinationLabel);
      while(taken.contains(destinationCup)) {
        destinationLabel = destinationLabel - 1;
        if (destinationLabel == 0) {
          destinationLabel = max;
        }

        destinationCup = cups.get(destinationLabel);
      }

      for(int j = 2; j >= 0; j--) {
        destinationCup.add(taken.get(j));

      }

      currentLabel = currentCup.next.value;
    }

    var cup1 = cups.get(1).next;
    return Long.valueOf(cup1.value) * Long.valueOf(cup1.next.value);
  }

  static class Node {
    Node next = this;
    Node previous = this;

    public final Integer value;

    Node(Integer value) {
      this.value = value;
    }

    public Node remove() {
      previous.next = next;
      next.previous = previous;

      return this;
    }

    public void add(Node node) {
      next.previous = node;
      node.previous = this;
      node.next = next;
      this.next = node;
    }
  }
}
