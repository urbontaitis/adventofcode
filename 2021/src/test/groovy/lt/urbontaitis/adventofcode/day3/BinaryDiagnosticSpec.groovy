package lt.urbontaitis.adventofcode.day3

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class BinaryDiagnosticSpec extends Specification {

    def SAMPLE_FILE = FileReader.readFile("day3.txt")
    def SAMPLE_INPUT = """00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010"""

    def "Sample 1"() {
        given:
        def testObject = new BinaryDiagnostic(SAMPLE_INPUT)

        when:
        def result = testObject.calculatePowerConsumption()

        then:
        result == 198
    }

    def "Part 1"() {
        given:
        def testObject = new BinaryDiagnostic(SAMPLE_FILE)

        when:
        def result = testObject.calculatePowerConsumption()

        then:
        result == 4147524
    }

    def "Sample 2"() {
        given:
        def testObject = new BinaryDiagnostic(SAMPLE_INPUT)

        when:
        def result = testObject.calculateLifeSupportRating()

        then:
        result == 230
    }

    def "Part 2"() {
        given:
        def testObject = new BinaryDiagnostic(SAMPLE_FILE)

        when:
        def result = testObject.calculateLifeSupportRating()

        then:
        result == 3570354
    }
}
