package tdd.vendingMachine;

import tdd.vendingMachine.money.Coin;
import tdd.vendingMachine.products.ProductType;

import java.math.BigDecimal;

/**
 * Created by wprzecho on 29.08.16.
 */
public class Transaction {
    private int shelveNumber;
    private ProductType productType;
    private BigDecimal currentMoney;

    public Transaction(int shelveNumber, ProductType productType) {
        this.shelveNumber = shelveNumber;
        this.productType = productType;
        currentMoney = new BigDecimal("0");
    }

    public BigDecimal insertCoin(Coin coin) {
        currentMoney = currentMoney.add(coin.getValue());
        return productType.getPrice().subtract(currentMoney);
    }

}
