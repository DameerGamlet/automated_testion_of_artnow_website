package ssu.task.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import ssu.task.models.BrowsersSupport;

import java.time.Duration;

@Slf4j
public class BaseTest {
    protected static WebDriver driver;
    public static final String ORIGINS = "--remote-allow-origins=*";

    private final String site = "https://artnow.ru/";

    public static WebDriverWait webDriverWait;
    public static Duration webDriverWaitTimeout;

    @BeforeMethod(description = "Start webdriver, set up browser")
    @Parameters(value = "browser")
    public void setup(String browser) {

        log.info("""
                Initializing browser setup for automated testing...
                Selected site for testing: {}
                """, site);
        log.info("Selected browser for testing: {}", browser);

        if (browser.equalsIgnoreCase(String.valueOf(BrowsersSupport.FIREFOX))) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBrowserVersion("123.0");
            driver = new FirefoxDriver();
            WebDriverManager.firefoxdriver().setup();
        } else if (browser.equalsIgnoreCase(String.valueOf(BrowsersSupport.CHROME))) {
            ChromeOptions optionsChrome = new ChromeOptions();
            optionsChrome.addArguments(ORIGINS);
            optionsChrome.addArguments("--no-sandbox");
            optionsChrome.addArguments("--disable-dev-shm-usage");
            optionsChrome.setBrowserVersion("122.0.6261.69");

            driver = new ChromeDriver(optionsChrome);
        } else {
            throw new IllegalCallerException("You need to add a specific browser in the configurations");
        }

        webDriverWaitTimeout = Duration.parse("PT20S");
        webDriverWait = new WebDriverWait(driver, webDriverWaitTimeout);

        log.info("Navigating to site: {}", site);
        driver.get(site);
    }

    @AfterMethod(description = "Stop browser")
    public void stopMethod() {
        driver.quit();
        log.info("Browser successfully quited.");
    }
}