package lt.urbontaitis.adventofcode.day9

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class XmasDecryptorSpec extends Specification {

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day9/sample.txt")
        def testObject = new XmasDecryptor(inputData)
        def preambleLength = 5

        when:
        def response = testObject.findFirstWhichIsNotTheSumOfTwo(preambleLength)

        then:
        127 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day9/input.txt")
        def testObject = new XmasDecryptor(inputData)
        def preambleLength = 25

        when:
        def response = testObject.findFirstWhichIsNotTheSumOfTwo(preambleLength)

        then:
        1212510616 == response
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFileToList("day9/sample.txt")
        def testObject = new XmasDecryptor(inputData)
        def preambleLength = 5

        when:
        def response = testObject.findTheEncryptionWeakness(preambleLength)

        then:
        62 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day9/input.txt")
        def testObject = new XmasDecryptor(inputData)
        def preambleLength = 25

        when:
        def response = testObject.findTheEncryptionWeakness(preambleLength)

        then:
        171265123 == response
    }
}
