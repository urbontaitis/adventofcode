package lt.urbontaitis.adventofcode.day1

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

import java.util.stream.Collectors

class SonarSweepSpec extends Specification {

    SonarSweep testObject = new SonarSweep()

    def "Sample 1"() {
        def inputData = [
                199,
                200,
                208,
                210,
                200,
                207,
                240,
                269,
                260,
                263]

        when:
        def result = testObject.countDepthMeasurementIncrease(inputData)

        then:
        result == 7
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day1.txt").stream().map(i -> Integer.valueOf(i)).collect(Collectors.toList())

        when:
        def result = testObject.countDepthMeasurementIncrease(inputData)

        then:
        result == 1387
    }

    def "Sample 2"() {
        def inputData = [
                199,
                200,
                208,
                210,
                200,
                207,
                240,
                269,
                260,
                263]

        when:
        def result = testObject.threeMeasurementsSlidingWindow(inputData)

        then:
        result == 5
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day1.txt").stream().map(i -> Integer.valueOf(i)).collect(Collectors.toList())

        when:
        def result = testObject.threeMeasurementsSlidingWindow(inputData)

        then:
        result == 1362
    }
}
