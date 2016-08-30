import spock.lang.Specification
import tdd.vendingMachine.HardwareInterface
import tdd.vendingMachine.VendingMachine
import tdd.vendingMachine.money.Coin
import tdd.vendingMachine.products.Shelf

import static tdd.vendingMachine.products.ProductType.*
import static tdd.vendingMachine.utils.DisplayMessages.*

class VendingMachineTest extends Specification {

    HardwareInterface hardwareInterface
    VendingMachine vendingMachine

    def "setup"() {
        hardwareInterface = Mock(HardwareInterface);
        vendingMachine = new VendingMachine()
        Map<Integer, Shelf> shelves = [1: Shelf.of(5, COCA_COLA_0_25), 2: Shelf.of(2, MINERAL_WATER_0_33), 3: Shelf.of(0, TYMBARK_0_5)]
        Map<Coin, Integer> coins = [(Coin.COIN_0_1): 3, (Coin.COIN_0_2): 3, (Coin.COIN_0_5): 3, (Coin.COIN_1): 3]
        vendingMachine.initialize(hardwareInterface, shelves, coins)
    }

    def "choosen shelf not found"() {
        when:
        vendingMachine.chooseShelve(4)
        then:
        1 * hardwareInterface.displayWarning(SHELF_NOT_FOUND)
    }

    def "choosen shelf with no products"() {
        when:
        vendingMachine.chooseShelve(3)
        then:
        1 * hardwareInterface.displayWarning(PRODUCT_OUT_OF_STOCK)
    }


    def "choose shelf"() {
        when:
        vendingMachine.chooseShelve(1)
        then:
        1 * hardwareInterface.displayMsg(productCosts(COCA_COLA_0_25.getPrice()))
    }

    def "buy product"() {
        when:
        vendingMachine.chooseShelve(1)
        vendingMachine.insertCoin(Coin.COIN_0_5)
        vendingMachine.insertCoin(Coin.COIN_1)
        then:
        1 * hardwareInterface.displayMsg(toPay(new BigDecimal("1.0")))
        1 * hardwareInterface.returnProduct(1)
    }

    def "buy product without change"() {
        when:
        vendingMachine.chooseShelve(1)
        vendingMachine.insertCoin(Coin.COIN_0_5)
        vendingMachine.insertCoin(Coin.COIN_1)
        then:
        1 * hardwareInterface.returnTheMoney(new LinkedList<Coin>())
    }

    def "buy product with change"() {
        given:
        List<Coin> coinsToReturn = [Coin.COIN_0_1]
        when:
        vendingMachine.chooseShelve(1)
        vendingMachine.insertCoin(Coin.COIN_0_5)
        vendingMachine.insertCoin(Coin.COIN_0_1)
        vendingMachine.insertCoin(Coin.COIN_1)
        then:
        1 * hardwareInterface.returnTheMoney(coinsToReturn)
    }

    def "try to buy product when machine has no money to return change"() {
        given:
        List<Coin> coinsToReturn = [Coin.COIN_5]
        when:
        vendingMachine.chooseShelve(1)
        vendingMachine.insertCoin(Coin.COIN_0_5)
        vendingMachine.insertCoin(Coin.COIN_0_1)
        vendingMachine.insertCoin(Coin.COIN_5)
        vendingMachine.chooseShelve(1)
        vendingMachine.insertCoin(Coin.COIN_5)
        then:
        1 * hardwareInterface.displayWarning(INSUFFICIENT_MONEY_TO_RETURN_CHANGE)
        1 * hardwareInterface.returnTheMoney(coinsToReturn)
    }

    def "press cancel button"() {

    }
}
