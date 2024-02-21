/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ssu.task.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

@Feature("GUI Test site artnow.ru")
@Story("Testing of site functionality as required")
public class BaseTest {
    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    public void setup() {
//        System.setProperty("webdriver.chrome.driver", "");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass(description = "Stop browser")
    public void stopMethod() {
        driver.quit();
        System.out.println("Browser was successfully quited.");
    }

    public WebDriver getDriver() {
        return driver;
    }
}