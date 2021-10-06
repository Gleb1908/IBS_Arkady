package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import products.Product;

import java.util.List;

/**
 * Реализация страницы корзины
 */
public class ShoppingBasketPage extends BasePage {

    @FindBy(xpath = "//div[@id='total-amount']//span[@class='price__current']")
    private WebElement shoppingBasketSumElement;
    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement upperShoppingBasketSumElement;
    @FindBy(xpath = "//div[@class='cart-items__product']//span[@class='price__current']")
    private List<WebElement> listPrices;
    @FindBy(xpath = "//div[@class='slider']//span[contains(@class,'checked')]")
    private List<WebElement> checkedGuarantyElements;
    @FindBy(xpath = "//a[@class='cart-items__product-name-link']")
    private List<WebElement> listProductsNames;
    @FindBy(xpath = "//div[@class='group-tabs']//span[@class='restore-last-removed']")
    private WebElement returnDeletedProduct;
    @FindBy(xpath = "//span[@class='cart-link__badge']")
    private WebElement numberInCart;

    public ShoppingBasketPage() {
    }

    /**
     * Проверка соответствия суммы в корзине
     *
     * @return CartPage
     */
    public ShoppingBasketPage checkSum() {
        double shoppingBasketSum = Double.parseDouble(shoppingBasketSumElement.getText().split("Р")[0].replaceAll("\\s+", ""));
        double upperShoppingBasketSum = Double.parseDouble(upperShoppingBasketSumElement.getText().replaceAll("[^0-9]", ""));
        double productsSum = 0;
        for (Product product : products)
            productsSum += product.getPrice() * product.getQuantity();
        Assertions.assertFalse(shoppingBasketSum != productsSum || upperShoppingBasketSum != productsSum, "Сумма в корзине не соответствует сумме покупок");
        return this;
    }

    /**
     * Удаление товара из корзины
     *
     * @param name - имя заданное в поиске
     * @return CartPage
     */
    public ShoppingBasketPage delete(String name) {
        for (Product product : products) {
            if (product.getSearchedName().equals(name)) {
                deletedProducts.add(product);
                for (WebElement element : listProductsNames) {
                    if (element.getText().contains(name)) {
                        WebElement deleteButton = element.findElement(By.xpath("//div[@class='cart-items__content-container']//a[contains(text(),'" + name + "')]/../../../../..//i[@class='count-buttons__icon-minus']"));
                        Assertions.assertTrue(waitUtilElementToBeClickable(deleteButton), "Кнопка удалить не активна");
                        deleteButton.click();
                        Assertions.assertFalse(waitUtilElementNotToBeVisible(element), "Элемент не удален");
                        products.remove(product);
                        break;
                    }
                }
                break;
            }
        }
        checkSum();
        return this;
    }

    /**
     * Добавление существующего товара
     *
     * @param name - имя заданное в поиске
     * @return CartPage
     */
    public ShoppingBasketPage add(String name) {

        for (WebElement element : listProductsNames) {
            if (element.getText().contains(name)) {
                WebElement addButton = element.findElement(By.xpath("//div[@class='cart-items__content-container']//a[contains(text(),'" + name + "')]/../../../../..//i[@class='count-buttons__icon-plus']"));
                Assertions.assertTrue(waitUtilElementToBeClickable(addButton), "Кнопка добавить не активна");
                addButton.click();
                break;
            }
        }
        for (Product product : products) {
            if (product.getSearchedName().equals(name)) {
                product.setQuantity(product.getQuantity() + 1);
                break;
            }
        }
        return this;
    }

    /**
     * Возвращение удаленного ранее товара
     *
     * @return CartPage
     */
    public ShoppingBasketPage addBack() {
        Assertions.assertTrue(waitUtilElementToBeClickable(returnDeletedProduct), "Кнопка восстановить не кликабельна");
        int prevNumber = Integer.parseInt(numberInCart.getText());
        returnDeletedProduct.click();
        Product lastDeleted = deletedProducts.get(deletedProducts.size() - 1);
        Assertions.assertTrue(waitUtilTextToBePresent(numberInCart, String.valueOf(prevNumber + lastDeleted.getQuantity())), "Не верное изменение счетчика корзины");
        Assertions.assertTrue(waitUtilElementToBeVisibleInList(listProductsNames, lastDeleted.getSearchedName()));
        products.add(lastDeleted.getIndexInArrayProducts(), lastDeleted);
        checkSum();
        return this;
    }
}
