import spock.lang.Specification
import tdd.vendingMachine.HardwareInterface

class VendingMachineTest extends Specification {

    HardwareInterface hardwareInterface

    def "initial test"() {
        given:
        expect:


        where:
        a | b || c
        3 | 7 || 3
        5 | 4 || 5
        9 | 9 || 9
    }
}
