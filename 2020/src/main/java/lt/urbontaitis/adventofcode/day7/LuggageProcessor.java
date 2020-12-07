package lt.urbontaitis.adventofcode.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class LuggageProcessor {

  private final List<Bag> inputData;

  LuggageProcessor(List<String> inputData) {
    this.inputData = inputData.stream().map(BagMapper::transform).collect(Collectors.toList());
  }

  private void findMatchingBagColors(List<Bag> bags, List<Bag> matchedBagColors, String bagColor) {
    bags.forEach(
        bag -> {
          if (bag.innerBags().containsKey(bagColor) && !matchedBagColors.contains(bag)) {
            matchedBagColors.add(bag);
            findMatchingBagColors(bags, matchedBagColors, bag.color());
          }
        });
  }

  Integer countHowManyBagsCanHoldGivenBagColor(String bagColor) {
    List<Bag> matchedBagColors = new ArrayList<>();
    findMatchingBagColors(inputData, matchedBagColors, bagColor);

    return matchedBagColors.size();
  }

  private Integer countInnerBags(List<Bag> bags, String bagColor) {
    return bags.stream()
        .filter(bag -> bagColor.equals(bag.color()))
        .findFirst()
        .orElseThrow(
            () -> {
              throw new IllegalStateException("Failed to find bag by color " + bagColor);
            })
        .innerBags()
        .entrySet()
        .stream()
        .mapToInt(
            innerBag ->
                innerBag.getValue()
                    + (innerBag.getValue() * countInnerBags(bags, innerBag.getKey())))
        .sum();
  }

  Integer howManyInnerBagsAreRequired(String bagColor) {
    return countInnerBags(inputData, bagColor);
  }
}
