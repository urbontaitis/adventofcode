package io.github.urbontaitis.adventofcode.day5

import io.github.urbontaitis.adventofcode.FileReader
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

    @Unroll
    def "Proceed 3 opcode - move the input by mode: #modeA"() {
        given:
        int input = 1
        LinkedList<Integer> initialState = [3, 0, 4, 0, 99]
        int opcodeIndex = 0

        when:
        LinkedList<Integer> state = testObject.move(modeA, opcodeIndex, initialState, input)

        then:
        state == expectedState

        where:
        modeA | expectedState
        0     | [1, 0, 4, 0, 99]
        1     | [3, 1, 4, 0, 99]
    }

    @Unroll
    def "Proceed 4 opcode - outputs parameter by mode: #modeA"() {
        given:
        LinkedList<Integer> initialState = [3, 0, 4, 0, 99]
        int opcodeIndex = 0

        when:
        Integer state = testObject.output(modeA, opcodeIndex, initialState)

        then:
        state == output

        where:
        modeA | output
        0     | 3
        1     | 0
    }

    def "Should support parameter modes: #dataInput"() {
        given:
        IntcodeUpgrade intcode = new IntcodeUpgrade(dataInput)
        int input = 1

        when:
        intcode.diagnostic(input)

        then:
        intcode.getTemp() == expectedState

        where:
        dataInput             | expectedState
        [1002, 4, 3, 4, 33]   | [1002, 4, 3, 4, 99]
        [1101, 100, -1, 4, 0] | [1101, 100, -1, 4, 99]

    }

    @Unroll
    def "Part 2 test data: #dataInput with input: #input and expected: #output"() {
        given:
        IntcodeUpgrade intcode = new IntcodeUpgrade(dataInput)

        when:
        int debugCode = intcode.diagnostic(input)

        then:
        debugCode == output

        where:
        dataInput                                                                                                                                                                                                | input | output
        [3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8]                                                                                                                                                                     | 8     | 1
        [3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8]                                                                                                                                                                     | 9     | 0
        [3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8]                                                                                                                                                                     | 7     | 1
        [3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8]                                                                                                                                                                     | 8     | 0
        [3, 3, 1108, -1, 8, 3, 4, 3, 99]                                                                                                                                                                         | 8     | 1
        [3, 3, 1108, -1, 8, 3, 4, 3, 99]                                                                                                                                                                         | 9     | 0
        [3, 3, 1107, -1, 8, 3, 4, 3, 99]                                                                                                                                                                         | 7     | 1
        [3, 3, 1107, -1, 8, 3, 4, 3, 99]                                                                                                                                                                         | 8     | 0
        [3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9]                                                                                                                                                | 0     | 0
        [3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9]                                                                                                                                                | 2     | 1
        [3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1]                                                                                                                                                        | 0     | 0
        [3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1]                                                                                                                                                        | 3     | 1
        [3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99] | 0     | 999
        [3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99] | 8     | 1000
        [3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99] | 9     | 1001

    }

    @Unroll
    def "Execute the test diagnostics with #input and expect: #output"() {
        when:
        int debugCode = testObject.diagnostic(input)

        then:
        debugCode == output

        where:
        input | output
        1     | 12440243
        5     | 15486302
    }
}
