package ssu.task.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ssu.task.models.Picture;

import java.util.List;

import static ssu.task.utils.PictureServiceSupport.getPictureName;
import static ssu.task.utils.PictureServiceSupport.getPicturePrice;

public class PicturePage extends BasePage {
    public PicturePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//*[@id='sa_container']/div[@class='post']")
    private List<WebElement> pictureList;

    @FindBy(xpath = "//div[@class='infocontainer']//div[@class='txtline lft']")
    private List<WebElement> pictureInfo;

    private WebElement favButton;

    @FindBy(css = "#CartButton1127052")
    private WebElement basketButton;

    @Step("Check catalog item name")
    public boolean isPictureByNameExists(String pictureName) {
        waitForAllElementsTimeoutSeconds(pictureList);

        for (var picture : pictureList) {
            if (picture.getText().contains(pictureName)) {
                return true;
            }
        }

        return false;
    }

    public void openPictureInfo(String pictureName) {
        waitForAllElementsTimeoutSeconds(pictureList);

        for (WebElement picture : pictureList) {
            if (picture.getText().contains(pictureName)) {
                picture.click();
                break;
            }
        }
    }

    public boolean checkStylePicture(String style) {
        waitForAllElementsTimeoutSeconds(pictureInfo);

        for (WebElement picture : pictureInfo) {
            if (picture.getText().contains("Стиль:") && picture.getText().contains(style)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkContainsRequestInName(String request) {
        waitForAllElementsTimeoutSeconds(pictureList);

        WebElement picture = null;
        if (!pictureList.isEmpty()) {
            picture = pictureList.get(0);
        }

        assert picture != null;
        String pictureName = getPictureName(picture, "ssize");

        return pictureName.contains(request);
    }

    public String putInFavoriteSpecificPicture(int index) {
        waitForAllElementsTimeoutSeconds(pictureList);

        WebElement picture = null;
        if (pictureList.size() >= index + 1) {
            picture = pictureList.get(index);
        }

        assert picture != null;
        favButton = picture.findElement(By.className("heart"));

        favButton.click();
        timeSleep();
        return getPictureName(picture, "ssize");
    }

    public Picture putInBasketSpecificPicture(int index) {
        waitForAllElementsTimeoutSeconds(pictureList);

        WebElement picture = null;
        if (pictureList.size() >= index + 1) {
            picture = pictureList.get(index);
        }

        assert picture != null;
        basketButton = picture.findElement(By.className("oclick"));

        basketButton.click();
        timeSleep();
        return new Picture(getPictureName(picture, "ssize"), getPicturePrice(picture));
    }
}
