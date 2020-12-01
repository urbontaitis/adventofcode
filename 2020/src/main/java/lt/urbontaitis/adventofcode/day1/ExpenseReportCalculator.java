package lt.urbontaitis.adventofcode.day1;

import java.util.List;
import java.util.stream.Collectors;

public class ExpenseReportCalculator {

  public Integer calculateTwoNumbers(List<String> input) {
    List<Integer> numbers = input.stream().map(Integer::valueOf).collect(Collectors.toList());

    for (var number1 : numbers) {
      for (var number2 : numbers) {
        if (number1 + number2 == 2020) {
          return number1 * number2;
        }
      }
    }

    return null;
  }

  public Integer calculateThreeNumbers(List<String> input) {
    List<Integer> numbers = input.stream().map(Integer::valueOf).collect(Collectors.toList());
    for (var number1 : numbers) {
      for (var number2 : numbers) {
        for (var number3 : numbers) {
          if (number1 + number2 + number3 == 2020) {
            return number1 * number2 * number3;
          }
        }
      }
    }

    return null;
  }
}
