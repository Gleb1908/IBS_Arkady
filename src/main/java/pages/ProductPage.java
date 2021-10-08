package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import products.Guarantee;
import products.Product;
import products.Products;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static utils.PropConst.IMPLICITLY_WAIT;

/**
 * Реализация страницы продукта
 */
public class ProductPage extends BasePage {
    @FindBy(xpath = "//h1")
    private WebElement productName;
    @FindBy(xpath = "//div[@class='product-card-top__buy']//div[@class='product-buy__price']")
    private WebElement productPrice;
    @FindBy(xpath = "//div[@class='additional-sales-tabs__titles-wrap']/div[contains(text(),'Гарантия')]")
    private WebElement productGuaranty;
    @FindBy(xpath = "//div[@class='product-card-top__code']")
    private WebElement productVendorCode;
    @FindBy(xpath = "//span[@class='product-warranty__period']")
    private List<WebElement> listGuaranty;
    @FindBy(xpath = "//div[@class='product-card-top__buy']//button[contains(@class,'buy-btn')]")
    private WebElement buyButton;

    /**
     * Сохранение информации о товаре
     * @return ProductPage
     */
    public ProductPage saveProductInformation(String searchedName) {
        Assertions.assertTrue(waitUtilElementToBeVisible(productPrice),"Цена товара не загрузилась");
        double productPrice = Double.parseDouble(this.productPrice.getText().split("₽")[0].replaceAll("\\s+", ""));
        boolean productGuaranty;
        try {
            driverManager.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            this.productGuaranty.isDisplayed();
            productGuaranty = true;
        } catch (NoSuchElementException ex) {
            productGuaranty = false;
        } finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        }

        products.add(new Product(searchedName, productPrice, productGuaranty, products.size()));
        return this;
    }

    /**
     * Добавление гарантии
     * @param guar - сколько месяцев гарантии
     * @return ProductPage
     */
    public ProductPage addProductGuaranty(Guarantee guar) {

        Assertions.assertTrue(waitUtilElementToBeClickable(productGuaranty), "Поле гарантии не кликабельно");
        productGuaranty.click();
        for (WebElement element : listGuaranty) {
            Assertions.assertTrue(waitUtilElementToBeVisible(element), "Элемент не загрузился");
            if (element.findElement(By.xpath("//span[contains(@class,'warranty__period') and text()='" + guar.getTitle() + "']")) != null) {
                try {
                    element.findElement(By.xpath("//span[contains(@class,'warranty__period') and text()='" + guar.getTitle() + "']")).click();
                    return pageManager.getProductPage();
                } catch (NoSuchElementException ignored) {
                }
            }
        }
        Assertions.fail("Товар для покупки не найден");
        return null;
    }

    /**
     * Добавление товара в корзину
     * @return HomePage
     */
    public HomePage addToShoppingBasket() {
        Assertions.assertTrue(waitUtilElementToBeClickable(buyButton), "Кнопка добавить в корзину не кликабельна");
        buyButton.click();
        // проверка на работу кнопки добавления
        return pageManager.getHomePage();
    }

}