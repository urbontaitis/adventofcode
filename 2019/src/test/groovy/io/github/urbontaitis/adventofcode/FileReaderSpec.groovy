package io.github.urbontaitis.adventofcode

import spock.lang.Specification

class FileReaderSpec extends Specification {

    def "FileReader finds resource" () {
        when:
        String resource = FileReader.readFile("day1/input.txt")

        then:
        resource != null
    }

}
