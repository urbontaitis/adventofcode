package lt.urbontaitis.adventofcode.day24

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification
import spock.lang.Unroll

class LobbyLayoutSpec extends Specification {

    @Unroll
    def "Get direction by #directions"() {
        when:
        def actual = LobbyLayout.transform(directions)

        then:
        expectedTile == actual

        where:
        expectedTile           | directions
        new LobbyLayout.Direction(2, 0)   | "e"
        new LobbyLayout.Direction(-2, 0)  | "w"
        new LobbyLayout.Direction(1, 2)   | "ne"
        new LobbyLayout.Direction(-1, 2)  | "nw"
        new LobbyLayout.Direction(1, -2)  | "se"
        new LobbyLayout.Direction(-1, -2) | "sw"
        new LobbyLayout.Direction(-4, -4) | "sesenwnenenewseeswwswswwnenewsewsw"
    }

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day24/sample.txt")
        def testObject = new LobbyLayout(inputData)

        when:
        def response = testObject.countBlackTiles()

        then:
        10 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day24/input.txt")
        def testObject = new LobbyLayout(inputData)

        when:
        def response = testObject.countBlackTiles()

        then:
        469 == response
    }

    def "Sample 2"() {
        given:
        def inputData = FileReader.readFileToList("day24/sample.txt")
        def testObject = new LobbyLayout(inputData)

        when:
        def response = testObject.countBlackTilesAfter100Days()

        then:
        2208 == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day24/input.txt")
        def testObject = new LobbyLayout(inputData)

        when:
        def response = testObject.countBlackTilesAfter100Days()

        then:
        4353 == response
    }
}
