package tdd.vendingMachine;

import tdd.vendingMachine.money.Coin;

import java.util.List;

public interface HardwareInterface {
    void displayMsg(String message);

    void displayWarning(String message);

    void returnTheMoney(List<Coin> coins);

    void returnProduct(int shelfNumber);

}
