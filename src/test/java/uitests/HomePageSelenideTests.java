package uitests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ui.HomePageSelenide;
import ui.WebDriverHandlerSelenide;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageSelenideTests {

    @AfterMethod
    public void afterMethod() {
        WebDriverHandlerSelenide.closeBrowser();
    }

    @Test
    public void test() {
        List<String> menuNamesList = new ArrayList<>(List.of("HOME","TRADING","PLATFORMS","RESEARCH & EDUCATION",
                "PROMOTIONS", "ABOUT US", "PARTNERSHIPS"));
        HomePageSelenide homePageSelenide = new HomePageSelenide();
        homePageSelenide.open();
        assertTrue(homePageSelenide.isAlertPopUpDisplayed(), "No Cookies Alert pop-up message!");
        assertTrue(homePageSelenide.isCookiesAlertPopUpAcceptAllButtonDisplayed(), "No Accept All Cookies button!");
        homePageSelenide.clickOnCookiesAlertPopUpAcceptAllButton();
        homePageSelenide.waitForLogoToBeDisplayed();
        assertTrue(homePageSelenide.isLogoDisplayed(), "Logo wasn't displayed!");
        assertTrue(homePageSelenide.isOpenAnRealAccountButtonDisplayed(), "No Open an real account button!");
        assertTrue(homePageSelenide.isOpenAnDemoAccountButtonDisplayed(), "No Open an demo account button!");
        assertTrue(homePageSelenide.isMainMenuDisplayed(),"No Main Menu!");
        assertEquals(homePageSelenide.getMenuNames(),menuNamesList);
    }
}
