package ssu.task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FavoritePage extends BasePage {
    public FavoritePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "img[alt='Избранное']")
    private WebElement favoriteButton;

    @FindBy(css = "div[class='post']")
    private List<WebElement> favoritePictures;

    public void openFavoriteListPage() {
        waitForElementEnable(favoriteButton);
        favoriteButton.click();
    }

    public boolean checkPictureInFavorite(String pictureName) {
        waitForAllElementsTimeoutSeconds(favoritePictures);

        if (!favoritePictures.isEmpty()) {
            for (var picture : favoritePictures) {
                if (picture.getText().contains(pictureName)) {
                    return true;
                }
            }
        }

        return false;
    }
}
