package lt.urbontaitis.adventofcode.day2

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class PasswordValidatorSpec extends Specification {

    PasswordValidator testObject

    def "Two passwords should be valid by first policy"() {
        given:
        def inputData = ["1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"]
        testObject = new PasswordValidator(inputData)

        when:
        def response = testObject.validPasswordCountByFirstPolicy()

        then:
        response == 2
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day2/input.txt")
        testObject = new PasswordValidator(inputData)

        when:
        def response = testObject.validPasswordCountByFirstPolicy()

        then:
        response == 469
    }

    def "One password is valid by The Official Toboggan Corporate policy"() {
        given:
        def inputData = ["1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"]
        testObject = new PasswordValidator(inputData)

        when:
        def response = testObject.validPasswordCountByTheOfficialTobogganCorporatePolicy()

        then:
        response == 1
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day2/input.txt")
        testObject = new PasswordValidator(inputData)

        when:
        def response = testObject.validPasswordCountByTheOfficialTobogganCorporatePolicy()

        then:
        response == 267
    }
}
