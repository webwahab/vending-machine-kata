package tdd.vendingMachine;

public interface HardwareInterface {
    void displayMsg(String message);

    void displayWarning(String message);

    void returnTheMoney();

    void returnProduct(int shelfNumber);

}
