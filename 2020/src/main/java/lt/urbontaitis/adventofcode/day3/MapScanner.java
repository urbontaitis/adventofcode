package lt.urbontaitis.adventofcode.day3;

import java.util.List;
import java.util.stream.Collectors;

class MapScanner {

  private final List<String[]> inputData;

  MapScanner(List<String> inputData) {
    this.inputData = inputData.stream().map(MapScanner::transform).collect(Collectors.toList());
  }

  private static String[] transform(String s) {
    return s.split("");
  }

  record Slope(int right, int down) {}

  Long countTrees(Slope slope) {
    var count = 0L;
    var lines = inputData;
    int rowLength = lines.get(0).length;
    int x = slope.right;
    int y = slope.down;
    while (y < lines.size()) {
      var location = lines.get(y)[x];

      if (location.equals("#")) {
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
