package lt.urbontaitis.adventofcode.day1

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification;

class ExpenseReportCalculatorSpec extends Specification {

    ExpenseReportCalculator testObject = new ExpenseReportCalculator()

    def "Find two numbers which sum is 2020"() {
        given:
        def inputData = ["1721", "979", "366", "299", "675", "1456"]

        when:
        def result = testObject.calculateTwoNumbers(inputData)

        then:
        result == 514579
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day1/input.txt")

        when:
        def result = testObject.calculateTwoNumbers(inputData)

        then:
        result == 713184
    }

    def "Find three numbers which sum is 2020"() {
        given:
        def inputData = ["1721", "979", "366", "299", "675", "1456"]

        when:
        def result = testObject.calculateThreeNumbers(inputData)

        then:
        result == 241861950
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day1/input.txt")

        when:
        def result = testObject.calculateThreeNumbers(inputData)

        then:
        result == 261244452
    }
}
