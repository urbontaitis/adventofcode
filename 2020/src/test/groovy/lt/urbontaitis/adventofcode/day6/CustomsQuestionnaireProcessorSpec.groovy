package lt.urbontaitis.adventofcode.day6

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class CustomsQuestionnaireProcessorSpec extends Specification {

    def "When duplicate answers are given then only unique are counted"() {
        given:
        def inputData = FileReader.readFile("day6/simple-sample.txt")
        def testObject = new CustomsQuestionnaireProcessor(inputData)

        when:
        def response = testObject.sumOfAllYesAnswers()

        then:
        6 == response
    }

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFile("day6/sample.txt")
        def testObject = new CustomsQuestionnaireProcessor(inputData)

        when:
        def response = testObject.sumOfAllYesAnswers()

        then:
        11 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFile("day6/input.txt")
        def testObject = new CustomsQuestionnaireProcessor(inputData)

        when:
        def response = testObject.sumOfAllYesAnswers()

        then:
        6310 == response
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFile("day6/sample.txt")
        def testObject = new CustomsQuestionnaireProcessor(inputData)

        when:
        def response = testObject.sumOfGroupYesAnswers()

        then:
        6 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFile("day6/input.txt")
        def testObject = new CustomsQuestionnaireProcessor(inputData)

        when:
        def response = testObject.sumOfGroupYesAnswers()

        then:
        3193 == response


    }
}
