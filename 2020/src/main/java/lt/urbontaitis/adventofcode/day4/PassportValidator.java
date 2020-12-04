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

  record Field(String key, String value) {}

  class Passport {
    private final Field birt;
    private final Field iyr;
    private final Field eyr;
    private final Field hgt;
    private final Field hcl;
    private final Field ecl;
    private final Field pid;
    private final Field cid;

    private Passport(Field byr, Field iyr, Field eyr, Field hgt, Field hcl, Field ecl, Field pid, Field cid) {
      this.birt = byr;
      this.iyr = iyr;
      this.eyr = eyr;
      this.hgt = hgt;
      this.hcl = hcl;
      this.ecl = ecl;
      this.pid = pid;
      this.cid = cid;
    }

    public boolean hasRequiredFields() {
      return birt != null && iyr != null && eyr != null && hgt != null
          && hcl != null && ecl != null && pid != null;
    }

    public boolean isFieldValuesIsValid() {
      Long birthYear = Long.valueOf(birt.value);
      boolean isBirthYearValid = 1920 <= birthYear && birthYear <= 2002;
      Long issueYear = Long.valueOf(iyr.value);
      boolean isIssueYearValid = 2010 <= issueYear && issueYear <= 2020;
      Long expirationYear = Long.valueOf(eyr.value);
      boolean isExpirationYearValid = 2020 <= expirationYear && expirationYear <= 2030;
      String rawHeight = hgt.value;
      String mat = rawHeight.substring(rawHeight.length()-2, rawHeight.length());
      String heightRaw = rawHeight.substring(0, rawHeight.length()-2);
      Long height = !heightRaw.isBlank() ? Long.valueOf(heightRaw) : 0L;
      boolean isHeightValid;
      if (mat.equals("cm")) {
        isHeightValid =  150 <= height && height <= 193;
      } else {
        isHeightValid =  59 <= height && height <= 76;
      }
      String hairColor = hcl.value;
      boolean isHairColorValid = hairColor.matches("^#([0-9a-f]{6})");
      String eyeColor = ecl.value;
      boolean isValidEyeColor = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(eyeColor);
      String passportID = pid.value;
      boolean isValidPassportId = passportID.length() == 9;


      return isBirthYearValid && isIssueYearValid && isExpirationYearValid
          && isHeightValid && isHairColorValid && isValidEyeColor && isValidPassportId;
    }
  }

  private Passport transform(String s) {
    List<String> rawFields = Arrays.asList(s.split("[ /,]"));
    Field cid = null;
    Field byr = null;
    Field iyr = null;
    Field eyr = null;
    Field hgt = null;
    Field hcl = null;
    Field ecl = null;
    Field pid = null;
    for (var rawField : rawFields) {
      String[] field = rawField.split(":");
      if ("byr".equals(field[0])) {
        byr = new Field(field[0], field[1]);
      } else if ("iyr".equals(field[0])) {
        iyr = new Field(field[0], field[1]);
      } else if ("eyr".equals(field[0])) {
        eyr = new Field(field[0], field[1]);
      } else if ("hgt".equals(field[0])) {
        hgt = new Field(field[0], field[1]);
      } else if ("hcl".equals(field[0])) {
        hcl = new Field(field[0], field[1]);
      } else if ("ecl".equals(field[0])) {
        ecl = new Field(field[0], field[1]);
      } else if ("pid".equals(field[0])) {
        pid = new Field(field[0], field[1]);
      } else if ("cid".equals(field[0])) {
        cid = new Field(field[0], field[1]);
      }
    }
    return new Passport(byr, iyr, eyr, hgt, hcl, ecl, pid, cid);
  }

  Long validPassportCount() {
    return this.inputData.stream().filter(Passport::hasRequiredFields).count();
  }

  Long validPassportContentCount() {
    return this.inputData.stream().filter(Passport::hasRequiredFields).filter(Passport::isFieldValuesIsValid).count();
  }
}
