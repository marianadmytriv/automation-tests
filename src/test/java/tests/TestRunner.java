package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.BrowserStrategyExtension;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import lombok.val;
import utilities.config_properties_interface.TestConfiguration;
import lombok.Getter;
import org.aeonbits.owner.ConfigCache;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;
import utilities.Browser;

import java.net.MalformedURLException;

@Getter
public abstract class TestRunner extends Assertions {

    protected static final TestConfiguration TEST_CONFIGURATION = ConfigCache.getOrCreate(TestConfiguration.class, System.getProperties());
    protected static final SoftAssertions SOFT_ASSERTIONS = new SoftAssertions();

    @RegisterExtension
    static BrowserStrategyExtension browserStrategy = new BrowserStrategyExtension();

    @RegisterExtension
    static ScreenShooterExtension screenShooterExtension = new ScreenShooterExtension().to("target/screenshots");

    @RegisterExtension
    static TextReportExtension textReportExtension = new TextReportExtension().onFailedTest(true).onSucceededTest(true);


    @BeforeAll
    public static void beforeAllTests() throws MalformedURLException {

       createWebDriver(TEST_CONFIGURATION.browser());

        Configuration.timeout = TEST_CONFIGURATION.timeout();
        Configuration.pollingInterval = TEST_CONFIGURATION.pollingInterval();
        Configuration.startMaximized = TEST_CONFIGURATION.startMaximized();
    }

    /**
     * initializes specified webdrivers, setting profiles.
     *
     * @param browser
     * @throws MalformedURLException
     */
    private static void createWebDriver(String browser) throws MalformedURLException {
            switch (browser) {
                case "firefox":
                    Configuration.browser = Browser.FIREFOX.getBrowserName();
                    break;
                case "chrome":
                default:
                    Configuration.browser = Browser.CHROME.getBrowserName();
                    break;
            }
    }
}
