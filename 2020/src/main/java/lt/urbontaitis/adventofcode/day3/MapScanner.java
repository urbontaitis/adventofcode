package lt.urbontaitis.adventofcode.day3;

import java.util.List;
import java.util.stream.Collectors;

class MapScanner {

  private final List<char[]> inputData;

  MapScanner(List<String> inputData) {
    this.inputData = inputData.stream().map(String::toCharArray).collect(Collectors.toList());
  }

  record Slope(int right, int down) {}

  Long countTrees(Slope slope) {
    var count = 0L;
    int rowLength = inputData.get(0).length;
    int x = slope.right;
    int y = slope.down;
    while (y < inputData.size()) {
      var location = inputData.get(y)[x];

      if (location == '#') {
        count++;
      }
      x += slope.right;
      x = x % rowLength;
      y += slope.down;
    }

    return count;
  }

  Long countTotalTrees(List<Slope> slopes) {
    return slopes.stream().map(this::countTrees).reduce(1L, (x, y) -> x * y);
  }
}
