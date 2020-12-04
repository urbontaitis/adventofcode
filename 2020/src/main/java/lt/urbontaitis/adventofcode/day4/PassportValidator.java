package lt.urbontaitis.adventofcode.day4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class PassportValidator {

  private final List<Passport> inputData;

  PassportValidator(String inputData) {
    this.inputData =
        Arrays.stream(inputData.split(",,")).map(this::transform).collect(Collectors.toList());
  }

  private Passport transform(String s) {
    String[] rawFields = s.split("[\s/,]");
    Field byr = null;
    Field iyr = null;
    Field eyr = null;
    Field hgt = null;
    Field hcl = null;
    Field ecl = null;
    Field pid = null;
    Field cid = null;

    for (var rawField : rawFields) {
      String[] field = rawField.split(":");
      String fieldName = field[0];
      String fieldValue = field[1];
      switch (fieldName) {
        case "byr" -> byr = new Field(fieldName, fieldValue);
        case "iyr" -> iyr = new Field(fieldName, fieldValue);
        case "eyr" -> eyr = new Field(fieldName, fieldValue);
        case "hgt" -> hgt = new Field(fieldName, fieldValue);
        case "hcl" -> hcl = new Field(fieldName, fieldValue);
        case "ecl" -> ecl = new Field(fieldName, fieldValue);
        case "pid" -> pid = new Field(fieldName, fieldValue);
        case "cid" -> cid = new Field(fieldName, fieldValue);
        default -> throw new IllegalStateException("Unknown field: " + fieldName);
      }
    }
    return new Passport(byr, iyr, eyr, hgt, hcl, ecl, pid, cid);
  }

  Long validPassportCount() {
    return this.inputData.stream().filter(Passport::hasRequiredFields).count();
  }

  Long validPassportContentCount() {
    return this.inputData.stream().filter(Passport::hasRequiredFields).filter(Passport::isFieldValuesAreValid).count();
  }
}
