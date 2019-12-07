package io.github.urbontaitis.adventofcode.day7

import io.github.urbontaitis.adventofcode.FileReader
import io.github.urbontaitis.adventofcode.day5.IntcodeUpgrade
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class AmplificationCircuitSpec extends Specification {

    @Unroll
    def "Calculate thruster using sequence: #sequence"() {
        given:
        AmplificationCircuit ac = new AmplificationCircuit(state)

        when:
        Integer thruster = ac.calculateThruster(sequence)

        then:
        thruster == maxThruster

        where:
        maxThruster | sequence        | state
        43210       | [4, 3, 2, 1, 0] | [3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0]
        54321       | [0, 1, 2, 3, 4] | [3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0]
        65210       | [1, 0, 4, 3, 2] | [3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33, 1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0]
    }

    @Unroll
    def "Find highest signal - expected is #maxThruster"() {
        given:
        AmplificationCircuit ac = new AmplificationCircuit(state)

        when:
        Integer thruster = ac.findMaxThruster()

        then:
        thruster == maxThruster

        where:
        maxThruster | state
        43210       | [3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0]
        54321       | [3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0]
        65210       | [3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33, 1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0]

    }


    def "Find highest signal"() {
        given:
        String dataInputPath = "day7/input.txt"
        String file = FileReader.readFile(dataInputPath)
        List<Integer> dataInput = Arrays.stream(file.split(",")).map(Integer.&valueOf).collect(Collectors.toList())
        AmplificationCircuit ac = new AmplificationCircuit(dataInput);

        when:
        Integer thruster = ac.findMaxThruster();

        then:
        thruster == 368584
    }

}
