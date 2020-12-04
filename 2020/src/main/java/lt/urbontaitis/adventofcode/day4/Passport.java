package lt.urbontaitis.adventofcode.day4;

import java.util.Arrays;

record Passport(Field byr, Field iyr, Field eyr, Field hgt, Field hcl, Field ecl, Field pid,
                Field cid) {

  public boolean hasRequiredFields() {
    return byr != null && iyr != null && eyr != null && hgt != null
        && hcl != null && ecl != null && pid != null;
  }

  public boolean isFieldValuesAreValid() {
    return isBirthYearValid() && isIssueYearValid() && isExpirationYearValid()
        && isHeightValid() && isHairColorValid() && isValidEyeColor() && isValidPassportId();
  }

  private boolean isValidPassportId() {
    return pid.value().length() == 9;
  }

  private boolean isValidEyeColor() {
    String eyeColor = ecl.value();
    return Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        .contains(eyeColor);
  }

  private boolean isHairColorValid() {
    String hairColor = hcl.value();
    return hairColor.matches("^#([0-9a-f]{6})");
  }

  private boolean isHeightValid() {
    String rawHeight = hgt.value();
    String mat = rawHeight.substring(rawHeight.length() - 2);
    String heightRaw = rawHeight.substring(0, rawHeight.length() - 2);
    long height = !heightRaw.isBlank() ? Long.parseLong(heightRaw) : 0L;

    return "cm".equals(mat) ? 150 <= height && height <= 193 : 59 <= height && height <= 76;
  }

  private boolean isExpirationYearValid() {
    long expirationYear = Long.parseLong(eyr.value());
    return 2020 <= expirationYear && expirationYear <= 2030;
  }

  private boolean isIssueYearValid() {
    long issueYear = Long.parseLong(iyr.value());
    return 2010 <= issueYear && issueYear <= 2020;
  }

  private boolean isBirthYearValid() {
    long birthYear = Long.parseLong(byr.value());
    return 1920 <= birthYear && birthYear <= 2002;
  }

}
