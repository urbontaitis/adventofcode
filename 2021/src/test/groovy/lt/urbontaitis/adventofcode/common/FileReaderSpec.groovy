package lt.urbontaitis.adventofcode.common


import spock.lang.Specification

class FileReaderSpec extends Specification {

    def "FileReader reads file content as String" () {
        when:
        def resource = FileReader.readFile("dummy.txt")

        then:
        resource == """1
2
3
4"""
    }

    def "FileReader reads file content as List<String>" () {
        when:
        def resource = FileReader.readFileToList("dummy.txt")

        then:
        resource == ["1", "2", "3", "4"]
    }

}
