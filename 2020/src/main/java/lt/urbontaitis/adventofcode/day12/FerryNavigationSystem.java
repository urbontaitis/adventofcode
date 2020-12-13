package lt.urbontaitis.adventofcode.day12;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class FerryNavigationSystem {

    private final List<Navigation> inputData;
    private final List<Position> waypoints = Arrays.asList(
            new Position(0, 1),//east
            new Position(1, 0),//north
            new Position(0, -1),//south
            new Position(-1, 0));//west


    public FerryNavigationSystem(List<String> inputData) {
        this.inputData = inputData.stream().map(this::transform).collect(Collectors.toList());
    }

    private Navigation transform(String s) {
        String[] raw = s.split("", 2);
        return new Navigation(Direction.valueOf(raw[0]), Integer.valueOf(raw[1]));
    }

    Integer findTheManhattanDistance() {
        var waypointPosition = waypoints.get(0);
        var shipPosition = new Position(0, 0);

        for (Navigation navigation : inputData) {
            switch (navigation.direction()) {
                case N -> shipPosition = shipPosition.subY(navigation.units());
                case S -> shipPosition = shipPosition.addY(navigation.units());
                case E -> shipPosition = shipPosition.addX(navigation.units());
                case W -> shipPosition = shipPosition.subX(navigation.units());
                case L -> {
                    int rotations = navigation.units() / 90;
                    int waypointIndex = waypoints.indexOf(waypointPosition);
                    int newWaypointIndex = BigInteger.valueOf(waypointIndex - rotations).mod(BigInteger.valueOf(4)).intValue();
                    waypointPosition = waypoints.get(newWaypointIndex);
                }
                case R -> {
                    int rotations = navigation.units() / 90;
                    int waypointIndex = waypoints.indexOf(waypointPosition);
                    int newWaypointIndex = (waypointIndex + rotations) % 4;
                    waypointPosition = waypoints.get(newWaypointIndex);
                }
                case F -> shipPosition = shipPosition.add(waypointPosition, navigation.units());
            }
        }
        return Math.abs(shipPosition.x()) + Math.abs(shipPosition.y());
    }

    Integer findTheManhattanDistancePart2() {
        var waypointPosition = new Position(10, 1);
        var shipPosition = new Position(0, 0);

        for (Navigation navigation : inputData) {
            switch (navigation.direction()) {
                case N -> waypointPosition = waypointPosition.addY(navigation.units());
                case S -> waypointPosition = waypointPosition.subY(navigation.units());
                case E -> waypointPosition = waypointPosition.addX(navigation.units());
                case W -> waypointPosition = waypointPosition.subX(navigation.units());
                case L -> {
                    int rotations = navigation.units() / 90;
                    for (int i = 0; i < rotations; i++) {
                        waypointPosition = waypointPosition.swap();
                        waypointPosition = waypointPosition.oppositeX();
                    }
                }
                case R -> {
                    int rotations = navigation.units() / 90;
                    for (int i = 0; i < rotations; i++) {
                        waypointPosition = waypointPosition.swap();
                        waypointPosition = waypointPosition.oppositeY();
                    }
                }
                case F -> shipPosition = shipPosition.add(waypointPosition, navigation.units());
            }

        }
        return Math.abs(shipPosition.x()) + Math.abs(shipPosition.y());
    }
}
