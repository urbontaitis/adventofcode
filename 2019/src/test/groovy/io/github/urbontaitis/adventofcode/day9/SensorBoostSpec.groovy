package io.github.urbontaitis.adventofcode.day9

import io.github.urbontaitis.adventofcode.day2.Intcode
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class SensorBoostSpec extends Specification {

    List<Long> toLongList(List<Integer> integers) {
        integers.stream().map(Long.&valueOf).collect(Collectors.toList())
    }

    @Unroll
    def "Should support to adjusts the relative mode and expected relative base is #expectedRelativeBase"() {
        given:
        Intcode intcode = new Intcode(dataInput)

        when:
        intcode.diagnostic(1L);

        then:
        intcode.getRelativeBase() == expectedRelativeBase

        where:
        dataInput                  | expectedRelativeBase
        toLongList([109, 19, 99])  | 19L
        toLongList([109, -34, 99]) | -34L
    }


    @Ignore
    @Unroll
    def "Test intcode computer with new features: #dataInput"() {
        given:
        Intcode intcode = new Intcode(dataInput)

        when:
        def output = intcode.diagnostic(1);

        then:
        output == expectedOutput

        where:
        dataInput                                                                              | expectedOutput
        toLongList([109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99]) | 1L
        toLongList([1102, 34915192, 34915192, 7, 4, 7, 99, 0])                                 | 1L
        toLongList([104, 1125899906842624, 99])                                                | 1125899906842624L
    }
}
