package tdd.vendingMachine;

import tdd.vendingMachine.money.Bank;
import tdd.vendingMachine.money.Coin;
import tdd.vendingMachine.products.ProductType;
import tdd.vendingMachine.products.Shelf;
import tdd.vendingMachine.utils.DisplayMessages;

import java.util.HashMap;
import java.util.Map;

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
        if (shelves.containsKey(shelveNumber)) {
            ProductType productType = shelves.get(shelveNumber).getProductType();
            currentTransaction = new Transaction(shelveNumber, productType);
            hardwareInterface.displayMsg(productType.getPrice().toString());
        } else {
            hardwareInterface.displayWarning(DisplayMessages.SHELF_NOT_FOUND);
        }
    }

    public void insertCoin(Coin coin) {
    }

    public void cancel() {

    }


}
