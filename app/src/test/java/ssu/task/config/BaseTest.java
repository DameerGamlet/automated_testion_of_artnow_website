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

import java.time.Duration;

@Slf4j
public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static final String ORIGINS = "--remote-allow-origins=*";

    private final String site = "https://artnow.ru/";

    public static WebDriverWait webDriverWait;
    public static Duration webDriverWaitTimeout;

    protected WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @BeforeMethod(description = "Start webdriver, set up browser")
    @Parameters(value = "browser")
    public void setup(String browser) {
        log.info("""
                Initializing browser setup for automated testing...
                Selected site for testing: {}
                """, site);
        log.info("Selected browser for testing: {}", browser);

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBrowserVersion("123.0");
            driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
        } else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions optionsChrome = new ChromeOptions();
            optionsChrome.addArguments(ORIGINS);
            optionsChrome.addArguments("--no-sandbox");
            optionsChrome.addArguments("--disable-dev-shm-usage");
            optionsChrome.setBrowserVersion("122.0.6261.69");
            driverThreadLocal.set(new ChromeDriver(optionsChrome));
        } else {
            throw new IllegalCallerException("You need to add a specific browser in the configurations");
        }

        webDriverWaitTimeout = Duration.parse("PT20S");
        webDriverWait = new WebDriverWait(getDriver(), webDriverWaitTimeout);

        log.info("Navigating to site: {}", site);
        getDriver().get(site);
    }

    @AfterMethod(description = "Stop browser")
    public void stopMethod() {
        getDriver().quit();
        log.info("Browser successfully quited.");
    }
}
