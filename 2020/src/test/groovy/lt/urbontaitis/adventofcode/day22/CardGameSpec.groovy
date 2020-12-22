package lt.urbontaitis.adventofcode.day22

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class CardGameSpec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFile("day22/sample.txt")
        def testObject = new CardGame(inputData)

        when:
        def response = testObject.playCombat()

        then:
        306 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFile("day22/input.txt")
        def testObject = new CardGame(inputData)

        when:
        def response = testObject.playCombat()

        then:
        33694 == response
    }


    def "Recursive sample" () {
        given:
        def inputData = FileReader.readFile("day22/recursive-sample.txt")
        def testObject = new CardGame(inputData)

        when:
        def response = testObject.playRecursiveCombat()

        then:
        105 == response

    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFile("day22/sample.txt")
        def testObject = new CardGame(inputData)

        when:
        def response = testObject.playRecursiveCombat()

        then:
        291 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFile("day22/input.txt")
        def testObject = new CardGame(inputData)

        when:
        def response = testObject.playRecursiveCombat()

        then:
        31835 == response


    }
}
