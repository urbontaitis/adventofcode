package io.github.urbontaitis.adventofcode.day6

import spock.lang.Ignore
import spock.lang.Specification

class UniversalOrbitMapSpec extends Specification {

    UniversalOrbitMap testObject

    def setup() {
        testObject = new UniversalOrbitMap();
    }

    @Ignore
    def "build tree from the list #list"() {
        when:
        def orbitMap = testObject.build(list)

        then:
        orbitMap == []

        where:
        list                                                                            | _
        ['COM)B', 'B)C', 'C)D', 'D)E', 'E)F', 'B)G', 'G)H', 'D)I', 'E)J', 'J)K', 'K)L'] | _
    }
}
