package ssu.task.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static ssu.task.config.TestConfig.*;
import static ssu.task.models.BrowsersSupport.CHROME;
import static ssu.task.models.BrowsersSupport.FIREFOX;

@Slf4j
public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriverWait webDriverWait;


    protected WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @BeforeMethod(description = "Start webdriver, set up browser")
    @Parameters(value = "browser")
    public void setup(String browser) {

        log.info("""
                Initializing browser setup for automated testing...
                Selected site for testing: {}
                """, SITE_URL);
        log.info("Selected browser for testing: {}", browser);

        WebDriver driver;

        if (browser.equalsIgnoreCase(FIREFOX.getBrowserName())) {
//            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBrowserVersion("123.0");
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
            driverThreadLocal.set(driver);
        } else if (browser.equalsIgnoreCase(CHROME.getBrowserName())) {
//            WebDriverManager.chromedriver().setup();
            ChromeOptions optionsChrome = new ChromeOptions();
//            optionsChrome.addArguments("--remote-allow-origins=*");
//            optionsChrome.addArguments("--no-sandbox");
//            optionsChrome.addArguments("--disable-dev-shm-usage");
            optionsChrome.addArguments("--headless");
            optionsChrome.setBrowserVersion("122.0.6261.69");
            driver = new ChromeDriver(optionsChrome);
            driverThreadLocal.set(driver);
        } else {
            throw new IllegalCallerException("You need to add a specific browser in the configurations");
        }

        driver.manage().deleteAllCookies();
        webDriverWait = new WebDriverWait(getDriver(), Duration.parse("PT20S"));

        log.info("Navigating to site: {}", SITE_URL);
        getDriver().get(SITE_URL);
    }

    @AfterMethod(description = "Stop browser")
    public void stopMethod() {
        getDriver().quit();
        log.info("Browser successfully quited.");
    }
}
