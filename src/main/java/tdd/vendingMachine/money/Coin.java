package tdd.vendingMachine.money;

import java.math.BigDecimal;

/**
 * Created by wprzecho on 22.08.16.
 */
public enum Coin {
    COIN_0_1(new BigDecimal("0.1")),
    COIN_0_2(new BigDecimal("0.2")),
    COIN_0_5(new BigDecimal("0.5")),
    COIN_1(new BigDecimal("1")),
    COIN_2(new BigDecimal("2")),
    COIN_5(new BigDecimal("5"));

    private BigDecimal value;

    Coin(final BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
