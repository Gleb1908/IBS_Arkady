package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import products.Products;

import java.util.List;

/**
 * Реализация страницы с результатами поиска
 */
public class SearchingResultsPage extends BasePage {

    @FindBy(xpath = "//div[@data-id = 'product']")
    private List<WebElement> listProducts;

    public ProductPage searchProductOnSearchingResultPage(Products product) {
        checkProductAvailable(product.getTitle());
        pageManager.getProductPage().saveProductInformation(product.getTitle());
        return pageManager.getProductPage();
    }

    /**
     * Проверка наличия определенного товара в результате поиска
     *
     * @return ProductPage
     */
    private ProductPage checkProductAvailable(String name) {
        for (WebElement element : listProducts) {
            Assertions.assertTrue(waitUtilElementToBeVisible(element),"Элемент не загрузился");
            if (element.findElement(By.xpath("//a[contains(@class,'product__name') and contains(span,'" + name + "')]")).getSize()!=null) {
                try {
                    element.findElement(By.xpath("//a[contains(@class,'product__name') and contains(span,'" + name + "')]")).click();
                    return pageManager.getProductPage();
                } catch (NoSuchElementException ignored) {
                }
            }
        }
        Assertions.fail("Товар для покупки не найден");
        return null;
    }

}