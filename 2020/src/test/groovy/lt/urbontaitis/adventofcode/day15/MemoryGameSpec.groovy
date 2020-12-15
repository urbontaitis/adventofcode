package lt.urbontaitis.adventofcode.day15


import spock.lang.Specification
import spock.lang.Unroll

class MemoryGameSpec extends Specification {

    @Unroll
    def "Given #startingNumbers the 2020th number spoken is #numberSpoken"() {
        given:
        def theNumberSpoken = 2020
        def testObject = new MemoryGame(startingNumbers)

        when:
        def response = testObject.play(theNumberSpoken)

        then:
        numberSpoken == response

        where:
        startingNumbers | numberSpoken
        [0, 3, 6]       | 436
        [1, 3, 2]       | 1
        [2, 1, 3]       | 10
        [1, 2, 3]       | 27
        [2, 3, 1]       | 78
        [3, 2, 1]       | 438
        [3, 1, 2]       | 1836
    }

    def "Part 1"() {
        given:
        def theNumberSpoken = 2020
        def inputData = [2, 1, 10, 11, 0, 6]
        def testObject = new MemoryGame(inputData)

        when:
        def response = testObject.play(theNumberSpoken)

        then:
        232 == response
    }

    @Unroll
    def "Given #startingNumbers the 30000000th number spoken is #numberSpoken"() {
        given:
        def theNumberSpoken = 30000000
        def testObject = new MemoryGame(startingNumbers)

        when:
        def response = testObject.play(theNumberSpoken)

        then:
        numberSpoken == response

        where:
        startingNumbers | numberSpoken
        [0, 3, 6]       | 175594
        [1, 3, 2]       | 2578
        [2, 1, 3]       | 3544142
        [1, 2, 3]       | 261214
        [2, 3, 1]       | 6895259
        [3, 2, 1]       | 18
        [3, 1, 2]       | 362
    }

    def "Part 2"() {
        given:
        def theNumberSpoken = 30000000
        def inputData = [2, 1, 10, 11, 0, 6]
        def testObject = new MemoryGame(inputData)

        when:
        def response = testObject.play(theNumberSpoken)

        then:
        18929178 == response
    }
}
