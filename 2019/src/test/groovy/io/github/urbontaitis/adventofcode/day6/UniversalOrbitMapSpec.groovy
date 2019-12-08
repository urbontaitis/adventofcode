package io.github.urbontaitis.adventofcode.day6

import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import spock.lang.Specification
import spock.lang.Unroll

class UniversalOrbitMapSpec extends Specification {

    def "build graph from the list #list"() {
        given:
        UniversalOrbitMap testObject = new UniversalOrbitMap(list)

        when:
        DefaultDirectedGraph<String, DefaultEdge> orbitMap = testObject.generateMap()

        then:
        orbitMap == []

        where:
        list                                                                            | _
        ['COM)B', 'B)C', 'C)D', 'D)E', 'E)F', 'B)G', 'G)H', 'D)I', 'E)J', 'J)K', 'K)L'] | _
    }

    def "Total number of direct and indirect orbits"() {
        given:
        UniversalOrbitMap testObject = new UniversalOrbitMap(list)

        when:
        int total = testObject.totalNumberOfDirectAndIndirectOrbits()

        then:
        total == expectedTotal

        where:
        list                                                                            | expectedTotal
        ['COM)B', 'B)C', 'C)D', 'D)E', 'E)F', 'B)G', 'G)H', 'D)I', 'E)J', 'J)K', 'K)L'] | 42
    }

    @Unroll
    def "Total number of direct and indirect #orbit should be #number"() {
        given:
        def list = ['COM)B', 'B)C', 'C)D', 'D)E', 'E)F', 'B)G', 'G)H', 'D)I', 'E)J', 'J)K', 'K)L']
        UniversalOrbitMap testObject = new UniversalOrbitMap(list)

        when:
        int total = testObject.countNumberOfDirectAndIndirectOrbitsBy(orbit)

        then:
        total == number

        where:
        orbit | number
        "D"   | 3
        "L"   | 7
        "COM" | 0
    }


}
