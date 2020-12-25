package lt.urbontaitis.adventofcode.day25

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class ComboBreakerSpec extends Specification {

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day25/input.txt")
        def testObject = new ComboBreaker(inputData)

        when:
        def response = testObject.findEncryptionKey()

        then:
        4441893 == response
    }

}
