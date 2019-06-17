package utilities.browser_providers;

import com.codeborne.selenide.WebDriverProvider;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import utilities.config_properties_interface.TestConfiguration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static test_data.InslyTestData.DOWNLOADS_PATH;
import static test_data.InslyTestData.INSLY_URL;
import static utilities.browser_providers.BrowserMobProxy.proxyServer;

public class ChromeDriverProvider implements WebDriverProvider, CustomWebDriverProvider {
    private ChromeOptions options = new ChromeOptions();
    private Map<String, Object> prefs = new HashMap<>();

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        TestConfiguration testConfiguration = ConfigFactory.create(TestConfiguration.class, System.getProperties());

        ChromeDriverManager.getInstance().version("74.0.3729.6").setup();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-browser-side-navigation");

        isHeadless(testConfiguration.headlessMode());
        withImagesDisabledMode(testConfiguration.browserImagesDisabled());
        withDebugMode(testConfiguration.debug());
        withProxy(testConfiguration.proxy());

        prefs.put("download.default_directory", DOWNLOADS_PATH);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

    @Override
    public void isHeadless(boolean isHeadless) {
        if (isHeadless) {
            options.setHeadless(isHeadless);
        }
    }

    @Override
    public void withImagesDisabledMode(boolean withImagesDisabledMode) {
        if (withImagesDisabledMode) {
            HashMap<String, Object> images = new HashMap<>();
            images.put("images", 2);
            prefs.put("profile.default_content_setting_values", images);
        }
    }

    @Override
    public void withDebugMode(boolean withDebugMode) {
        if (withDebugMode) {
            options.addArguments("auto-open-devtools-for-tabs");
        }
    }

    @Override
    public void withProxy(boolean withProxy) {
        if (withProxy) {
            // start the proxy
            proxyServer = new BrowserMobProxyServer();
            proxyServer.start(0);

            // get the Selenium proxy object
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
            proxyServer.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());

            // configure it as a desired capability
            options.setCapability(CapabilityType.PROXY, seleniumProxy);

            // create a new HAR
            proxyServer.newHar(INSLY_URL+"/signup");
        }
    }
}
