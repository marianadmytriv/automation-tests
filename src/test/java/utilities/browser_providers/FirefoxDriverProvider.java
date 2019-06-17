package utilities.browser_providers;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;
import static test_data.InslyTestData.DOWNLOADS_PATH;

public class FirefoxDriverProvider implements WebDriverProvider, CustomWebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        FirefoxDriverManager.getInstance().setup();

        FirefoxProfile profile = new FirefoxProfile();
        System.setProperty("webdriver.firefox.marionette", "true");
        profile.setPreference("browser.download.dir", DOWNLOADS_PATH);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/html, application/xhtml+xml, " +
                "application/xml, application/csv, text/plain, application/vnd.ms-excel, text/csv, " +
                "text/comma-separated-values, application/octet-stream");
        DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
        firefoxCapabilities.setCapability("marionette", true);
        firefoxCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return new FirefoxDriver();
    }

    @Override
    public void isHeadless(boolean isHeadless) {

    }

    @Override
    public void withImagesDisabledMode(boolean withImagesDisabledMode) {

    }

    @Override
    public void withProxy(boolean withProxy) {

    }

    @Override
    public void withDebugMode(boolean withDebugMode) {

    }
};
