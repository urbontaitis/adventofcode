package io.github.urbontaitis.adventofcode.day5

import io.github.urbontaitis.adventofcode.FileReader
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class IntcodeUpgradeSpec extends Specification {

    IntcodeUpgrade testObject

    def setup() {
        String dataInputPath = "day5/input.txt"
        String file = FileReader.readFile(dataInputPath)
        List<Integer> dataInput = Arrays.stream(file.split(",")).map(Integer.&valueOf).collect(Collectors.toList())
        testObject = new IntcodeUpgrade(dataInput)
    }

    def "Proceed 3 opcode - move the input"() {
        given:
        int input = 1
        LinkedList<Integer> initialState = [3, 0, 4, 0, 99]
        int opcodeIndex = 0

        when:
        LinkedList<Integer> state = testObject.move(opcodeIndex, initialState, input)

        then:
        state == [1, 0, 4, 0, 99]
    }

    def "Proceed 4 opcode - outputs parameter"() {
        given:
        LinkedList<Integer> initialState = [3, 0, 4, 0, 99]
        int opcodeIndex = 0

        when:
        Integer state = testObject.output(opcodeIndex, initialState)

        then:
        state == 3
    }

    def "Should support parameter modes: #initialState"() {
        given:
        int opcodeIndex = 0
        int input = 1

        when:
        int steps = testObject.parameterMode(opcodeIndex, initialState, input)

        then:
        steps == expectedSteps
        initialState == expectedState

        where:
        initialState        | expectedState | expectedSteps
        [1002, 4, 3, 4, 33] | [1002, 4, 3, 4, 99] | 3
        [1101,100,-1,4,0]   | [1101,100,-1,4,99] | 3

    }

    def "Execute the test diagnostics"() {
        given:
        def input = 1

        when:
        int debugCode = testObject.diagnostic(input)

        then:
        debugCode == 12440243
    }
}
