package lt.urbontaitis.adventofcode.day4

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class Day4Spec extends Specification {

    def SAMPLE_FILE = FileReader.readFile("day4.txt")
    def SAMPLE_INPUT = """_"""

    def "Sample 1"() {
        given:
        def testObject = new Day4(SAMPLE_INPUT)

        when:
        def result = testObject.calculatePart1()

        then:
        result == null
    }

    def "Part 1"() {
        given:
        def testObject = new Day4(SAMPLE_FILE)

        when:
        def result = testObject.calculatePart1()

        then:
        result == null
    }

    def "Sample 2"() {
        given:
        def testObject = new Day4(SAMPLE_INPUT)

        when:
        def result = testObject.calculatePart2()

        then:
        result == null
    }

    def "Part 2"() {
        given:
        def testObject = new Day4(SAMPLE_FILE)

        when:
        def result = testObject.calculatePart2()

        then:
        result == null
    }
}
