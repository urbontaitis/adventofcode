package io.github.urbontaitis.adventofcode.day2

import io.github.urbontaitis.adventofcode.FileReader
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class IntcodeSpec extends Specification {

    private static final long opcodeIndex = 0L

    List<Long> toLongList(List<Integer> integers) {
        integers.stream().map(Long.&valueOf).collect(Collectors.toList())
    }

    def "Loads initial data"() {
        given:
        String dataInputPath = "day2/input.txt"
        String file = FileReader.readFile(dataInputPath)
        List<Long> dataInput = Arrays.stream(file.split(",")).map(Long.&valueOf).collect(Collectors.toList())
        Intcode testObject = new Intcode(dataInput)

        when:
        List<Long> data = testObject.getDataInput()

        then:
        data != null
        data.size() == 145
    }

    def "Proceed 1 opcode - sum"() {
        given:
        List<Long> initialState = toLongList([1, 0, 0, 0, 99])
        Intcode testObject = new Intcode(initialState)

        when:
        List<Long> state = testObject.sum(opcodeIndex)

        then:
        state == toLongList([2, 0, 0, 0, 99])
    }

    def "Proceed 2 opcode - sum"() {
        given:
        List<Long> initialState = toLongList([2, 3, 0, 3, 99])
        Intcode testObject = new Intcode(initialState)

        when:
        List<Long> state = testObject.multiply(opcodeIndex)

        then:
        state == toLongList([2, 3, 0, 6, 99])
    }

    @Unroll
    def "Read program state: #initialState"() {
        given:
        Intcode testObject = new Intcode(initialState)

        when:
        testObject.executeState()

        then:
        testObject.getTemp() == finalState

        where:
        initialState                                           | finalState
        toLongList([1, 0, 0, 0, 99])                           | toLongList([2, 0, 0, 0, 99])
        toLongList([2, 3, 0, 3, 99])                           | toLongList([2, 3, 0, 6, 99])
        toLongList([2, 4, 4, 5, 99, 0])                        | toLongList([2, 4, 4, 5, 99, 9801])
        toLongList([1, 1, 1, 4, 99, 5, 6, 0, 99])              | toLongList([30, 1, 1, 4, 2, 5, 6, 0, 99])
        toLongList([1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50]) | toLongList([3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50])
    }

    def "Restore state"() {
        given:
        String dataInputPath = "day2/input.txt"
        String file = FileReader.readFile(dataInputPath)
        List<Long> dataInput = Arrays.stream(file.split(",")).map(Long.&valueOf).collect(Collectors.toList())
        Intcode testObject = new Intcode(dataInput)
        def first = 12
        def second = 2

        when:
        testObject.restoreState(first, second)

        then:
        testObject.getTemp().get(0) == 7594646L
    }
}
