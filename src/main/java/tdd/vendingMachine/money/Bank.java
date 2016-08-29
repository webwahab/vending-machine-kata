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
        if (money.containsKey(coin)) {
            Math.incrementExact(money.get(coin));
        } else {
            money.put(coin, 1);
        }
    }

    public boolean withdraw(BigDecimal moneyToReturn) {
        Map<Coin, Integer> transactionUpdate = new EnumMap<>(Coin.class);
        for (Coin coin : money.keySet()) {
            int coinToReturn = moneyToReturn.divide(coin.getValue()).intValue();
            int currentMoneyState = money.get(coin);
            if (coinToReturn <= currentMoneyState) {
                transactionUpdate.put(coin, currentMoneyState - coinToReturn);
                moneyToReturn = moneyToReturn.subtract(coin.getValue().multiply(BigDecimal.valueOf(coinToReturn)));
            } else {
                transactionUpdate.put(coin, 0);
                moneyToReturn = moneyToReturn.subtract(coin.getValue().multiply(BigDecimal.valueOf(currentMoneyState)));
            }
        }
        if (BigDecimal.ZERO.compareTo(moneyToReturn) == 0) {
            return acceptTransaction(transactionUpdate);
        } else {
            return false;
        }
    }

    private boolean acceptTransaction(Map<Coin, Integer> transactionUpdate) {
        money.putAll(transactionUpdate);
        return true;
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
