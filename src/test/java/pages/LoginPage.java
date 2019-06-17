package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

@Getter
@Slf4j
public class LoginPage {
    @FindBy(id = "login_username")
    private SelenideElement usernameField;

    @FindBy(id = "login_password")
    private SelenideElement passField;

    @FindBy(id = "login_submit")
    private SelenideElement loginButton;

    @FindBy(className = "login-panel-first")
    private SelenideElement logoHeader;

    @FindBy(xpath = ".//*[@id='page-dashboard']/ul/li[@class='active']/a[contains(text(), 'Dashboard')]")
    private SelenideElement dashboard;

    public LoginPage verifyLogoHeaderLoginPage() {
        logoHeader.waitUntil(Condition.appear, 12000).waitUntil(Condition.visible, 8000);
        return page(LoginPage.class);
    }

    public LoginPage fillLoginFields(String username, String password) {
        usernameField.waitUntil(Condition.appear, 6000).waitUntil(Condition.visible, 4000).val(username);
        passField.waitUntil(Condition.appear, 6000).waitUntil(Condition.visible, 4000).val(password);
        return page(LoginPage.class);
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    public LoginPage verifyDashboardPage() {
        dashboard.waitUntil(Condition.appear, 4000).waitUntil(Condition.visible, 4000);
        return page(LoginPage.class);
    }

}
