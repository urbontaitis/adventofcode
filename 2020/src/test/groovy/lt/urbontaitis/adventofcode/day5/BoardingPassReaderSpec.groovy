package lt.urbontaitis.adventofcode.day5

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification
import spock.lang.Unroll

class BoardingPassReaderSpec extends Specification {

    @Unroll
    def "Transform #rawString to Boarding pass #expectedBoardingPass" () {
        when:
        def boardingPass = BoardingPassMapper.transform(rawString)

        then:
        expectedBoardingPass == boardingPass

        where:
        rawString | expectedBoardingPass
        "BFFFBBFRRR" | new BoardingPass(70, 7, 567)
        "FFFBBBFRRR" | new BoardingPass(14, 7, 119)
        "BBFFBBFRLL" | new BoardingPass(102, 4, 820)
    }

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day5/sample.txt")
        def testObject = new BoardingPassReader(inputData)

        when:
        def response = testObject.findHighestSeatId()

        then:
        820 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day5/input.txt")
        def testObject = new BoardingPassReader(inputData)

        when:
        def response = testObject.findHighestSeatId()

        then:
        866 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day5/input.txt")
        def testObject = new BoardingPassReader(inputData)

        when:
        def response = testObject.findFreeSeatId()

        then:
        583 == response
    }

}
