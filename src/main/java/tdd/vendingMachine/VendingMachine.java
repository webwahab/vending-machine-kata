package tdd.vendingMachine;

import tdd.vendingMachine.money.Bank;
import tdd.vendingMachine.money.Coin;
import tdd.vendingMachine.products.ProductType;
import tdd.vendingMachine.products.Shelf;
import tdd.vendingMachine.transaction.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static tdd.vendingMachine.utils.DisplayMessages.*;

public class VendingMachine {

    private HardwareInterface hardwareInterface;
    private Bank bank;
    private Transaction currentTransaction;
    private Map<Integer, Shelf> shelves = new HashMap<>();


    public void initialize(HardwareInterface hardwareInterface, Map<Integer, Shelf> products, Map<Coin, Integer> coins) {
        this.hardwareInterface = hardwareInterface;
        bank = new Bank();
        bank.initialize(coins);
        shelves.putAll(products);
    }

    public void chooseShelve(int shelveNumber) {
        if (!shelves.containsKey(shelveNumber)) {
            hardwareInterface.displayWarning(SHELF_NOT_FOUND);
        } else if (!shelves.get(shelveNumber).hasProducts()) {
            hardwareInterface.displayWarning(PRODUCT_OUT_OF_STOCK);
        } else {
            ProductType productType = shelves.get(shelveNumber).getProductType();
            currentTransaction = Transaction.of(shelveNumber, productType);
            hardwareInterface.displayMsg(productCosts(productType.getPrice()));
        }
    }

    public void insertCoin(Coin coin) {
        bank.insertCoin(coin);
        currentTransaction.insertCoin(coin);
        BigDecimal missingMoney = currentTransaction.getMissingMoney();
        if (BigDecimal.ZERO.compareTo(missingMoney) >= 0) {
            finishTransaction(missingMoney.negate());
        } else {
            hardwareInterface.displayMsg(toPay(missingMoney));
        }
    }

    private void finishTransaction(BigDecimal missingMoney) {
        Optional<List<Coin>> coinsToReturn = bank.withdraw(missingMoney);
        if (coinsToReturn.isPresent()) {
            hardwareInterface.returnTheMoney(coinsToReturn.get());
            hardwareInterface.returnProduct(currentTransaction.getChoosenShelf());
            currentTransaction.commit();
        } else {
            hardwareInterface.displayWarning(INSUFFICIENT_MONEY_TO_RETURN_CHANGE);
            hardwareInterface.returnTheMoney(bank.withdraw(currentTransaction.getCurrentMoney()).get());
            currentTransaction.rollback();
        }
    }

    public void cancel() {

    }


}
