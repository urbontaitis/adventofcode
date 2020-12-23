package lt.urbontaitis.adventofcode.day23


import spock.lang.Specification

class CrabCupsSimulationSpec extends Specification {

    def "Sample 1"() {
        given:
        def testObject = new CrabCupsSimulation("389125467")

        when:
        def response = testObject.runSimulationOneHundredTimes()

        then:
        67384529 == response
    }

    def "Part 1"() {
        given:
        def testObject = new CrabCupsSimulation("739862541")

        when:
        def response = testObject.runSimulationOneHundredTimes()

        then:
        94238657 == response
    }

    def "Sample 2"() {
        given:
        def testObject = new CrabCupsSimulation("389125467")

        when:
        def response = testObject.runSimulationTenMillionsTime()

        then:
        149245887792 == response
    }

    def "Part 2"() {
        given:
        def testObject = new CrabCupsSimulation("739862541")

        when:
        def response = testObject.runSimulationTenMillionsTime()

        then:
        3072905352 == response


    }
}
