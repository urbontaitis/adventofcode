package lt.urbontaitis.adventofcode.day10

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class JoltageMeterSpec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day10/sample.txt")
        def testObject = new JoltageMeter(inputData)

        when:
        def response = testObject.countDiff()

        then:
        220 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day10/input.txt")
        def testObject = new JoltageMeter(inputData)

        when:
        def response = testObject.countDiff()

        then:
        1920 == response
    }

    def "Sample 2 - first example"() {
        given:
        def inputData = ["16",
                         "10",
                         "15",
                         "5",
                         "1",
                         "11",
                         "7",
                         "19",
                         "6",
                         "12",
                         "4"]
        def testObject = new JoltageMeter(inputData)

        when:
        def response = testObject.getPathCount()

        then:
        8 == response
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFileToList("day10/sample.txt")
        def testObject = new JoltageMeter(inputData)

        when:
        def response = testObject.getPathCount()

        then:
        19208 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day10/input.txt")
        def testObject = new JoltageMeter(inputData)

        when:
        def response = testObject.getPathCount()

        then:
        1511207993344 == response


    }
}
