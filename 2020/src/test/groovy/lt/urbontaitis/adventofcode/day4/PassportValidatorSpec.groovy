package lt.urbontaitis.adventofcode.day4

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class PassportValidatorSpec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFile("day4/sample.txt")
        PassportValidator testObject = new PassportValidator(inputData)

        when:
        def response = testObject.validPassportCount()

        then:
        response == 2
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFile("day4/input.txt")
        PassportValidator testObject = new PassportValidator(inputData)

        when:
        def response = testObject.validPassportCount()

        then:
        response == 219
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFile("day4/sample-2.txt")
        PassportValidator testObject = new PassportValidator(inputData)

        when:
        def response = testObject.validPassportContentCount()

        then:
        response == 4
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFile("day4/input.txt")
        PassportValidator testObject = new PassportValidator(inputData)

        when:
        def response = testObject.validPassportContentCount()

        then:
        response == 127
    }

}
