package tdd.vendingMachine.money;

import java.math.BigDecimal;

/**
 * Created by wprzecho on 22.08.16.
 */
public class Purse {
    private Coin coin;
    private int amount;

    public Purse(Coin coin, int amount) {
        this.coin = coin;
        this.amount = amount;
    }

    public BigDecimal getValue() {
        return coin.getValue();
    }

}
