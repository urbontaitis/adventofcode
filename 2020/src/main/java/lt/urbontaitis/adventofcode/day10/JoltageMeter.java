package lt.urbontaitis.adventofcode.day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JoltageMeter {

  private final List<Integer> inputData;

  public JoltageMeter(List<String> inputData) {
    this.inputData = inputData.stream().map(Integer::valueOf).collect(Collectors.toList());
    this.inputData.add(0);
    this.inputData.sort(Comparator.naturalOrder());
  }

  Integer countDiff() {
    int tempNumber = 0;
    int jolts1 = 0;
    int jolts3 = 1;
    for(Integer number : inputData) {
      int diff = number - tempNumber;
      if(diff == 3) {
        jolts3 += 1;
      } else if( diff == 1) {
        jolts1 += 1;
      }
      tempNumber = number;
    }

    return jolts1 * jolts3;
  }

  long getPathCount() {
    Map<Integer, List<Integer>> possibleConnections = new HashMap<>();
    for (int i = inputData.size() - 1; i >= 0; --i) {
      int j = i - 1;
      List<Integer> arrangements = new ArrayList<>();
      while (j >= 0 && inputData.get(i) <= inputData.get(j) + 3) {
        arrangements.add(inputData.get(j));
        --j;
      }
      possibleConnections.put(inputData.get(i), arrangements);
    }

    Map<Integer, Long> paths = new HashMap<>();
    paths.put(0, 1L);
    long total = 0;
    for (int i = 1; i < inputData.size(); ++i) {
      total = 0;
      for (int e : possibleConnections.get(inputData.get(i))) {
        total += paths.get(e);
      }
      paths.put(inputData.get(i), total);
    }

    return total;
  }

}
