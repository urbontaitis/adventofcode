package io.github.urbontaitis.adventofcode.day3

import io.github.urbontaitis.adventofcode.FileReader
import spock.lang.Specification
import spock.lang.Unroll

class CrossedWiresSpec extends Specification {

    CrossedWires testObject

    def setup() {
        String dataInputPath = "day3/input.txt"
        List<String> wires = FileReader.readFileToList(dataInputPath)
        testObject = new CrossedWires(wires)
    }

    def "Get x and y positions from #path"() {
        when:
        Wire wire = new Wire(path)

        then:
        wire.getDirection() == direction
        wire.getDistance() == distance

        where:
        path | direction   | distance
        "R8" | Direction.R | 8
        "U5" | Direction.U | 5
        "L5" | Direction.L | 5
        "D3" | Direction.D | 3
    }


    @Unroll
    def "Get positions from #wires"() {
        when:
        List<Position> positions = testObject.getPositions(wires)

        then:
        positions.size() == size

        where:
        wires                                                            | size
        [new Wire("R8"), new Wire("U5"), new Wire("L5"), new Wire("D3")] | 21
        [new Wire("U7"), new Wire("R6"), new Wire("D4"), new Wire("L4")] | 21

    }

    def "Closest intersection of #path"() {
        given:
        testObject = new CrossedWires(path)

        when:
        Integer actualDistance = testObject.getDistance()

        then:
        actualDistance == distance

        where:
        path                                                                                    | distance
        ["R8,U5,L5,D3", "U7,R6,D4,L4"]                                                          | 6
        ["R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"]               | 159
        ["R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"] | 135
    }

    def "The fewest combined steps the wires must take to reach an intersection: #path"() {
        given:
        testObject = new CrossedWires(path)

        when:
        Integer actualDistance = testObject.getDistanceWithFewestSteps()

        then:
        actualDistance == distance

        where:
        path                                                                                    | distance
        ["R8,U5,L5,D3", "U7,R6,D4,L4"]                                                          | 30
        ["R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"]               | 610
        ["R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"] | 410
    }

    def "What is the Manhattan distance from the central port to the closest intersection?" () {
        when:
        Integer distance = testObject.getDistance()

        then:
        distance == 1225

    }

    def "What is the fewest combined steps the wires must take to reach an intersection?" () {
        when:
        Integer distance = testObject.getDistanceWithFewestSteps()

        then:
        distance == 107036

    }


}
