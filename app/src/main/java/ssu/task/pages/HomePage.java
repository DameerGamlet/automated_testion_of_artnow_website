package ssu.task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='topmenu']//a[@href='//artnow.ru/ru/gallery.html']")
    WebElement catalogLink;

    @FindBy(xpath = "//*[@id=\"main_container\"]/a[1]")
    WebElement embroideredPicturesLink;

    @FindBy(xpath = "//div[@class='onefilter']//a[@id='genre257']")
    WebElement cityscapesGenre;

    @FindBy(xpath = "//div[@id='applymsg']")
    WebElement applyLink;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickCatalogPage() {
        waitForElementTimeoutSeconds(catalogLink);
        catalogLink.click();
    }

    public void clickEmbroideredPicturesLink() {
        waitForElementTimeoutSeconds(embroideredPicturesLink);
        embroideredPicturesLink.click();
    }

    public void enableClickCityscapesGenreCheckbox() {
        waitForElementTimeoutSeconds(cityscapesGenre);
        cityscapesGenre.click();
    }

    public void clickApply() {
        waitForElementTimeoutSeconds(applyLink);
        applyLink.click();
    }
}