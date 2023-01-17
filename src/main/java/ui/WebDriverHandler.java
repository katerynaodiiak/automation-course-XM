package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverHandler {

    private static WebDriver webDriver;
    private static WebDriverWait webDriverWait;

    static {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    public static WebDriver getDriver() {
        if(webDriver == null) {
           webDriver = new ChromeDriver();
           webDriver.manage().window().maximize();
           //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
           webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
        return webDriver;
    }

    public static void openPage(String url) {
        getDriver().get(url);
    }

    public static void closeBrowser(){
        getDriver().quit();
        webDriver = null;
        webDriverWait = null;
    }

    public static WebDriverWait getWait() {
        if(webDriverWait == null) {
            webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        }
        return webDriverWait;
    }
}
