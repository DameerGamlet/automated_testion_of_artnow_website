package ssu.task.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ssu.task.utils.FindElementSupport;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@id='left_container']//li[@class='menu-group gids']")
    private WebElement showMoreOnCategoryButton;

    @FindBy(xpath = "//*[@id='left_container']/div/ul[2]//li")
    private WebElement menuItemElement;

    @FindBy(css = "input[name='qs']")
    private WebElement searchField;

    @FindBy(css = "button[class^='control']")
    private WebElement searchButton;

    private final String GLOBAL_SEARCH_INPUT_XPATH = "//*[@id='MainSearchForm']/div/div[1]/input[3]";
    private final String GLOBAL_SEARCH_BUTTON_XPATH = "/html/body/div[1]/span[9]/form/div/div[2]/button";
    private final String ART_CATEGORY_MENU_ITEM_XPATH_TEMPLATE = "//*[@id='left_container']/div/ul[2]//li/a[contains(text(), '%s')]";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Click on art category 'show more' button")
    public void clickShowMoreOnCategory() {
        waitForElementEnable(showMoreOnCategoryButton);
        showMoreOnCategoryButton.click();
    }

    public void enteringTextInSearchField(String request) {
        waitForAllElementsTimeoutSeconds(List.of(searchField, searchButton));
        searchField.sendKeys(request);
        searchButton.click();
    }

    /*@Step("Input string to search")
    public void inputStringToSearch(CharSequence searchString) {
        WebElement globalSearchInput = driver.findElement(By.xpath(GLOBAL_SEARCH_INPUT_XPATH));
        waitForElementEnable(globalSearchInput);
        globalSearchInput.sendKeys(searchString);
        WebElement globalSearchButton = driver.findElement(By.xpath(GLOBAL_SEARCH_BUTTON_XPATH));
        waitForElementEnable(globalSearchButton);
        globalSearchButton.click();
    }*/

    @Step("Click on art category menu item")
    public void clickMenuItem(String menuItemName) {
        WebElement menuItem = FindElementSupport.findByLink(menuItemElement, menuItemName);
        waitForElementEnable(menuItem);
        menuItem.click();
    }

    @Step("WRONG! Click on art category menu item")
    public void clickOnArtCategoryMenuItemWrong(String menuItemName) {
        WebElement menuItemList = driver.findElement(By.xpath("//*[@id='leftt_container']/div/ul[2]"));
        WebElement menuItem = menuItemList.findElement(By.xpath(String.format(ART_CATEGORY_MENU_ITEM_XPATH_TEMPLATE, menuItemName)));
        waitForElementEnable(menuItem);
        menuItem.click();
    }
}
