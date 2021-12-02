package lt.urbontaitis.adventofcode.day1

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class SonarSweepSpec extends Specification {

    def SAMPLE_FILE = FileReader.readFile("day1.txt")
    def SAMPLE_INPUT = """199
200
208
210
200
207
240
269
260
263"""

    def "Sample 1"() {
        given:
        def testObject = new SonarSweep(SAMPLE_INPUT)

        when:
        def result = testObject.countDepthMeasurementIncrease()

        then:
        result == 7
    }

    def "Part 1"() {
        given:
        def testObject = new SonarSweep(SAMPLE_FILE)

        when:
        def result = testObject.countDepthMeasurementIncrease()

        then:
        result == 1387
    }

    def "Sample 2"() {
        given:
        def testObject = new SonarSweep(SAMPLE_INPUT)

        when:
        def result = testObject.threeMeasurementsSlidingWindow()

        then:
        result == 5
    }

    def "Part 2"() {
        given:
        def testObject = new SonarSweep(SAMPLE_FILE)

        when:
        def result = testObject.threeMeasurementsSlidingWindow()

        then:
        result == 1362
    }
}
