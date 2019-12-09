package io.github.urbontaitis.adventofcode.day5

import io.github.urbontaitis.adventofcode.FileReader
import io.github.urbontaitis.adventofcode.day2.Intcode
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class IntcodeUpgradeSpec extends Specification {

    public static final long opcodeIndex = 0L
    public static final long input = 1L

    List<Long> toLongList(List<Integer> integers) {
        integers.stream().map(Long.&valueOf).collect(Collectors.toList())
    }

    @Unroll
    def "Proceed 3 opcode - move the input by mode: #modeA"() {
        given:
        List<Long> initialState = toLongList([3, 0, 4, 0, 99])
        Intcode testObject = new Intcode(initialState)

        when:
        List<Long> state = testObject.move(modeA, opcodeIndex, input)

        then:
        state == expectedState

        where:
        modeA | expectedState
        0L    | toLongList([1, 0, 4, 0, 99])
        1L    | toLongList([3, 1, 4, 0, 99])
    }

    @Unroll
    def "Proceed 4 opcode - outputs parameter by mode: #modeA"() {
        given:
        List<Long> initialState = toLongList([3, 0, 4, 0, 99])
        Intcode testObject = new Intcode(initialState)

        when:
        Integer state = testObject.output(modeA, opcodeIndex)

        then:
        state == output

        where:
        modeA | output
        0L    | 3L
        1L    | 0L
    }

    def "Should support parameter modes: #dataInput"() {
        given:
        Intcode intcode = new Intcode(dataInput)

        when:
        intcode.diagnostic(input)

        then:
        intcode.getTemp() == expectedState

        where:
        dataInput                         | expectedState
        toLongList([1002, 4, 3, 4, 33])   | toLongList([1002, 4, 3, 4, 99])
        toLongList([1101, 100, -1, 4, 0]) | toLongList([1101, 100, -1, 4, 99])

    }

    @Unroll
    def "Part 2 test data: #dataInput with input: #input and expected: #output"() {
        given:
        Intcode intcode = new Intcode(dataInput)

        when:
        Long debugCode = intcode.diagnostic(input)

        then:
        debugCode == output

        where:
        dataInput                                                                                                                                                                                                            | input | output
        toLongList([3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8])                                                                                                                                                                     | 9L    | 0L
        toLongList([3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8])                                                                                                                                                                     | 8L    | 1L
        toLongList([3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8])                                                                                                                                                                     | 7L    | 1L
        toLongList([3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8])                                                                                                                                                                     | 8L    | 0L
        toLongList([3, 3, 1108, -1, 8, 3, 4, 3, 99])                                                                                                                                                                         | 8L    | 1L
        toLongList([3, 3, 1108, -1, 8, 3, 4, 3, 99])                                                                                                                                                                         | 9L    | 0L
        toLongList([3, 3, 1107, -1, 8, 3, 4, 3, 99])                                                                                                                                                                         | 7L    | 1L
        toLongList([3, 3, 1107, -1, 8, 3, 4, 3, 99])                                                                                                                                                                         | 8L    | 0L
        toLongList([3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9])                                                                                                                                                | 0L    | 0L
        toLongList([3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9])                                                                                                                                                | 2L    | 1L
        toLongList([3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1])                                                                                                                                                        | 0L    | 0L
        toLongList([3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1])                                                                                                                                                        | 3L    | 1L
        toLongList([3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99]) | 0L    | 999L
        toLongList([3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99]) | 8L    | 1000L
        toLongList([3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99]) | 9L    | 1001L

    }

    @Unroll
    def "Execute the test diagnostics with #input and expect: #output"() {
        given:
        String dataInputPath = "day5/input.txt"
        String file = FileReader.readFile(dataInputPath)
        List<Long> dataInput = Arrays.stream(file.split(",")).map(Long.&valueOf).collect(Collectors.toList())
        Intcode testObject = new Intcode(dataInput)

        when:
        Long debugCode = testObject.diagnostic(input)

        then:
        debugCode == output

        where:
        input | output
        1L    | 12440243L
        5L    | 15486302L
    }
}
