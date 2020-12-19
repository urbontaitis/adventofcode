package lt.urbontaitis.adventofcode.day19

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class Day19Spec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day19/sample.txt")
        def testObject = new Day19(inputData)

        when:
        def response = testObject.doSomething()

        then:
        !response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day19/input.txt")
        def testObject = new Day19(inputData)

        when:
        def response = testObject.doSomething()

        then:
        !response
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFileToList("day19/sample.txt")
        def testObject = new Day19(inputData)

        when:
        def response = testObject.doSomething2()

        then:
        !response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day19/input.txt")
        def testObject = new Day19(inputData)

        when:
        def response = testObject.doSomething2()

        then:
        !response


    }
}
