package lt.urbontaitis.adventofcode.day2

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class SubmarineControllerSpec extends Specification {

    def SAMPLE_FILE = FileReader.readFile("day2.txt")
    def SAMPLE_INPUT = """forward 5
down 5
forward 8
up 3
down 8
forward 2"""

    def "Sample 1"() {
        given:
        def testObject = new SubmarineController(SAMPLE_INPUT)

        when:
        def result = testObject.calculatePart1()

        then:
        result == 150
    }

    def "Part 1"() {
        given:
        def testObject = new SubmarineController(SAMPLE_FILE)

        when:
        def result = testObject.calculatePart1()

        then:
        result == 1660158
    }

    def "Sample 2"() {
        given:
        def testObject = new SubmarineController(SAMPLE_INPUT)

        when:
        def result = testObject.calculatePart2()

        then:
        result == 900
    }

    def "Part 2"() {
        given:
        def testObject = new SubmarineController(SAMPLE_FILE)

        when:
        def result = testObject.calculatePart2()

        then:
        result == 1604592846
    }
}
