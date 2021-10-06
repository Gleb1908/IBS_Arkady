package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Реализация домашней страницы
 */
public class HomePage extends BasePage{

    @FindBy(xpath = "//div[contains(@class,'header-menu-wrapper')]//input")
    private WebElement searchLine;

    @FindBy(xpath = "//span[@class='cart-link__lbl']")
    private WebElement shoppingBasketButton;

    /**
     * Поиск товара в строке поиска
     * @param name - имя товара
     * @return ResultsPage
     */
    public SearchingResultsPage searchProductInSearchLine(String name) {
        Assertions.assertTrue(waitUtilElementToBeClickable(searchLine), "Поисковая строка не кликабельна");
        searchLine.click();
        searchLine.clear();
        searchLine.sendKeys(name);
        Assertions.assertEquals(searchLine.getAttribute("value"),name,"Введенный текст не совпадает");
        searchLine.sendKeys(Keys.ENTER);
        return pageManager.getSearchingResultsPage();
    }


    /**
     * Переход в корзину с товарами
     */
    public ShoppingBasketPage goToShoppingBasket() {
        Assertions.assertTrue(waitUtilElementToBeClickable(shoppingBasketButton), "Кнопка корзины не кликабельна");
        shoppingBasketButton.click();
        pageManager.getShoppingBasketPage()
                .checkSum();
        return pageManager.getShoppingBasketPage();
    }

}
