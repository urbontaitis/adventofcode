package io.github.urbontaitis.adventofcode.day4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SecureContainer {

  public Boolean isValid(String password) {
    try {
      rules().forEach(rule -> rule.accept(password));

      return true;
    } catch (PasswordNotValidException e) {
      return false;
    }
  }

  List<Consumer<String>> rules() {
    return Arrays.asList(
        new PasswordLengthValidation(),
        new DigitsNeverDecreaseValidation(),
        new AtLeastTwoAdjacentDigitsAreTheSameValidation());
  }

  List<Consumer<String>> rulesPart2() {
    return Arrays.asList(
        new PasswordLengthValidation(),
        new DigitsNeverDecreaseValidation(),
        new AllRepeatedDigitsAreExactlyTwoDigitsLongValidation());
  }

  public List<String> generate(int from, int to) {
    return IntStream.range(from, to)
        .boxed()
        .map(String::valueOf)
        .filter(this::isValid)
        .collect(Collectors.toList());
  }

  public List<String> generatePart2(int from, int to) {
    return IntStream.range(from, to)
        .boxed()
        .map(String::valueOf)
        .filter(this::isValidPart2)
        .collect(Collectors.toList());
  }

  public Boolean isValidPart2(String password) {
    try {
      rulesPart2().forEach(rule -> rule.accept(password));

      return true;
    } catch (PasswordNotValidException e) {
      return false;
    }
  }

  class PasswordLengthValidation implements Consumer<String> {
    @Override
    public void accept(String password) {
      if (password.length() != 6) {
        throw new PasswordNotValidException();
      }
    }
  }

  class DigitsNeverDecreaseValidation implements Consumer<String> {
    @Override
    public void accept(String password) {
      String[] stringArray = password.split("");
      for (int i = 0; i < stringArray.length - 1; i++) {
        int first = Integer.parseInt(stringArray[i]);
        int neighbor = Integer.parseInt(stringArray[i + 1]);
        if (first > neighbor) {
          throw new PasswordNotValidException();
        }
      }
    }
  }

  class AllRepeatedDigitsAreExactlyTwoDigitsLongValidation implements Consumer<String> {
    @Override
    public void accept(String password) {
      List<String> validDigits = IntStream.range(1, 10)
      .boxed()
      .map(String::valueOf)
      .filter(d -> password.lastIndexOf(d) - password.indexOf(d) == 1 )
      .collect(Collectors.toList());

      if (validDigits.isEmpty()) {
        throw new PasswordNotValidException();
      }
    }
  }

  class AtLeastTwoAdjacentDigitsAreTheSameValidation implements Consumer<String> {
    @Override
    public void accept(String password) {
      String[] stringArray = password.split("");
      boolean found = false;
      for (int i = 0; i < stringArray.length - 1; i++) {
        int first = Integer.parseInt(stringArray[i]);
        int neighbor = Integer.parseInt(stringArray[i + 1]);
        if (first == neighbor) {
          found = true;
          break;
        }
      }

      if (!found) {
        throw new PasswordNotValidException();
      }
    }
  }

  private class PasswordNotValidException extends RuntimeException {}
}
