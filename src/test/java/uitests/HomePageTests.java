package uitests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import ui.HomePage;
import ui.HomePageWithPF;
import ui.WebDriverHandler;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageTests {

    //@AfterTest
    @AfterMethod
    public static void afterMethod() {
        WebDriverHandler.closeBrowser();
    }

    @Test
    public void homePageSmokeTest() {
        List<String> menuNamesList = new ArrayList<>(List.of("HOME","TRADING","PLATFORMS","RESEARCH & EDUCATION",
                                                            "PROMOTIONS", "ABOUT US", "PARTNERSHIPS"));
        HomePage homePage = new HomePage();
        homePage.open();
        assertTrue(homePage.isAlertPopUpDisplayed(), "No Cookies Alert pop-up message!");
        assertTrue(homePage.isCookiesAlertPopUpAcceptAllButtonDisplayed(), "No Accept All Cookies button!");
        homePage.clickOnCookiesAlertPopUpAcceptAllButton();
        assertTrue(homePage.isLogoDisplayed(), "Logo wasn't displayed!");
        assertTrue(homePage.isOpenAnRealAccountButtonDisplayed(), "No Open an real account button!");
        assertTrue(homePage.isOpenAnDemoAccountButtonDisplayed(), "No Open an demo account button!");
        assertTrue(homePage.isMainMenuDisplayed(),"No Main Menu!");
        assertEquals(homePage.getMenuNames(),menuNamesList);
        //homePage.browserClose();
    }


    @Test
    public void homePageSmokePFTest() {
        List<String> menuNamesList = new ArrayList<>(List.of("HOME","TRADING","PLATFORMS","RESEARCH & EDUCATION",
                "PROMOTIONS", "ABOUT US", "PARTNERSHIPS"));
        HomePageWithPF homePageWithPF = new HomePageWithPF();
        homePageWithPF.open();
        homePageWithPF.waitForLogoToBeDisplayed();
        assertTrue(homePageWithPF.isAlertPopUpDisplayed(), "No Cookies Alert pop-up message!");
        assertTrue(homePageWithPF.isCookiesAlertPopUpAcceptAllButtonDisplayed(), "No Accept All Cookies button!");
        homePageWithPF.clickOnCookiesAlertPopUpAcceptAllButton();
        assertTrue(homePageWithPF.isLogoDisplayed(), "Logo wasn't displayed!");
        assertTrue(homePageWithPF.isOpenAnRealAccountButtonDisplayed(), "No Open an real account button!");
        assertTrue(homePageWithPF.isOpenAnDemoAccountButtonDisplayed(), "No Open an demo account button!");
        assertTrue(homePageWithPF.isMainMenuDisplayed(),"No Main Menu!");
        assertEquals(homePageWithPF.getMenuNames(),menuNamesList);
        homePageWithPF.browserClose();
    }
}
