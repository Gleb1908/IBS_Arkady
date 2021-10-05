package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.stream.Stream;

public class TestSendingAnApplicationForDMS extends BaseTest {

    private static final String NAV_BAR_BUTTON_MENU_LOC = "//a[@class='hidden-xs' and @data-toggle='dropdown']";
    private static final String QUIT_BUTTON_CONTEXT_BAR_COOKIE_LOC = "//div[@class='btn btn-default text-uppercase']";
    private static final String NAVBAR_BUTTON_MENU_HEALTH_LOC = "//div[a[contains(text(),'Здоровье')]]/a[@class='hidden-xs']";
    private static final String DMS_BUTTON_LOC = "//a[contains(@class,'list-group') and contains(text(),'ДМС')]";
    private static final String SENDING_AN_APPLICATION_BUTTON_LOC = "//a[contains(text(),'Отправить заявку')]";
    private static final String CHECK_BOX_AGREE_LOC = "//input[@class='checkbox']";
    private static final String SEND_FORM_BUTTON_LOC = "//button[@id='button-m']";
    private static final String ERROR_LINE_LOC = "//input[@name='Email']/..//span";


    static Stream<Arguments> fieldNames() {
        return Stream.of(
                Arguments.arguments("Иванов", "Владимир", "Ярославович", "Владимирская область", "987 654 32 10", "vladimir.ivanov", "11.10.2021", "Прошу со мной никогда не связываться, я просто балуюсь"),
                Arguments.arguments("Ярославов", "Иван", "Владимирович", "Ивановская область", "987 012 34 56", "ivan.yaroslavov", "12.10.2021", "Свяжитесь со мной после 18:00"),
                Arguments.arguments("Владимиров", "Ярослав", "Иванович", "Ярославская область", "987 654 01 23", "yaroslav.vladimirov", "13.10.2021", "Я бессмертный, зачем мне страховка")
        );
    }

    @ParameterizedTest(name="#{index} - Аргументы теста: {1} - {3}")
    @MethodSource("fieldNames")
    public void testSelenium(
            String lastName,
            String firstName,
            String middleName,
            String region,
            String phoneNumber,
            String email,
            String contactDate,
            String comment) {

        WebElement cookiesBtnClose = driver.findElement(By.xpath(QUIT_BUTTON_CONTEXT_BAR_COOKIE_LOC));
        cookiesBtnClose.click();

        // выбрать пункт "меню"
        WebElement menuButton = driver.findElement(By.xpath(NAV_BAR_BUTTON_MENU_LOC));
        waitUtilElementToBeClickable(menuButton);
        menuButton.click();

        // выбрать пункт подменю - "Здоровье"
        WebElement menuButtonHealth = driver.findElement(By.xpath(NAVBAR_BUTTON_MENU_HEALTH_LOC));
        waitUtilElementToBeClickable(menuButtonHealth);
        menuButtonHealth.click();

        // проверка открытия страницы "Здоровье"
        Assertions.assertEquals(driver.getTitle(),
                "Страхование здоровья",
                "Заголовок отсутствует/не соответствует требуемому");

        // нажать кнопку "Добровольное медицинское страхование (ДМС)"
        WebElement dmsButton = driver.findElement(By.xpath(DMS_BUTTON_LOC));
        waitUtilElementToBeClickable(dmsButton);
        dmsButton.click();

        // проверка открытия страницы "Добровольное медицинское страхование (ДМС)"
        String pageTitleLoc = "//h1";
        waitUtilElementToBeVisible(By.xpath(pageTitleLoc));
        WebElement pageTitle = driver.findElement(By.xpath(pageTitleLoc));
        Assertions.assertEquals(pageTitle.getText(),
                "ДМС — добровольное медицинское страхование",
                "Заголовок отсутствует/не соответствует требуемому");

        // нажать кнопку "Отправить заявку"
        WebElement sendRequestButton = driver.findElement(By.xpath(SENDING_AN_APPLICATION_BUTTON_LOC));
        waitUtilElementToBeClickable(sendRequestButton);
        sendRequestButton.click();

        // заполнить поля данными
        String fieldXPath = "//input[@name='%s']";
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "LastName"))), lastName);
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "FirstName"))), firstName);
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "MiddleName"))), middleName);

        WebElement selectElemLoc = driver.findElement(By.xpath("//select"));
        Select selectElem = new Select(selectElemLoc);
        selectElem.selectByVisibleText(region);

        fillInputPhone(driver.findElement(By.xpath("//input[contains(@data-bind,'Phone')]")), phoneNumber);
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "Email"))), email);
        fillInputDate(driver.findElement(By.xpath("//input[@name='ContactDate']")), contactDate);
        fillInputField(driver.findElement(By.xpath("//textarea[@name='Comment']")), comment);

        WebElement checkBoxButton = driver.findElement(By.xpath(CHECK_BOX_AGREE_LOC));
        checkBoxButton.click();

        WebElement sendButton = driver.findElement(By.xpath(SEND_FORM_BUTTON_LOC));
        waitUtilElementToBeClickable(sendButton);
        sendButton.click();

        //проверка, что у поля "E-mail" появилась строка с ошибкой
        Assertions.assertEquals("Введите адрес электронной почты", driver.findElement(By.xpath(ERROR_LINE_LOC)).getText().trim(), "Сообщение об ошибке почты отсутствует или изменилось");

    }


    /**
     * Скрол до элемента на js коде
     *
     * @param element - веб элемент до которого нужно проскролить
     */
    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Явное ожидание того что элемент станет кликабельный
     *
     * @param element - веб элемент до которого нужно проскролить
     */
    private void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание того что элемент станет видемым
     *
     * @param locator - локатор до веб. элемента, который мы ожидаем найти, и который виден на странице
     */
    private void waitUtilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Заполнение полей определённым значений
     *
     * @param element - веб элемент (поле какое-то) которое планируем заполнить)
     * @param value - значение которы мы заполняем веб элемент (поле какое-то)
     */
    private void fillInputField(WebElement element, String value) {
        waitUtilElementToBeClickable(element);
        element.click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

    private void fillInputDate(WebElement element, String value) {
        waitUtilElementToBeClickable(element);
        element.click();
        element.clear();
        element.sendKeys(value);
        element.sendKeys(Keys.ENTER);
//        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
//        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

    private void fillInputPhone(WebElement element, String value) {
        waitUtilElementToBeClickable(element);
        element.click();
        element.sendKeys(value);
        element.sendKeys(Keys.ENTER);
//        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
//        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

}