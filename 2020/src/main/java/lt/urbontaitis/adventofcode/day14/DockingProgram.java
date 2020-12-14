package lt.urbontaitis.adventofcode.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DockingProgram {

  private final List<String> inputData;
  private final Pattern pattern = Pattern.compile("^mem\\[(\\d+)\\] = (\\d+)");

  public DockingProgram(List<String> inputData) {
    this.inputData = inputData;
  }

  Long sumOfAllValuesLeftInMemory() {
    Map<Long, Long> memory = new HashMap<>();
    long maskOr = 0;
    long maskAnd = 0;
    for (var raw : inputData) {
      if (raw.startsWith("mask")) {
        var maskValue = raw.split(" = ", 2)[1];
        maskOr = Long.parseLong(maskValue.replace('X', '0'), 2);
        maskAnd = Long.parseLong(maskValue.replace('X', '1'), 2);
      } else {
        var match = pattern.matcher(raw);
        if (!match.find()) {
          System.out.println("Match didn't find any results for: " + raw);
        } else {
          long target = Long.parseLong(match.group(1));
          long number = Long.parseLong(match.group(2));
          long masked = (number | maskOr) & maskAnd;
          memory.put(target, masked);
        }
      }
    }

    return memory.values().stream().reduce(0L, Long::sum);
  }

  Long sumOfAllValuesLeftInMemoryPart2() {
    Map<Long, Long> memory = new HashMap<>();
    String mask = "";

    for (var raw : inputData) {
      if (raw.startsWith("mask")) {
        mask = raw.split( " = ", 2)[1];
      } else {
        Matcher m = pattern.matcher(raw);
        if (!m.find()) {
          System.out.println("Match didn't find any results for: " + raw);
        } else {
          int memoryAddress = Integer.parseInt(m.group(1));
          long addressValue = Long.parseLong(m.group(2));
          char[] memoryAddresses =
              String.format("%36s", Integer.toBinaryString(memoryAddress)).replace(" ", "0").toCharArray();
          Set<char[]> addresses = applyMask(mask, memoryAddresses);
          addresses.stream()
              .map(String::new)
              .map(binary -> Long.parseLong(binary, 2))
              .forEach(adr -> memory.put(adr, addressValue));
        }
      }
    }

    return memory.values().stream().reduce(0L, Long::sum);
  }

  private Set<char[]> applyMask(String mask, char[] memoryAddresses) {
    List<Integer> floatingAddresses = new ArrayList<>();
    for (int i = 0; i < mask.length(); i++) {
      var bitmaskBit = mask.charAt(i);
      switch (bitmaskBit) {
        case 'X' -> floatingAddresses.add(i);
        case '1' -> memoryAddresses[i] = bitmaskBit;
        case '0' -> { }
        default -> throw new IllegalStateException("Invalid bitmask bit: " + bitmaskBit);
      }
    }

    Set<char[]> combinations = new HashSet<>();
    combinations.add(memoryAddresses);
    for (int index : floatingAddresses) {
      combinations = combinations.stream()
          .flatMap(combination -> {
            char[] combination2 = combination.clone();
            combination2[index] = '0';
            combination[index] = '1';
            return Stream.of(combination, combination2);
          })
          .collect(Collectors.toSet());
    }
    return combinations;
  }
}
