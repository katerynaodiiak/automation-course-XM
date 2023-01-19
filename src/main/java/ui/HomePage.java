package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    private static final String URL = "https://www.xm.com/";

    private static final String cookiesAlertPopUp = "#cookieModal";
    //probably you can remove most of the classes from this xpath :)
    private static final String cookiesAlertPopUpAcceptAllButton = "//*[@class='col-sm-5']/button[@class = 'btn btn-block btn-red btn-solid js-acceptDefaultCookie gtm-acceptDefaultCookieFirstVisit']";

    private static final String logo = "#navigation-collapse .logo";

    private static final String openAnRealAccountButton = "//div[@class='buttons-wrap']//div[contains(@class,'hidden-xs')]//a[contains(@class,'btn-red')]";
    private static final String openAnDemoAccountButton = "//div[@class='buttons-wrap']//div[contains(@class,'hidden-xs')]//a[contains(@class,'btn-green')]";

    private static final String mainmenu = "ul[class*='nav-logo']";

    public void open() {
        WebDriverHandler.openPage(URL);
    }

    public void browserClose() {
        WebDriverHandler.closeBrowser();
    }

    //this is not a mistake, just one thing to think about: when you create a method for checking(!) if element is visible or not,
    //such method should be able to return both true and false (because you can base other actions on the fact that element is displayed or not:
    //e.g., if menu is not displayed - click on button that opens this menu); but, as we first wait for element to be visible,
    //we can only get true or exception...
    // so if your method returns information about current state of element's property (visibility, text, etc.),
    //there's no point in waiting for this property to have specific value (but you can wait for element to exist!)
    public boolean isAlertPopUpDisplayed() {
        WebDriverHandler.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cookiesAlertPopUp)));
        return WebDriverHandler.getDriver().findElement(By.cssSelector(cookiesAlertPopUp)).isDisplayed();
    }

    public boolean isAlertPopUpClosed(){
        WebDriverHandler.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cookiesAlertPopUp)));
        if (WebDriverHandler.getDriver().findElement(By.cssSelector(cookiesAlertPopUp)).isDisplayed()){
            return false;
        } else return true;
    }

    public boolean isCookiesAlertPopUpAcceptAllButtonDisplayed() {
        return WebDriverHandler.getDriver().findElement(By.xpath(cookiesAlertPopUpAcceptAllButton)).isDisplayed();
    }

    public boolean isLogoDisplayed() {
        WebDriverHandler.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(logo)));
        return WebDriverHandler.getDriver().findElement(By.cssSelector(logo)).isDisplayed();
    }

    public boolean isOpenAnRealAccountButtonDisplayed() {
        return WebDriverHandler.getDriver().findElement(By.xpath(openAnRealAccountButton)).isDisplayed();
    }

    public boolean isOpenAnDemoAccountButtonDisplayed() {
        return WebDriverHandler.getDriver().findElement(By.xpath(openAnDemoAccountButton)).isDisplayed();
    }

    public boolean isMainMenuDisplayed() {
        return WebDriverHandler.getDriver().findElement(By.cssSelector(mainmenu)).isDisplayed();
    }

    public void clickOnCookiesAlertPopUpAcceptAllButton() {
        if (isCookiesAlertPopUpAcceptAllButtonDisplayed()){
            WebDriverHandler.getDriver().findElement(By.xpath(cookiesAlertPopUpAcceptAllButton)).click();
        } else throw new RuntimeException("No Accept All Cookies button");
        if (!isAlertPopUpClosed()) throw new RuntimeException("Cookies pop up was not closed!");
    }

    public List<String> getMenuNames() {
        WebElement mainMenu = WebDriverHandler.getDriver().findElement(By.cssSelector(mainmenu));
        List<WebElement> links = mainMenu.findElements(By.tagName("li"));
        return links.stream()
                .map(webElement -> webElement.getText())
                .filter(String -> !String.isEmpty())
                .collect(Collectors.toList());
    }
}
