package lt.urbontaitis.adventofcode.day20;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day20 {

  private final List<Tile> inputData;
  private final String SEA_MONSTER = """
                            # 
          #    ##    ##    ###
           #  #  #  #  #  #   
          """;
  private final Pattern TILE = Pattern.compile("^Tile\\s(\\d+):");

  public Day20(String inputData) {
    this.inputData =
        Arrays.stream(inputData.trim().split(",,")).map(this::transform).collect(Collectors.toList());
  }

  private Tile transform(String raw) {
    var rawData = raw.split(",");
    var matcher = TILE.matcher(rawData[0]);
    matcher.find();
    int id = Integer.parseInt(matcher.group(1));
    var grid = new ArrayList<List<String>>();
    for(int i = 1; i < rawData.length - 1; i++) {
      grid.add(Arrays.asList(rawData[i].split("")));
    }
    return new Tile(id, grid);
  }

  record Grid(List<List<String>> pixels) {
    List<String> top() {
      return pixels.get(0);
    }
    List<String> right() {
      return pixels.stream().map(i -> i.get(i.size() - 1)).collect(Collectors.toList());
    }
    List<String> left() {
      return pixels.stream().map(i -> i.get(0)).collect(Collectors.toList());
    }
    List<String> bottom() {
      return pixels.get(pixels.size() - 1);
    }
    List<List<String>> flipVertical() {
      var clone = new ArrayList<>(pixels);
      Collections.reverse(clone);
      return clone;
    }
    List<List<String>> flipHorizontal() {
      var clone = new ArrayList<>(pixels);
      clone.forEach(Collections::reverse);
      return clone;
    }
    List<List<String>> rotate() {
      var clone = new ArrayList<>(pixels);
      Collections.reverse(clone);
      clone.forEach(Collections::reverse);
      return clone;
    }
  }

  record Tile(int id, List<List<String>> grid) {
    Grid getBorders() {
      return new Grid(grid);
    }
  }


  Map<Integer, List<List<String>>> generateAllEdges(List<Tile> tiles) {
    Map<Integer, List<List<String>>> options = new HashMap<>();

    return options;
  }

  Long doSomething() {
    System.out.println(inputData);
    var options = generateAllEdges(inputData);


    return null;
  }

  Long doSomething2() {
    return null;
  }
}
