package io.github.urbontaitis.adventofcode.day4

import spock.lang.Specification
import spock.lang.Unroll

class SecureContainerSpec extends Specification {

    @Unroll
    def "Is #password password meet validation rules: #isValid" () {
        expect:
        new SecureContainer().isValid(password) == isValid

        where:
        password | isValid
        "111123"   | true
        "111111"   | true
        "123455"   | true
        "223450"   | false
        "123789"   | false
        "12"       | false
        "1234567"  | false
    }

    @Unroll
    def "Is #password password meet validation rules: #isValid - part2" () {
        expect:
        new SecureContainer().isValidPart2(password) == isValid

        where:
        password | isValid
        "112233"   | true
        "111122"   | true
        "145889"   | true
        "145899"   | true
        "123444"   | false
    }

    def "should generate different valid passwords in the given range" () {
        given:
        int from = 145852
        int to = 616942

        when:
        List<String> passwords = new SecureContainer().generate(from, to)

        then:
        passwords.size() == 1767
    }

    def "should generate different valid passwords in the given range - part2" () {
        given:
        int from = 145852
        int to = 616942

        when:
        List<String> passwords = new SecureContainer().generatePart2(from, to)

        then:
        passwords.size() == 1192
    }

}
