package lt.urbontaitis.adventofcode.day7

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification
import spock.lang.Unroll

class LuggageProcessorSpec extends Specification {

    @Unroll
    def "Rule #input should be transformed into #expected"() {

        when:
        def actual = BagMapper.transform(input)

        then:
        expected == actual

        where:
        input                                                             | expected
        "light red bags contain 1 bright white bag, 2 muted yellow bags." | new Bag("light red", ["bright white": 1, "muted yellow": 2])
        "bright white bags contain 1 shiny gold bag."                     | new Bag("bright white", ["shiny gold": 1])
        "dotted black bags contain no other bags."                        | new Bag("dotted black", [:])
    }

    def "Sample 1"() {
        given:
        def inputData = FileReader.readFileToList("day7/sample.txt")
        def testObject = new LuggageProcessor(inputData)
        def bagColor = "shiny gold"

        when:
        def response = testObject.countHowManyBagsCanHoldGivenBagColor(bagColor)

        then:
        4 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day7/input.txt")
        def testObject = new LuggageProcessor(inputData)
        def bagColor = "shiny gold"

        when:
        def response = testObject.countHowManyBagsCanHoldGivenBagColor(bagColor)

        then:
        238 == response
    }

    @Unroll
    def "Sample 2: by given #inputFile should be #otherBagsCount "() {
        given:
        def inputData = FileReader.readFileToList(inputFile)
        def testObject = new LuggageProcessor(inputData)
        def bagColor = "shiny gold"

        when:
        def response = testObject.howManyInnerBagsAreRequired(bagColor)

        then:
        otherBagsCount == response

        where:
        inputFile           | otherBagsCount
        "day7/sample.txt"   | 32
        "day7/sample-2.txt" | 126
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day7/input.txt")
        def testObject = new LuggageProcessor(inputData)
        def bagColor = "shiny gold"

        when:
        def response = testObject.howManyInnerBagsAreRequired(bagColor)

        then:
        82930 == response
    }
}
