package lt.urbontaitis.adventofcode.day21

import lt.urbontaitis.adventofcode.common.FileReader
import spock.lang.Specification

class AllergenAssessmentSpec extends Specification {

    def "Ingredients count without allergens should be 5"() {
        given:
        def inputData = FileReader.readFileToList("day21/sample.txt")
        def testObject = new AllergenAssessment(inputData)

        when:
        def response = testObject.ingredientsCountWithoutAllergens()

        then:
        5 == response
    }

    def "Part 1"() {
        given:
        def inputData = FileReader.readFileToList("day21/input.txt")
        def testObject = new AllergenAssessment(inputData)

        when:
        def response = testObject.ingredientsCountWithoutAllergens()

        then:
        2280 == response
    }

    def "Should return dangerous ingredients"() {
        given:
        def inputData = FileReader.readFileToList("day21/sample.txt")
        def testObject = new AllergenAssessment(inputData)

        when:
        def response = testObject.dangerousIngredients()

        then:
        "mxmxvkd,sqjhc,fvjkl" == response
    }

    def "Part 2"() {
        given:
        def inputData = FileReader.readFileToList("day21/input.txt")
        def testObject = new AllergenAssessment(inputData)

        when:
        def response = testObject.dangerousIngredients()

        then:
        "vfvvnm,bvgm,rdksxt,xknb,hxntcz,bktzrz,srzqtccv,gbtmdb" == response


    }
}
