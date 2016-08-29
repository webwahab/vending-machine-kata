package tdd.vendingMachine.money;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by wprzecho on 22.08.16.
 */
public class Bank {
    private final Map<Coin, Integer> money;

    public Bank() {
        money = new EnumMap<>(Coin.class);
    }

    public void initialize(Map<Coin, Integer> coins) {
        money.putAll(coins);
    }

    public void insertCoin(Coin coin) {
        money.put(coin, Math.incrementExact(money.get(coin) != null ? money.get(coin) : 0));
    }

    public void withdraw(double moneyToReturn) {

    }

    public BigDecimal balance() {
        return money.keySet()
            .stream()
            .map(this::getPurseValue)
            .reduce(BigDecimal::add)
            .get();
    }

    private BigDecimal getPurseValue(Coin coin) {
        return coin.getValue().multiply(BigDecimal.valueOf(money.get(coin)));
    }
}
