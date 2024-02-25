package ssu.task.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ssu.task.config.BaseTest;
import ssu.task.pages.FavoritePage;
import ssu.task.pages.HomePage;
import ssu.task.pages.PicturePage;

import static org.testng.Assert.assertTrue;

@Slf4j
//@Listeners(AllureListener.class)
public class PictureToFavoriteTests extends BaseTest {

    /* Тест 3
     * Перейти в “Батик”, потом добавить первую картину в избранное, проверить, что выбранная картина сохранилась в разделе «Избранное».
     * */
    @Test(testName = "Проверка добавления первой картину в разделе 'Батик' в избранное")
    public void verifyAddingFirstBatikPictureToFavorites() {
        log.info("Starting test: Verify adding the first 'Батик' picture to Favorites");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        FavoritePage favoritePage = new FavoritePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Батик");

        String pictureName = picture.putInFavoriteSpecificPicture(0);

        favoritePage.openFavoriteListPage();
        boolean isExist = favoritePage.checkPictureInFavorite(pictureName);

        assertTrue(isExist);
    }
}
