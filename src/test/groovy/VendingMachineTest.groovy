import spock.lang.Specification
import tdd.vendingMachine.HardwareInterface
import tdd.vendingMachine.VendingMachine
import tdd.vendingMachine.money.Coin
import static tdd.vendingMachine.products.ProductType.*
import tdd.vendingMachine.products.Shelf
import tdd.vendingMachine.utils.DisplayMessages

class VendingMachineTest extends Specification {

    HardwareInterface hardwareInterface
    VendingMachine vendingMachine

    def "setup"() {
        hardwareInterface = Mock(HardwareInterface);
        vendingMachine = new VendingMachine()
        Map<Integer, Shelf> shelves = new HashMap<>();
        shelves.put(1, Shelf.of(5, COCA_COLA_0_25))
        shelves.put(2, Shelf.of(2, MINERAL_WATER_0_33))
        Map<Coin, Integer> coins = new EnumMap<>(Coin.class)
        coins.put(Coin.COIN_0_1, 3)
        coins.put(Coin.COIN_0_2, 3)
        coins.put(Coin.COIN_0_5, 3)
        coins.put(Coin.COIN_1, 3)
        vendingMachine.initialize(hardwareInterface, shelves, coins)
    }

    def "choosen shelf not found"() {
        when:
        vendingMachine.chooseShelve(3)
        then:
        1 * hardwareInterface.displayWarning(DisplayMessages.SHELF_NOT_FOUND)
    }


    def "choose shelf"() {
        when:
        vendingMachine.chooseShelve(1)
        then:
        1 * hardwareInterface.displayMsg(COCA_COLA_0_25.getPrice().toString())
    }
}
