package io.github.urbontaitis.adventofcode.day1

import io.github.urbontaitis.adventofcode.FileReader
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class TheTyrannyOfTheRocketEquationSpec extends Specification {

    TheTyrannyOfTheRocketEquation testObject

    def setup() {
        String dataInputPath = "day1/input.txt"
        String file = FileReader.readFile(dataInputPath)
        List<Integer> dataInput = Arrays.stream(file.split(",")).map(Integer.&valueOf).collect(Collectors.toList())
        testObject = new TheTyrannyOfTheRocketEquation(dataInput)
    }

    @Unroll
    def "Calculate fuel requirements by #mass" () {
        when:
        Integer result = testObject.calculateFuelRequirements(mass)

        then:
        result == expected

        where:
        mass   | expected
        12     | 2
        14     | 2
        1969   | 654
        100756 | 33583
    }

    def "Calculate sum of fuel requirements" () {
        when:
        Integer result = testObject.sumOfFuelRequirements()

        then:
        result == 3406527
    }

    @Unroll
    def "Calculate total fuel requirements by #mass" () {
        when:
        Integer result = testObject.calculateTotalFuelRequirements(mass)

        then:
        result == expected

        where:
        mass   | expected
        14     | 2
        1969   | 966
        100756 | 50346
    }

    def "Calculate total sum of fuel requirements" () {
        when:
        Integer result = testObject.sumOfTotalFuelRequirements()

        then:
        result == 5106932
    }
}
