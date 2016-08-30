package tdd.vendingMachine.utils;

import java.math.BigDecimal;

/**
 * Created by wprzecho on 29.08.16.
 */
public class DisplayMessages {
    public static final String SHELF_NOT_FOUND = "This shelf is not available. Please try again...";
    public static final String PRODUCT_OUT_OF_STOCK = "Product out of stock";
    public static final String INSUFFICIENT_MONEY_TO_RETURN_CHANGE = "Insufficient amount of money to return change.";

    public static String toPay(BigDecimal toPay) {
        return new String("To pay: " + toPay + " zł");
    }

    public static String productCosts(BigDecimal cost) {
        return new String("Product costs: " + cost + " zł");
    }
}
