package utilities.config_properties_interface;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration/test_configuration.properties")
//@Config.Sources("file:src/test/resources/test_configuration.properties")
public interface TestConfiguration extends Config {
    @Key("timeout")
    long timeout();

    @Key("polling.interval")
    long pollingInterval();

    @Key("browser")
    String browser();

    @Key("browser.images.disabled")
    boolean browserImagesDisabled();

    @Key("headless.mode")
    boolean headlessMode();

    @Key("proxy")
    boolean proxy();

    @Key("debug")
    boolean debug();

    @Key("start.maximized")
    boolean startMaximized();
}
