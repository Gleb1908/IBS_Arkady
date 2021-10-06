package tests;

import baseTestClass.BaseTest;
import org.junit.jupiter.api.Test;
import products.Guarantee;
import products.Products;

public class DNSTest extends BaseTest {

    @Test
    public void startTest2() throws InterruptedException {
        app.getHomePage()
                .searchProductInSearchLine(Products.SEACH_HAIER.getTitle())
                .searchProductOnSearchingResultPage(Products.CHOOSE_HAIER.getTitle())
                .addProductGuaranty(Guarantee.BASE_12_PLUS_24.getTitle())
                .addToShoppingBasket()
                .searchProductInSearchLine(Products.SEACH_SAMSUNG.getTitle())
                .searchProductOnSearchingResultPage(Products.CHOOSE_SAMSUNG.getTitle())
                .addToShoppingBasket()
                .goToShoppingBasket()
                .delete(Products.CHOOSE_SAMSUNG.getTitle())
                .add(Products.CHOOSE_HAIER.getTitle())
                .add(Products.CHOOSE_HAIER.getTitle())
                .addBack();

    }
}
