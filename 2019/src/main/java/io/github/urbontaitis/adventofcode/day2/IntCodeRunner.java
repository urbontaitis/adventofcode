package io.github.urbontaitis.adventofcode.day2;

import io.github.urbontaitis.adventofcode.FileReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntCodeRunner {

  public static void main(String[] args) throws IOException {
    run(19690720L);
  }

  static void run(Long output) throws IOException {
    String dataInputPath = "day2/input.txt";
    String file = FileReader.readFile(dataInputPath);
    List<Long> dataInput =
        Arrays.stream(file.split(",")).map(Long::valueOf).collect(Collectors.toList());

    for (int i = 0; i <= 99; i++) {
      for (int j = 0; j <= 99; j++) {
        Intcode intcode = new Intcode(dataInput);
        intcode.restoreState(i, j);
        List<Long> state = intcode.getTemp();
        if (state.get(0).equals(output)) {
          System.out.println("noun=" + state.get(1) + " verb=" + state.get(2));
        }
      }
    }

    System.out.println("Done");
  }
}
