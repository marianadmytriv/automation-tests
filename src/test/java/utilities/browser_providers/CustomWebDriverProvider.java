package utilities.browser_providers;

public interface CustomWebDriverProvider {

    void isHeadless(boolean isHeadless);

    void withImagesDisabledMode(boolean withImagesDisabledMode);

    void withProxy(boolean withProxy);

    void withDebugMode(boolean withDebugMode);
}
