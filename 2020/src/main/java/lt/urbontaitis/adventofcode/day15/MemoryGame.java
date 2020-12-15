package lt.urbontaitis.adventofcode.day15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryGame {

  private final List<Integer> inputData;

  public MemoryGame(List<Integer> inputData) {
    this.inputData = inputData;
  }

  Integer play(int number) {
    Map<Integer, Integer> spoken = new HashMap<>();
    for(int i = 0; i < inputData.size(); i++) {
      var spokenNumber = inputData.get(i);
      spoken.put(spokenNumber, i + 1);
    }

    int turn = inputData.size() + 1;
    int nextSpoken = 0;

    while(turn < number) {
      if (spoken.containsKey(nextSpoken)) {
        int diff = turn - spoken.get(nextSpoken);
        spoken.put(nextSpoken, turn);
        nextSpoken = diff;
      } else {
        spoken.put(nextSpoken, turn);
        nextSpoken = 0;
      }
        turn++;
    }

    return nextSpoken;
  }
}
