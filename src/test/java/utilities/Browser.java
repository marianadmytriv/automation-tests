package utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum Browser {

    FIREFOX("utilities.browser_providers.FirefoxDriverProvider"),
    CHROME("utilities.browser_providers.ChromeDriverProvider");

    private String browserName;
}
