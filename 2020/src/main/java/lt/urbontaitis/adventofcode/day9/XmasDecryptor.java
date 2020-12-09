package lt.urbontaitis.adventofcode.day9;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class XmasDecryptor {

  private final List<Long> inputData;

  public XmasDecryptor(List<String> inputData) {
    this.inputData = inputData.stream().map(Long::valueOf).collect(Collectors.toList());
  }

  Long findFirstWhichIsNotTheSumOfTwo(int preambleLength) {
    for (int i = preambleLength; i < inputData.size(); i++) {
      var preambleValues = inputData.subList(i - preambleLength, i);
      var found = false;
      for (var preambleValue : preambleValues) {
        if (preambleValues.contains(inputData.get(i) - preambleValue)) {
          found = true;
          break;
        }
      }
      if (!found) {
        return inputData.get(i);
      }
    }
    return null;
  }

  Long findTheEncryptionWeakness(int preambleLength) {
    var target = findFirstWhichIsNotTheSumOfTwo(preambleLength);
    var current = inputData.get(0);
    int start = 0;
    int end = 0;

    while (true) {
      if (current < target) {
        end += 1;
        current += inputData.get(end);
      } else if (current > target) {
        current -= inputData.get(start);
        start += 1;
      } else {
        var sliced = inputData.subList(start, end + 1);
        return Collections.min(sliced) + Collections.max(sliced);
      }
    }
  }
}
