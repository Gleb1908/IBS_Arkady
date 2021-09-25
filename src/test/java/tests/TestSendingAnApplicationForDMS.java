package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestSendingAnApplicationForDMS extends BaseTest {

    private static final String NAVBAR_BUTTON_MENU_LOC = "//a[@class=\"hidden-xs\" and @data-toggle=\"dropdown\"]";
    private static final String COVID_FRAME_LOC = "//iframe[@class=\"flocktory-widget\"]";
    private static final String QUIT_BUTTON_COVID_FRAME_LOC = "//button[@class=\"CloseButton\"]";
    private static final String CONTEXT_BAR_COOKIE_LOC = "//div[@class=\"col-md-10half context-bar-row-content\"]";
    private static final String QUIT_BUTTON_CONTEXT_BAR_COOKIE_LOC = "//div[@class=\"btn btn-default text-uppercase\"]";
    private static final String NAVBAR_BUTTON_MENU_HEALTH_LOC = "//div[@class='h3 adv-analytics-navigation-line2-link' and ./a[contains(.,'Здоровье')]]/a[@class=\"hidden-xs\"]";
    private static final String DMS_BUTTON_LOC = "//div[@class='list-group list-group-rgs-menu collapse']/a[@class=\"list-group-item adv-analytics-navigation-line4-link\" and contains(.,'ДМС')]";
    private static final String DMS_HEADER_LOC = "//div[@class=\"clearfix\"]/h1/text()";
    private static final String SENDING_AN_APPLICATION_BUTTON_LOC = "//div[@class=\"rgs-context-bar-content-call-to-action-buttons\"]/a[@class=\"btn btn-default text-uppercase hidden-xs adv-analytics-navigation-desktop-floating-menu-button\"]";
    private static final String SEND_BUTTON_LOC = "";
    private static final String SECOND_NAME_FIELD_LOC = "//div[@class=\"validation-group-error-wrap\"]";
    private static final String NAME_FIELD_LOC = "";
    private static final String PATRONYMIC_FIELD_LOC = "";
    private static final String REGION_FIELD_LOC = "";
    private static final String NUMBER_FIELD_LOC = "";
    private static final String MAIL_FIELD_LOC = "";
    private static final String DATE_FIELD_LOC = "";
    private static final String COMMENT_FIELD_LOC = "";
    private static final String CHECK_BOX_AGREE_LOC = "";
    private static final String SEND_FORM_BUTTON_LOC = "";

    @Test
    public void testSelenium() {

        //Проверка на наличие плашки о вакцинации и если присутствует, то нажатие на "крестик"
//        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10, 300);
//        WebElement frameWait = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(COVID_FRAME_LOC)));
//        if (frameWait.isDisplayed()) {
//            webDriver.switchTo().frame(frameWait);
//            WebElement covidFrame = webDriver.findElement(By.xpath(QUIT_BUTTON_COVID_FRAME_LOC));
//            covidFrame.click();
//            webDriver.switchTo().defaultContent();
//        }

        //Проверка на наличие плашки с использованием cookie и если присутствует, то нажатие кнопки "ок"
        WebElement contextBarCookie = webDriver.findElement(By.xpath(CONTEXT_BAR_COOKIE_LOC));
        if (contextBarCookie.isDisplayed()) {
            WebElement quitButton = webDriver.findElement(By.xpath(QUIT_BUTTON_CONTEXT_BAR_COOKIE_LOC));
            quitButton.click();
        }

        //нахождение кнопки "меню" и клик по ней
        WebElement navBarButtonMenu = webDriver.findElement(By.xpath(NAVBAR_BUTTON_MENU_LOC));
        navBarButtonMenu.click();

        //нахождение кнопки "Здоровье" в выпадающем списке "меню" и клик по ней
        WebElement navBarButtonMenuHealth = webDriver.findElement(By.xpath(NAVBAR_BUTTON_MENU_HEALTH_LOC));
        navBarButtonMenuHealth.click();

        //нахождение кнопки "Добровольное медицинское страхование (ДМС)" и клик по ней
        WebElement dmsButton = webDriver.findElement(By.xpath(DMS_BUTTON_LOC));
        dmsButton.click();

        //проверка соответствия заголовка страницы "ДМС — добровольное медицинское страхование"
//        WebElement dmsHeader = webDriver.findElement(By.xpath(DMS_HEADER_LOC));
//        String name = dmsHeader.getText();
//        Assertions.assertEquals("ДМС — добровольное медицинское страхование", name, "Заголовок \"ДМС\" не совпадает");

        //нахождение кнопки "Отправить заявку" и клик по ней
        WebElement sendingButton = webDriver.findElement(By.xpath(SENDING_AN_APPLICATION_BUTTON_LOC));
        sendingButton.click();

        //нахождение поля для ввода фамилии
        WebElement secondNameField = webDriver.findElement(By.xpath(SECOND_NAME_FIELD_LOC));
        secondNameField.click();


    }
}
