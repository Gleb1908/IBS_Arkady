package tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    private static final String AUTOMATION_PRACTICE_URL = "https://www.rgs.ru/";

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        webDriverWait = new WebDriverWait(webDriver,60,1000);

        webDriver.get(AUTOMATION_PRACTICE_URL);

    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }

}
