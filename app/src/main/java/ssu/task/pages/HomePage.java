package ssu.task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    // clickable picture link
    @FindBy(xpath = "//div[@class='post']//a")
    WebElement pictureLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickPictureLink () {
        pictureLink.click();
    }

    public String getPictureLinkHref () {
        return pictureLink.getAttribute("href");
    }
}
