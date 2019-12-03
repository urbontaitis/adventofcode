package io.github.urbontaitis.adventofcode.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class CrossedWires {

  private final List<String> wires;

  CrossedWires(List<String> wires) {
    this.wires = wires;
  }

  List<Wire> getWires(int wireIndex) {
    return transformToWire(wires.get(wireIndex));
  }

  public List<Position> getPositions(List<Wire> wires) {
    List<Position> positions = new ArrayList<>();
    int x = 0;
    int y = 0;
    int length = 0;

    for (Wire wire : wires) {
      Direction direction = wire.getDirection();
      for (int i = 0; i < wire.getDistance(); i++) {
        x += direction.getStepX();
        y += direction.getStepY();

        positions.add(new Position(x, y, ++length));
      }
    }

    return positions;
  }

  private List<Wire> transformToWire(String rawData) {
    return Arrays.stream(rawData.split(",")).map(Wire::new).collect(Collectors.toList());
  }

  public Integer getDistance() {
    List<Wire> firstWires = getWires(0);
    List<Wire> secondWires = getWires(1);
    List<Position> firstWirePositions = getPositions(firstWires);
    List<Position> secondWirePositions = getPositions(secondWires);

    List<Integer> distances =
        secondWirePositions.stream()
            .filter(firstWirePositions::contains)
            .map(p -> Math.abs(p.getX()) + Math.abs(p.getY()))
            .sorted(Comparator.comparingInt(p -> p))
            .collect(Collectors.toList());

    return distances.get(0);
  }

  public Integer getDistanceWithFewestSteps() {
    List<Wire> firstWires = getWires(0);
    List<Wire> secondWires = getWires(1);
    List<Position> firstWirePositions = getPositions(firstWires);
    List<Position> secondWirePositions = getPositions(secondWires);

    List<Integer> distances =
        secondWirePositions.stream()
            .filter(firstWirePositions::contains)
            .map(
                p -> {
                  int firstWireIndex = firstWirePositions.indexOf(p);
                  Position firstWirePosition = firstWirePositions.get(firstWireIndex);
                  return firstWirePosition.getLength() + p.getLength();
                })
            .sorted(Comparator.comparingInt(p -> p))
            .collect(Collectors.toList());

    return distances.get(0);
  }
}
