package ssu.task.tests;

import org.testng.annotations.Test;
import ssu.task.pages.HomePage;
import ssu.task.config.BaseTest;

/*
 * 1. Перейти в “Вышитые картины”,
 * 2. произвести поиск по жанру «Городской пейзаж»,
 * 3. проверить, что картина “Трамвайный путь” присутствует в выдаче.
 * */
public class HomePageTest extends BaseTest {

    @Test(testName = "Check art name with the name 'Трамвайный путь'")
    public void checkArtNameTramWay() {
        HomePage home = new HomePage(driver);
        home.clickCatalogPage();
        home.clickEmbroideredPicturesLink();
        home.enableClickCityscapesGenreCheckbox();
        home.clickApply();
    }

}





























