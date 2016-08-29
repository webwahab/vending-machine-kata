package tdd.vendingMachine;

import java.util.Map;

public class VendingMachine {

    private HardwareInterface hardwareInterface;

    public void initialize(HardwareInterface hardwareInterface, Map<Integer, Map<String, Integer>> products) {
        this.hardwareInterface = hardwareInterface;
    }



}
