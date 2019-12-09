package io.github.urbontaitis.adventofcode.day9

import io.github.urbontaitis.adventofcode.day5.IntcodeUpgrade
import spock.lang.Specification
import spock.lang.Unroll

class SensorBoostSpec extends Specification {

    @Unroll
    def "Should support to adjusts the relative mode and expected relative base is #expectedRelativeBase"() {
        given:
        IntcodeUpgrade intcode = new IntcodeUpgrade(dataInput)

        when:
        intcode.diagnostic(1);

        then:
        intcode.getRelativeBase() == expectedRelativeBase

        where:
        dataInput      | expectedRelativeBase
        [109, 19, 99]  | 19
        [109, -34, 99] | -34
    }


    @Unroll
    def "Test intcode computer with new features: #dataInput"() {
        given:
        IntcodeUpgrade intcode = new IntcodeUpgrade(dataInput)

        when:
        def output = intcode.diagnostic(1);

        then:
        output == expectedOutput

        where:
        dataInput | expectedOutput
        [109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99] | 1
        [1102, 34915192, 34915192, 7, 4, 7, 99, 0]                                 | 1
        [104, 1125899906842624, 99]                                                | 1125899906842624
    }
}
