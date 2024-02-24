package ssu.task.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static ssu.task.utils.FindElementSupport.findByLabel;

public class CatalogPage extends BasePage {

    public CatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@id='genrebox']/span[1]/span[1]")
    private WebElement showMoreOnGenreButton;

    @FindBy(xpath = "//div[@id='genrebox']/div")
    private WebElement genreItemList;

    @FindBy(xpath = "//div[@id='applymsg']")
    private WebElement applyGenreButton;

    private final String ART_CONTAINER_XPATH = "//*[@id='sa_container']";
    private final String ART_LIST_XPATH = "//*[@id='sa_container']/div[@class='post']";

    @FindBy(xpath = "//*[@id='sa_container']/div[@class='post']")
    private List<WebElement> pictureList;

    private final String ART_AUTHOR_AND_NAME_XPATH = ".//div[@class='ssize']";

    @Step("Click on genre 'show more' button")
    public CatalogPage clickOnGenreShowMoreButton() {
        waitForElementEnable(showMoreOnGenreButton);
        showMoreOnGenreButton.click();
        return this;
    }

    @Step("Select picture genre")
    public void selectPictureGenre(String genre) {
        WebElement element = findByLabel(genreItemList, genre);
        waitForElementEnable(element);
        element.click();

        waitForElementEnable(applyGenreButton);
        applyGenreButton.click();
    }

    @Step("Check catalog item name")
    public boolean selectCatalogItemName(String catalogItemName) {
        waitForAllElementsTimeoutSeconds(pictureList);

        for (var picture : pictureList) {
            if (picture.getText().contains(catalogItemName))
                return true;
        }

        return false;
    }

    @Step("Click on catalog item by name")
    public CatalogPage clickOnCatalogItemByName(String catalogItemName) {
        List<WebElement> arts = driver.findElements(By.xpath(ART_LIST_XPATH));
        for (WebElement art : arts) {
            WebElement authorAndName = art.findElement(By.xpath(ART_AUTHOR_AND_NAME_XPATH));
            if (authorAndName.getText().contains(catalogItemName)) {
                waitForElementEnable(authorAndName);
                authorAndName.click();
                break;
            }
        }
        return this;
    }

    @Step("Click on catalog item by number")
    public CatalogPage clickOnCatalogItemByNumber(int number) {
        List<WebElement> arts = driver.findElements(By.xpath(ART_LIST_XPATH));
        WebElement art = arts.get(number);
        waitForElementEnable(art);
        art.click();
        return this;
    }
}