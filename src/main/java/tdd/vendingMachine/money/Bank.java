package tdd.vendingMachine.money;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wprzecho on 22.08.16.
 */
public class Bank {
    private static final Map<Coin, Integer> money;

    static {
        money = new HashMap<>();
        money.put(Coin.COIN_0_1, 0);
        money.put(Coin.COIN_0_2, 0);
        money.put(Coin.COIN_0_5, 0);
        money.put(Coin.COIN_1, 0);
        money.put(Coin.COIN_2, 0);
        money.put(Coin.COIN_5, 0);
    }

    public static void initialize(Map<Coin, Integer> coins) {
        money.putAll(coins);
    }

    public static void insertCoin(Coin coin) {
        if (money.containsKey(coin)) {
            money.put(coin, Math.incrementExact(money.get(coin)));
        } else {
            throw new RuntimeException("Unknown type of coin.");
        }
    }

    public static void withdraw(double moneyToReturn) {

    }

    public static BigDecimal balance() {
        return money.keySet()
            .stream()
            .map(coin -> coin.getValue().multiply(BigDecimal.valueOf(money.get(coin))))
            .reduce(BigDecimal::add)
            .get();
    }
}
