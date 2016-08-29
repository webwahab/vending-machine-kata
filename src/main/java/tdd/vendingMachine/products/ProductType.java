package tdd.vendingMachine.products;

import java.math.BigDecimal;

/**
 * Created by wprzecho on 29.08.16.
 */
public enum ProductType {
    COCA_COLA_0_25("Coca cola 0,25l", new BigDecimal("1.5")),
    TYMBARK_0_5("Tymbark 0,5l", new BigDecimal("2.3")),
    MINERAL_WATER_0_33("Mineral water 0,33l", new BigDecimal("5"));

    private String name;
    private BigDecimal price;

    ProductType(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
