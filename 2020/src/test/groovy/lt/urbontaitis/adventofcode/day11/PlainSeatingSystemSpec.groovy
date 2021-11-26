package lt.urbontaitis.adventofcode.day11

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class PlainSeatingSystemSpec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day11/sample.txt")
        def testObject = new PlainSeatingSystem(inputData)

        when:
        def response = testObject.doSomething()

        then:
        37 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day11/input.txt")
        def testObject = new PlainSeatingSystem(inputData)

        when:
        def response = testObject.doSomething()

        then:
        2481 == response
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFileToList("day11/sample.txt")
        def testObject = new PlainSeatingSystem(inputData)

        when:
        def response = testObject.doSomething2()

        then:
        26 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day11/input.txt")
        def testObject = new PlainSeatingSystem(inputData)

        when:
        def response = testObject.doSomething2()

        then:
        2227 == response
    }
}
