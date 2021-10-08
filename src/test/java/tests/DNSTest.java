package tests;

import baseTestClass.BaseTest;
import org.junit.jupiter.api.Test;
import products.Guarantee;
import products.Products;

public class DNSTest extends BaseTest {

    @Test
    public void startTest() {
        app.getHomePage()
                .searchProductInSearchLine(Products.SEACH_HAIER)
                .searchProductOnSearchingResultPage(Products.CHOOSE_HAIER)
                .addProductGuaranty(Guarantee.BASE_12_PLUS_24)
                .addToShoppingBasket()
                .searchProductInSearchLine(Products.SEACH_SAMSUNG)
                .searchProductOnSearchingResultPage(Products.CHOOSE_SAMSUNG)
                .addToShoppingBasket()
                .goToShoppingBasket()
                .delete(Products.CHOOSE_SAMSUNG.getTitle())
                .add(Products.CHOOSE_HAIER)
                .add(Products.CHOOSE_HAIER)
                .addBack();

    }
}
