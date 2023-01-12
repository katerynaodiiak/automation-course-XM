package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePageSelenide {

    private static final String URL = "";

    private static final SelenideElement cookiesAlertPopUp = $("#cookieModal");
    private static final SelenideElement cookiesAlertPopUpAcceptAllButton = $x("//*[@class='col-sm-5']/button[@class = 'btn btn-block btn-red btn-solid js-acceptDefaultCookie gtm-acceptDefaultCookieFirstVisit']");

    private final static SelenideElement logo = $("#navigation-collapse .logo");

    private static final SelenideElement openAnRealAccountButton = $x("//div[@class='buttons-wrap']//div[contains(@class,'hidden-xs')]//a[contains(@class,'btn-red')]");
    private static final SelenideElement openAnDemoAccountButton = $x("//div[@class='buttons-wrap']//div[contains(@class,'hidden-xs')]//a[contains(@class,'btn-green')]");

    private static final SelenideElement mainmenu = $("ul[class*='nav-logo']");

    public void open() {
        WebDriverHandlerSelenide.openPage(URL);
    }

    public boolean isAlertPopUpDisplayed() {
        cookiesAlertPopUp.shouldBe(visible);
        return cookiesAlertPopUp.isDisplayed();
    }

    public boolean isAlertPopUpClosed(){
        cookiesAlertPopUp.shouldNot(visible, Duration.ofSeconds(5));
        if (cookiesAlertPopUp.isDisplayed())
            return false;
        else return true;
    }

    public boolean isCookiesAlertPopUpAcceptAllButtonDisplayed() {
        return cookiesAlertPopUpAcceptAllButton.isDisplayed();
    }

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public void waitForLogoToBeDisplayed() {
        logo.should(exist).shouldBe(visible);
    }

    public boolean isOpenAnRealAccountButtonDisplayed() {
        return openAnRealAccountButton.isDisplayed();
    }

    public boolean isOpenAnDemoAccountButtonDisplayed() {
        return openAnDemoAccountButton.isDisplayed();
    }

    public boolean isMainMenuDisplayed() {
        return mainmenu.isDisplayed();
    }

    public void clickOnCookiesAlertPopUpAcceptAllButton() {
        if (isCookiesAlertPopUpAcceptAllButtonDisplayed()){
            cookiesAlertPopUpAcceptAllButton.click();
        } else throw new RuntimeException("No Accept All Cookies button");
        if (!isAlertPopUpClosed()) throw new RuntimeException("Cookies pop up was not closed!");
    }

    public List<String> getMenuNames() {
        return mainmenu.findAll("li").texts()
                .stream().filter(String -> !String.isEmpty())
                .toList();
    }
}
