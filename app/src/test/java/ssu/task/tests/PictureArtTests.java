package ssu.task.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ssu.task.config.BaseTest;
import ssu.task.models.Picture;
import ssu.task.pages.*;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Slf4j
public class PictureArtTests extends BaseTest {

    /* Tест 1
     * Перейти в раздел "Вышитые картины", выполнить поиск по жанру "Городской пейзаж",
     * и проверить наличие картины "Трамвайный путь" в результатах поиска.
     * */
    @Test(testName = "Проверка наличия 'Трамвайного пути' на вышитых картинках в жанре 'Городской пейзаж'")
    public void VerifyPresenceOfCityscapePaintingInEmbroideredPictures() {
        log.info("Starting test: Navigating to 'Вышитые картины', searching for 'Городской пейзаж' genre, and verifying the presence of 'Трамвайный путь'.");

        HomePage home = new HomePage(getDriver());
        CatalogPage catalog = new CatalogPage(getDriver());
        PicturePage picture = new PicturePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Вышитые картины");
        catalog.selectPictureGenre("Городской пейзаж");
        boolean isCatalogExists = picture.isPictureByNameExists("Трамвайный путь");

        assertTrue(isCatalogExists);
    }

    /* Тест 2
     * Перейти в “Вышитые картины”, произвести поиск по жанру «Городской пейзаж», открыть
     * подробности картины “Трамвайный путь”, проверить, что стиль картины «Реализм».
     * */
    @Test(testName = "Проверка стиля 'Реализм' у картины 'Трамвайный путь' в разделе 'Вышитые картины'")
    public void verifyPaintingStyleTramwayPath() {
        log.info("Starting test: Navigate to 'Вышитые картины', search for 'Городской пейзаж', " +
                "open details of painting 'Трамвайные пути', and verify its style - 'Реализм'.");

        HomePage home = new HomePage(getDriver());
        CatalogPage catalog = new CatalogPage(getDriver());
        PicturePage picture = new PicturePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Вышитые картины");
        catalog.selectPictureGenre("Городской пейзаж");

        boolean isCatalogExists = picture.isPictureByNameExists("Трамвайный путь");

        if (isCatalogExists) {
            picture.openPictureInfo("Трамвайный путь");
            boolean isRealism = picture.checkStylePicture("Реализм");

            assertTrue(isRealism);
        }
    }

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

    /* Тест 4
     * Ввести в поисковую строку «Жираф», проверить, что название первой картины содержит слово «Жираф».
     * */
    @Test(testName = "Поиск по ключевому слову 'Жираф'")
    public void verifyTitleOfFirstPaintingInSearchResultsForGiraffe() {
        log.info("Starting test: Searching for 'Жираф' in the search bar and verifying that the title of the first painting contains the word 'Жираф'.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());

        String request = "Жираф";

        home.enteringTextInSearchField(request);
        boolean isContains = picture.checkContainsRequestInName(request);

        assertTrue(isContains);
    }

    /* Тест 5
     * Перейти в “Ювелирное искусство”, добавить первое изделие в корзину,
     * проверить, что выбранный товар находится в корзине, стоимость товара не изменилась.
     * */
    @Test(testName = "Добавление первого изделия в корзину в разделе 'Ювелирное искусство'")
    public void verifyAddingFirstJewelryItemToCartAndPriceConsistency() {
        log.info("Starting test: Adding the first jewelry item to the cart in the 'Ювелирное искусство' section and verifying its presence and unchanged price.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        BasketPage basket = new BasketPage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Ювелирное искусство");
        Picture firstPicture = picture.putInBasketSpecificPicture(0);

        basket.openBasketFromModalPage();
        boolean isExistAndEqualPrice = basket.checkPictureInBasket(firstPicture);

        assertTrue(isExistAndEqualPrice);
    }

    /* Тест 5
     * Перейти в “Ювелирное искусство”, добавить первое изделие в корзину,
     * проверить, что выбранный товар находится в корзине, стоимость товара не изменилась.
     * */
    @Test(testName = "Неудачное добавление первого изделия в корзину в разделе 'Ювелирное искусство'", enabled = false)
    public void verifyAddingFirstJewelryItemToCartAndPriceConsistencyBadResult() {
        log.info("Starting test: Adding the first jewelry item to the cart in the 'Ювелирное искусство' section and verifying its presence and unchanged price.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        BasketPage basket = new BasketPage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Ювелирное искусство");
        Picture firstPicture = picture.putInBasketSpecificPicture(0);

        basket.openBasketFromModalPage();
        boolean isExistAndEqualPrice = basket.checkPictureInBasket(firstPicture);

        assertFalse(isExistAndEqualPrice);
    }
}


