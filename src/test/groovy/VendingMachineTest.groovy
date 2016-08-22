import spock.lang.Specification

class VendingMachineTest extends Specification {
    def "maximum of two numbers"() {
        given:
        expect:
        Math.min(a, b) == c

        where:
        a | b || c
        3 | 7 || 3
        5 | 4 || 5
        9 | 9 || 9
    }
}
