package io.github.urbontaitis.adventofcode.day4

import spock.lang.Specification

class SecureContainerSpec extends Specification {

    def "Verify is #password is valid or not"() {
        expect:
        new SecureContainer().isValid(password) == isValid

        where:
        password  | isValid
        "111123"  | true
        "111111"  | true
        "111122"  | true
        "123455"  | true
        "223450"  | false
        "123789"  | false
        "12"      | false
        "1234567" | false
        "123444"  | false
        "111223"  | false

    }

    def "should generate different valid passwords in the given range"() {
        given:
        int from = 145852
        int to = 616942

        when:
        List<String> passwords = new SecureContainer().generate(from, to)

        then:
        passwords.size() == 1767

    }

}
