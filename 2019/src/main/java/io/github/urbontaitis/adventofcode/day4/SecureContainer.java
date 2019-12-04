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
        new AtLeastTwoAdjacentDigitsAreTheSameValidation(),
        new DigitsNeverDecreaseValidation());
  }

  public List<String> generate(int from, int to) {
    return IntStream.range(from, to)
        .boxed()
        .map(String::valueOf)
        .filter(this::isValid)
        .collect(Collectors.toList());
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
      int first = Integer.parseInt(stringArray[0]);
      for (int i = 1; i < stringArray.length; i++) {
        int neighbor = Integer.parseInt(stringArray[i]);
        if (first > neighbor) {
          throw new PasswordNotValidException();
        }
        first = neighbor;
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
