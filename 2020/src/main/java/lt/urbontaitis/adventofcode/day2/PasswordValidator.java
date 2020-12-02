package lt.urbontaitis.adventofcode.day2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordValidator {

  private final List<PasswordGroup> inputData;

  public PasswordValidator(List<String> inputData) {
    this.inputData =
        inputData.stream().map(PasswordValidator::transform).collect(Collectors.toList());
  }

  record PasswordGroup(Integer min, Integer max, String c, String password) {}

  private static PasswordGroup transform(String rawData) {
    var separatedData = rawData.split(" ");
    var limits = separatedData[0].split("-");
    return new PasswordGroup(
        Integer.valueOf(limits[0]),
        Integer.valueOf(limits[1]),
        separatedData[1].replace(":", ""),
        separatedData[2]);
  }

  private static boolean isValidByFirstPolicy(PasswordGroup p) {
    var charCount = Arrays.stream(p.password.split("")).filter(c -> c.equals(p.c)).count();
    return p.min <= charCount && charCount <= p.max;
  }

  private static boolean isValidByTheOfficialTobogganCorporatePolicy(PasswordGroup p) {
    var password = p.password;
    var character = p.c;
    var firstChar = String.valueOf(password.charAt(p.min - 1));
    var lastChar = String.valueOf(password.charAt(p.max - 1));
    var isFirstCharIsEqual = character.equals(firstChar);
    var isLastCharIsEqual = character.equals(lastChar);

    return isFirstCharIsEqual && !isLastCharIsEqual || isLastCharIsEqual && !isFirstCharIsEqual;
  }

  Long validPasswordCountByFirstPolicy() {
    return inputData.stream().filter(PasswordValidator::isValidByFirstPolicy).count();
  }

  Long validPasswordCountByTheOfficialTobogganCorporatePolicy() {
    return inputData.stream()
        .filter(PasswordValidator::isValidByTheOfficialTobogganCorporatePolicy)
        .count();
  }
}
