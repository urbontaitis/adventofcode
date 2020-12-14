package lt.urbontaitis.adventofcode.day14

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class DockingProgramSpec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day14/sample.txt")
        def testObject = new DockingProgram(inputData)

        when:
        def response = testObject.sumOfAllValuesLeftInMemory()

        then:
        165 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day14/input.txt")
        def testObject = new DockingProgram(inputData)

        when:
        def response = testObject.sumOfAllValuesLeftInMemory()

        then:
        12512013221615 == response
    }

    def "Sample 2"() {
        given:
        def inputData = [
                "mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1"
        ]
        def testObject = new DockingProgram(inputData)

        when:
        def response = testObject.sumOfAllValuesLeftInMemoryPart2()

        then:
        208 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day14/input.txt")
        def testObject = new DockingProgram(inputData)

        when:
        def response = testObject.sumOfAllValuesLeftInMemoryPart2()

        then:
        3905642473893 == response


    }
}
