package lt.urbontaitis.adventofcode.day8

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class BootCodeRunnerSpec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day8/sample.txt")
        def testObject = new BootCodeRunner(inputData)

        when:
        def response = testObject.run()

        then:
        5 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day8/input.txt")
        def testObject = new BootCodeRunner(inputData)

        when:
        def response = testObject.run()

        then:
        1420 == response
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFileToList("day8/sample.txt")
        def testObject = new BootCodeRunner(inputData)

        when:
        def response = testObject.runSelfHealing()

        then:
        8 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day8/input.txt")
        def testObject = new BootCodeRunner(inputData)

        when:
        def response = testObject.runSelfHealing()

        then:
        1245 == response


    }
}
