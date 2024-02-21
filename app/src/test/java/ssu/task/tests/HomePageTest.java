package ssu.task.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ssu.task.pages.HomePage;

public class HomePageTest extends BaseTest {

    private HomePage home;

    @BeforeMethod
    public void initPage () {
        home = new HomePage(getDriver());
    }

    @Test(description = "Click on the picture link")
    public void clickPictureLinkTest() {
        home.clickPictureLink();
    }

    @Test(description = "Get the href attribute of the post")
    public void getPictureLinkHrefTest() {
        String expecterHref = "123";
        String actualHref = home.getPictureLinkHref();


    }

}





























