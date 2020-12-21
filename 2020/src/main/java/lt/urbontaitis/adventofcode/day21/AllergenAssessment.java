package lt.urbontaitis.adventofcode.day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AllergenAssessment {

  private final List<Recipe> inputData;

  public AllergenAssessment(List<String> inputData) {
    this.inputData = inputData.stream().map(this::transform).collect(Collectors.toList());
  }

  public Recipe transform(String s) {
    var raw = s.split("\\(contains ", 2);
    var ingredients = raw[0].split(" ");
    var allergens = raw[1].replaceAll("[)|\\s]", "").split(",");
    return new Recipe(new ArrayList<>(Arrays.asList(ingredients)), new ArrayList<>(Arrays.asList(allergens)));
  }

  record Recipe(List<String> ingredients, List<String> allergens) {}

  private Map<String, String> findDangerousIngredients(List<Recipe> recipes) {
    var groupByAllergens = new HashMap<String, List<String>>();
    for(var recipe : recipes) {
      for(var allergen : recipe.allergens()) {
        if (!groupByAllergens.containsKey(allergen)) {
          groupByAllergens.put(allergen, new ArrayList<>(recipe.ingredients()));
        }
        groupByAllergens.get(allergen).retainAll(recipe.ingredients());
      }
    }

    var dangerousIngredients = new HashMap<String, String>();
    while (dangerousIngredients.size() != groupByAllergens.size()) {
      for(var allergen : groupByAllergens.keySet()) {
        var ingredients = groupByAllergens.get(allergen);
        if(ingredients.size() == 1) {
          var ingredient = ingredients.stream().findFirst().get();
          dangerousIngredients.put(allergen, ingredient);
          for (var pair : groupByAllergens.entrySet()) {
            pair.getValue().remove(ingredient);
            groupByAllergens.put(pair.getKey(), pair.getValue());
          }
        }
      }
    }
    return  dangerousIngredients;
  }

  Integer ingredientsCountWithoutAllergens() {
    var dangerousIngredients = findDangerousIngredients(inputData).values();

    return inputData.stream().mapToInt(recipe -> {
      recipe.ingredients().removeAll(dangerousIngredients);
      return recipe.ingredients().size();
    }).sum();
  }

  String dangerousIngredients() {
    var dangerousIngredients = findDangerousIngredients(inputData);
    var sorted = new TreeMap<>(dangerousIngredients);

    return String.join(",", sorted.values());

  }
}
