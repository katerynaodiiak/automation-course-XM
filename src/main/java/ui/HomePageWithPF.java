package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class HomePageWithPF {

    private static final String URL = "https://www.xm.com/#";

    @FindBy(css = "#cookieModal")
    private WebElement cookiesAlertPopUp;

    @FindBy(xpath = "//*[@class='col-sm-5']/button[@class = 'btn btn-block btn-red btn-solid js-acceptDefaultCookie gtm-acceptDefaultCookieFirstVisit']")
    private WebElement cookiesAlertPopUpAcceptAllButton;

    @FindBy(css = "#navigation-collapse .logo")
    private WebElement logo;

    @FindBy(xpath = "//div[@class='buttons-wrap']//div[contains(@class,'hidden-xs')]//a[contains(@class,'btn-red')]")
    private WebElement openAnRealAccountButton;

    @FindBy(xpath = "//div[@class='buttons-wrap']//div[contains(@class,'hidden-xs')]//a[contains(@class,'btn-green')]")
    private WebElement openAnDemoAccountButton;

    @FindBy(css = "ul[class*='nav-logo']")
    private WebElement mainmenu;

    public HomePageWithPF() {
        PageFactory.initElements(WebDriverHandler.getDriver(), this);
    }

    public void open() {
        WebDriverHandler.openPage(URL);
    }
    public void browserClose() {WebDriverHandler.closeBrowser();}

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public void waitForLogoToBeDisplayed() {
        WebDriverHandler.getWait().until(ExpectedConditions.visibilityOf(logo));
    }

    public boolean isAlertPopUpDisplayed() {
        WebDriverHandler.getWait().until(ExpectedConditions.visibilityOf(cookiesAlertPopUp));
        return cookiesAlertPopUp.isDisplayed();
    }

    public boolean isAlertPopUpClosed(){
        WebDriverHandler.getWait().until(ExpectedConditions.invisibilityOf(cookiesAlertPopUp));
        if (cookiesAlertPopUp.isDisplayed()){
            return false;
        } else return true;
    }

    public boolean isCookiesAlertPopUpAcceptAllButtonDisplayed() {
        return cookiesAlertPopUpAcceptAllButton.isDisplayed();
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
        List<WebElement> links = mainmenu.findElements(By.tagName("li"));
        return links.stream()
                .map(webElement -> webElement.getText())
                .filter(String -> !String.isEmpty())
                .collect(Collectors.toList());
    }
}
