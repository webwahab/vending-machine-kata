import spock.lang.Specification
import tdd.vendingMachine.money.Bank
import tdd.vendingMachine.money.Coin

/**
 * Created by wprzecho on 22.08.16.
 */
class BankTest extends Specification {

    Bank bank

    def "insert money"() {
        given:
        bank = new Bank(new HashMap<Coin, Integer>())
        when:
        bank.insertCoin(Coin.COIN_0_1)
        bank.insertCoin(Coin.COIN_0_2)
        then:
        bank.balance() == 0.3
    }

    def "insert coins after bank initialization"() {
        given:
        Map<Coin, Integer> coins = [(Coin.COIN_0_5): 2, (Coin.COIN_0_1): 3]
        bank = new Bank()
        when:
        bank.initialize(coins)
        then:
        bank.balance() == 1.3
    }

    def "withdraw money"() {
        given:
        Map<Coin, Integer> coins = [(Coin.COIN_0_5): 2, (Coin.COIN_0_1): 3]
        bank = new Bank()
        bank.initialize(coins)
        when:
        bank.withdraw(new BigDecimal("1.1"))
        then:
        bank.balance() == 0.2
    }

    def "not enough amount of money"() {
        given:
        Map<Coin, Integer> coins = [(Coin.COIN_0_5): 2, (Coin.COIN_0_1): 3]
        bank = new Bank()
        bank.initialize(coins)
        expect:
        bank.withdraw(new BigDecimal("1.5")).isPresent() == false
        bank.balance() == 1.3
    }
}
