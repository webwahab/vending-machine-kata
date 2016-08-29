package tdd.vendingMachine.products;

/**
 * Created by wprzecho on 29.08.16.
 */
public class Shelf {
    private int amountOfProducts;
    private ProductType productType;

    public Shelf(int amountOfProducts, ProductType productType) {
        this.amountOfProducts = amountOfProducts;
        this.productType = productType;
    }

    public static Shelf of(int amountOfProducts, ProductType productType) {
        return new Shelf(amountOfProducts, productType);
    }

    public ProductType getProductType() {
        return productType;
    }
}
