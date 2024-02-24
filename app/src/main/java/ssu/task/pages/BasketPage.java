package ssu.task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ssu.task.models.Picture;

import java.util.List;

import static ssu.task.utils.PictureServiceSupport.getPictureName;
import static ssu.task.utils.PictureServiceSupport.getPicturePrice;

public class BasketPage extends BasePage {

    @FindBy(css = "img[alt='Избранное']")
    private WebElement basketButton;

    @FindBy(css = "button[onclick='sendCartForm();']")
    private WebElement basketModalButton;

    @FindBy(css = "div[class='c_row']")
    private List<WebElement> basketPictures;

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public void openBasketFromModalPage() {
        waitForElementEnable(basketModalButton);
        basketModalButton.click();
    }

    public boolean checkPictureInBasket(Picture picture) {
        waitForAllElementsTimeoutSeconds(basketPictures);

        String name = picture.getName();

        if (!basketPictures.isEmpty()) {
            for (var pic : basketPictures) {

                String pictureName = getPictureName(pic, "c_name");

                if (pic.getText().contains(name) || name.contains(pictureName)) {
                    long picPrice = getPicturePrice(pic);
                    if (picPrice == picture.getPrice()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
