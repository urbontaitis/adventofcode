package lt.urbontaitis.adventofcode.day12

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class FerryNavigationSystemSpec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day12/sample.txt")
        def testObject = new FerryNavigationSystem(inputData)

        when:
        def response = testObject.findTheManhattanDistance()

        then:
        25 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day12/input.txt")
        def testObject = new FerryNavigationSystem(inputData)

        when:
        def response = testObject.findTheManhattanDistance()

        then:
        904 == response
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFileToList("day12/sample.txt")
        def testObject = new FerryNavigationSystem(inputData)

        when:
        def response = testObject.findTheManhattanDistancePart2()

        then:
        286 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day12/input.txt")
        def testObject = new FerryNavigationSystem(inputData)

        when:
        def response = testObject.findTheManhattanDistancePart2()

        then:
        18747 == response
    }
}
