package ssu.task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

abstract class BasePage {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 10;
    private static final int DEFAULT_TIME_OF_SECONDS = 1;

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementTimeoutSeconds(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitFotElementEnable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void timeSleep() {
        try {
            TimeUnit.SECONDS.sleep(DEFAULT_TIME_OF_SECONDS);
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
    }
}