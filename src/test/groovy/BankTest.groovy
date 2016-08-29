import spock.lang.Specification
import tdd.vendingMachine.money.Bank
import tdd.vendingMachine.money.Coin

/**
 * Created by wprzecho on 22.08.16.
 */
class BankTest extends Specification {

    Bank bank

    def "simple bank test"() {
        given:
        bank = new Bank()
        when:
        bank.insertCoin(Coin.COIN_0_1)
        bank.insertCoin(Coin.COIN_0_2)
        then:
        bank.balance() == 0.3
    }
}
