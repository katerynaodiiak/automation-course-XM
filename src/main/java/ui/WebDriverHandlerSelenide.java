package ui;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class WebDriverHandlerSelenide {

    public static void openPage(String url) {
        if (!WebDriverRunner.hasWebDriverStarted()) {
            setUp();
        }
        Selenide.open(url);
    }

    public static void closeBrowser() {
        Selenide.closeWebDriver();
    }

    private static void setUp() {
        Configuration.browser = Browsers.CHROME;
        Configuration.timeout = 10;
        Configuration.baseUrl = "https://www.xm.com";
        Configuration.browserSize = "1920x1080";
    }
}
