package tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final String AUTOMATION_PRACTICE_URL = "https://www.rgs.ru/";

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver,30,1000);

        driver.get(AUTOMATION_PRACTICE_URL);

    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
