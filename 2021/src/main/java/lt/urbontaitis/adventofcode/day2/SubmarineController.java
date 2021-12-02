package lt.urbontaitis.adventofcode.day2;

import java.util.List;

public class SubmarineController {

    private final List<SubmarineCommand> data;

    public SubmarineController(String inputData) {
       data = inputData.lines().map(raw -> {
           var rawCommand = raw.split("\s");
           return new SubmarineCommand(Direction.valueOf(rawCommand[0]), Integer.valueOf(rawCommand[1]));
       }).toList();
    }

    public Integer calculatePart1() {
        int depth = 0;
        int position = 0;

        for(var command : data) {
            switch (command.direction) {
                case up -> position -= command.position;
                case down -> position += command.position;
                case forward -> depth += command.position;
            }
        }

        return depth * position;
    }

    public Integer calculatePart2() {
        int aim = 0;
        int depth = 0;
        int position = 0;

        for(var command : data) {
            switch (command.direction) {
                case up -> aim -= command.position;
                case down -> aim += command.position;
                case forward -> {
                    position += command.position;
                    depth += aim * command.position;
                }
            }
        }

        return depth * position;
    }

    record SubmarineCommand(Direction direction, Integer position) {}

    enum Direction {
        forward,
        down,
        up
    }
}
