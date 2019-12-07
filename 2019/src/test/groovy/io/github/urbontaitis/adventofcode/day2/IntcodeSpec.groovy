package io.github.urbontaitis.adventofcode.day2

import io.github.urbontaitis.adventofcode.FileReader
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class IntcodeSpec extends Specification {

    Intcode testObject

    def setup() {
        String dataInputPath = "day2/input.txt"
        String file = FileReader.readFile(dataInputPath)
        List<Integer> dataInput = Arrays.stream(file.split(",")).map(Integer.&valueOf).collect(Collectors.toList())
        testObject = new Intcode(dataInput)
    }

    def "Loads initial data"() {
        when:
        List<Integer> data = testObject.getDataInput()

        then:
        data != null
        data.size() == 145
    }

    def "Proceed 1 opcode - sum" () {
        given:
        LinkedList<Integer> initialState = [1,0,0,0,99]
        int opcodeIndex = 0

        when:
        LinkedList<Integer> state = testObject.sum(opcodeIndex, initialState)

        then:
        state == [2,0,0,0,99]
    }

    def "Proceed 2 opcode - sum"() {
        given:
        LinkedList<Integer> initialState = [2,3,0,3,99]
        int opcodeIndex = 0

        when:
        LinkedList<Integer> state = testObject.multiply(opcodeIndex, initialState)

        then:
        state == [2,3,0,6,99]
    }

    @Unroll
    def "Read program state: #initialState"() {
        when:
        LinkedList<Integer> state = testObject.executeState(initialState)

        then:
        state == finalState

        where:
        initialState | finalState
        [1,0,0,0,99] | [2,0,0,0,99]
        [2,3,0,3,99] | [2,3,0,6,99]
        [2,4,4,5,99,0] | [2,4,4,5,99,9801]
        [1,1,1,4,99,5,6,0,99] | [30,1,1,4,2,5,6,0,99]
        [1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50] | [3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50]
    }

    def "Restore state" () {
        given:
        def first = 12
        def second  = 2

        when:
        LinkedList<Integer> state = testObject.restoreState(first, second)

        then:
        state.first == 7594646
    }
}
