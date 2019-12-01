package io.github.urbontaitis.adventofcode.day1;

import java.util.List;

public class TheTyrannyOfTheRocketEquation {

  private final List<Integer> dataInput;

  TheTyrannyOfTheRocketEquation(List<Integer> dataInput) {
    this.dataInput = dataInput;
  }

  protected int calculateFuelRequirements(Integer mass) {
    return mass / 3 - 2;
  }

  public Integer sumOfFuelRequirements() {
    return dataInput.stream().mapToInt(this::calculateFuelRequirements).sum();
  }

  public Integer calculateTotalFuelRequirements(Integer mass) {
    int fuelRequirements = calculateFuelRequirements(mass);
    int totalFuelRequirements = fuelRequirements;

    while (fuelRequirements > 0) {
      fuelRequirements = calculateFuelRequirements(fuelRequirements);

      if (fuelRequirements < 0) {
        return totalFuelRequirements;
      }

      totalFuelRequirements += fuelRequirements;
    }

    return totalFuelRequirements;
  }

  public Integer sumOfTotalFuelRequirements() {
      return dataInput.stream().mapToInt(this::calculateTotalFuelRequirements).sum();
  }
}
