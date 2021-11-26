package lt.urbontaitis.adventofcode.day17;

import java.util.List;

public class Day17 {

  private final List<String> inputData;

  public Day17(List<String> inputData) {
    this.inputData = inputData;
  }

  Long doSomething() {
    var size = inputData.size();
    char[][][] space = new char[size][size][size];
    for(int y = 0; y < size; y++) {
      var line = inputData.get(y).toCharArray();
      for(int x = 0; x < line.length; x++) {
        var item = line[x];
        if(item == '#') {
          space[0][y][x] = item;
        }
      }
    }

    for(int i = 0; i < 6; i++) {
        char[][][] marked = new char[size][size][size];
        for(int j, z, y, x = 0; j < size; j++) {

        }
    }

    return null;
  }

  Long doSomething2() {

    return null;
  }
}
