package lt.urbontaitis.adventofcode.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class BagMapper {

  private BagMapper() {}

  protected static Bag transform(String rawBag) {
    var bag = Pattern.compile("bags contain").split(rawBag);
    var color = bag[0].trim();
    var holdsInnerBags = bag[1];
    var innerBags = parseInnerBags(holdsInnerBags);
    return new Bag(color, innerBags);
  }

  private static Map<String, Integer> parseInnerBags(String rawInnerBags) {
    var innerBags = new HashMap<String, Integer>();
    if (!rawInnerBags.contains("no other bags")) {
      var rawBags = rawInnerBags.replace("bags", "").replace("bag", "").replace(".", "").split(",");
      for (var rawBag : rawBags) {
        var innerBag = rawBag.trim().split(" ", 2);
        innerBags.put(innerBag[1], Integer.valueOf(innerBag[0]));
      }
    }
    return innerBags;
  }
}
