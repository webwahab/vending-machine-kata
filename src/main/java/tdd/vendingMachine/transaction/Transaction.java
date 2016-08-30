package tdd.vendingMachine.transaction;

import tdd.vendingMachine.money.Coin;
import tdd.vendingMachine.products.ProductType;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by wprzecho on 29.08.16.
 */
public class Transaction {
    private static Transaction instance = null;
    private int shelveNumber;
    private ProductType productType;
    private BigDecimal currentMoney;


    public static Transaction of(int shelveNumber, ProductType productType) {
        if (instance == null) {
            instance = new Transaction();
            instance.updateTransaction(shelveNumber, productType);
            instance.currentMoney = new BigDecimal(BigInteger.ZERO);
        } else {
            instance.updateTransaction(shelveNumber, productType);
        }
        return instance;
    }

    public void updateTransaction(int shelveNumber, ProductType productType) {
        instance.shelveNumber = shelveNumber;
        instance.productType = productType;
    }

    public BigDecimal insertCoin(Coin coin) {
        currentMoney = currentMoney.add(coin.getValue());
        return productType.getPrice().subtract(currentMoney);
    }

    public BigDecimal getMissingMoney() {
        return productType.getPrice().subtract(currentMoney);
    }

    public int getChoosenShelf() {
        return shelveNumber;
    }

    public void commit() {
        currentMoney = BigDecimal.ZERO;
    }

    public void rollback() {
        currentMoney = BigDecimal.ZERO;
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }
}
