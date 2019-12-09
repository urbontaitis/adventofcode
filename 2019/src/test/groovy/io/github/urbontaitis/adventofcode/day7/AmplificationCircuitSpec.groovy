package io.github.urbontaitis.adventofcode.day7

import io.github.urbontaitis.adventofcode.FileReader
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class AmplificationCircuitSpec extends Specification {

    List<Long> toLongList(List<Long> Longs) {
        Longs.stream().map(Long.&valueOf).collect(Collectors.toList())
    }

    @Unroll
    def "Calculate thruster using sequence: #sequence"() {
        given:
        AmplificationCircuit ac = new AmplificationCircuit(state)

        when:
        Long thruster = ac.calculateThruster(sequence)

        then:
        thruster == maxThruster

        where:
        maxThruster | sequence                    | state
        43210L      | toLongList([4, 3, 2, 1, 0]) | toLongList([3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0])
        54321L      | toLongList([0, 1, 2, 3, 4]) | toLongList([3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0])
        65210L      | toLongList([1, 0, 4, 3, 2]) | toLongList([3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33, 1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0])
    }

    @Ignore
    @Unroll
    def "Calculate thruster using sequence: #sequence 2"() {
        given:
        AmplificationCircuit ac = new AmplificationCircuit(state)

        when:
        Long thruster = ac.feedbackLoopTemp(sequence, maxThruster)

        then:
        thruster == maxThruster

        where:
        maxThruster | sequence                    | state
        139629729L  | toLongList([9, 8, 7, 6, 5]) | toLongList([3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5])
        18216L      | toLongList([9, 7, 8, 5, 6]) | toLongList([3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54, -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4, 53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10])
    }

    @Unroll
    def "Find highest signal - expected is #maxThruster"() {
        given:
        AmplificationCircuit ac = new AmplificationCircuit(state)

        when:
        Long thruster = ac.findMaxThruster(sequenceTemplate)

        then:
        thruster == maxThruster

        where:
        sequenceTemplate | maxThruster | state
        "01234"          | 43210L      | toLongList([3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0])
        "01234"          | 54321L      | toLongList([3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0])
    }

    @Ignore
    @Unroll
    def "Find highest signal with feedback loop - expected is #maxThruster"() {
        given:
        AmplificationCircuit ac = new AmplificationCircuit(state)

        when:
        Long thruster = ac.findMaxThrusterWithFeedBackLoop(sequenceTemplate)

        then:
        thruster == maxThruster

        where:
        sequenceTemplate | maxThruster | state
        "56789"          | 139629729L  | toLongList([3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5])
        "56789"          | 18216L      | toLongList([3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54, -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4, 53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10])
    }


    def "Find highest signal"() {
        given:
        String dataInputPath = "day7/input.txt"
        String file = FileReader.readFile(dataInputPath)
        List<Long> dataInput = Arrays.stream(file.split(",")).map(Long.&valueOf).collect(Collectors.toList())
        AmplificationCircuit ac = new AmplificationCircuit(dataInput);

        when:
        Long thruster = ac.findMaxThruster("01234");

        then:
        thruster == 368584L
    }

}
