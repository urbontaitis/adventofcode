package lt.urbontaitis.adventofcode.day13

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification
import spock.lang.Unroll

class BusSchedulerSpec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFile("day13/sample.txt")
        def testObject = new BusScheduler(inputData)

        when:
        def response = testObject.theEarliestBusIdMultipliedByTheWaitTime()

        then:
        295 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFile("day13/input.txt")
        def testObject = new BusScheduler(inputData)

        when:
        def response = testObject.theEarliestBusIdMultipliedByTheWaitTime()

        then:
        2045 == response
    }

    @Unroll
    def "The earliest timestamp that matches the #busList is #expectedTimestamp"() {
        given:
        def testObject = new BusScheduler(busList)

        when:
        def response = testObject.findTheEarliestTimestamp()

        then:
        expectedTimestamp == response

        where:
        busList             | expectedTimestamp
        "0,17,x,13,19"      | 3417
        "0,67,7,59,61"      | 754018
        "0,67,x,7,59,61"    | 779210
        "0,67,7,x,59,61"    | 1261476
        "0,1789,37,47,1889" | 1202161486
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFile("day13/sample.txt")
        def testObject = new BusScheduler(inputData)

        when:
        def response = testObject.findTheEarliestTimestamp()

        then:
        1068781 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFile("day13/input.txt")
        def testObject = new BusScheduler(inputData)

        when:
        def response = testObject.findTheEarliestTimestamp()

        then:
        402251700208309 == response


    }
}
