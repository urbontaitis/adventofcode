package lt.urbontaitis.adventofcode.day24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

class LobbyLayout {

  private final static List<Direction> DIRECTIONS =
          Arrays.asList(
                  new Direction(2, 0),
                  new Direction(-2, 0),
                  new Direction(1, 2),
                  new Direction(-1, 2),
                  new Direction(1, -2),
                  new Direction(-1, -2));
  private final List<String> inputData;

  LobbyLayout(List<String> inputData) {
    this.inputData = inputData;
  }

  record Direction(int x, int y) {}

  static Direction transform(String directions) {
    int x = 0;
    int y = 0;
    int i = 0;

    while (i < directions.length()) {
      if (directions.startsWith("e", i)) {
        x += 2;
        i += 1;
      } else if (directions.startsWith("w", i)) {
        x += -2;
        i += 1;
      } else if (directions.startsWith("ne", i)) {
        x += 1;
        y += 2;
        i += 2;
      } else if (directions.startsWith("nw", i)) {
        x += -1;
        y += 2;
        i += 2;
      } else if (directions.startsWith("se", i)) {
        x += 1;
        y += -2;
        i += 2;
      } else if (directions.startsWith("sw", i)) {
        x += -1;
        y += -2;
        i += 2;
      } else {
        throw new IllegalArgumentException(directions);
      }
    }

    return new Direction(x, y);
  }

  static Set<Direction> findAllBlackTiles(List<String> rawDirections) {
    var tiles = new HashSet<Direction>();

    for (var rawDirection : rawDirections) {
      Direction direction = transform(rawDirection);
      if (tiles.contains(direction)) {
        tiles.remove(direction);
      } else {
        tiles.add(direction);
      }
    }

    return tiles;
  }

  static Map<Direction, Integer> countTiles(Set<Direction> blackTiles) {
    var counts = new HashMap<Direction, Integer>();
    for (var tile : blackTiles) {
      for (var direction : DIRECTIONS) {
        var temp = new Direction(tile.x + direction.x, tile.y + direction.y);
        int count = counts.getOrDefault(temp, 0);
        counts.put(temp, count + 1);
      }
    }
    return counts;
  }

  static Set<Direction> tilesToAdd(Set<Direction> blackTiles, Map<Direction, Integer> counts) {
    return counts.keySet().stream()
            .filter(
                    t -> {
                      var count = counts.get(t);
                      return count == 2 && !blackTiles.contains(t);
                    })
            .collect(toSet());
  }

  static Set<Direction> tilesToRemove(Set<Direction> blackTiles, Map<Direction, Integer> counts) {
    return blackTiles.stream()
            .filter(
                    t -> {
                      var count = counts.get(t);
                      return count == null || count > 2;
                    })
            .collect(toSet());
  }

  int countBlackTiles() {
    return findAllBlackTiles(inputData).size();
  }

  int countBlackTilesAfter100Days() {
    var blackTiles = findAllBlackTiles(inputData);

    for (int i = 0; i < 100; i++) {
      var tileCounts = countTiles(blackTiles);
      var toDiscard = tilesToRemove(blackTiles, tileCounts);
      var toAdd = tilesToAdd(blackTiles, tileCounts);

      blackTiles.removeAll(toDiscard);
      blackTiles.addAll(toAdd);
    }

    return blackTiles.size();
  }
}
