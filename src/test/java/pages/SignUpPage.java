package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import models.AdminAccountDetails;
import models.Company;
import models.TermsAndConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static utilities.RandomUtils.getRandomElementInList;

@Slf4j
@Getter
public class SignUpPage {
    public String companyNameField = "name";
    public String countryField = "address_country";
    public String inslyAddressField = "tag";
    public String companyProfileField = "company_profile";
    public String numberOfEmployeesField = "company_no_employees";
    public String describeYourselfField = "company_person_description";

    public String emailField = "admin_email";
    public String accountAdminNameField = "admin_name";
    public String passwordField = "person_password";
    public String passwordRepeatField = "person_password_repeat";
    public String phoneField = "admin_phone";

    public String termsAndConditionsCheckbox = "termsandconditions";
    public String privacyPolicyCheckbox = "privacypolicy";
    public String storePersonalDataCheckbox = "data_processing";

    @FindBy(name = "submit_save")
    private SelenideElement signUpButton;

    @FindBy(xpath = ".//*[@id='broker_person_password']/following-sibling::a")
    private SelenideElement suggestSecurePasswordLink;

    @FindBy(id = "insly_alert")
    private SelenideElement suggestSecurePasswordDialog;

    @FindBy(xpath = ".//*[@id='insly_alert']/child::b")
    private SelenideElement suggestSecurePassword;

    public SelenideElement getSignUpField(String field) {
        return $(By.name("broker_" + field)).waitUntil(visible, 1000);
    }

    public List<SelenideElement> getListValuesFromCountryDropDown(String field) {
        return $$(By.xpath(".//select[@name='broker_" + field + "']/option[count(preceding::option)<10 and count(preceding::option)>=1]"));
    }

    public SelenideElement getButton(String button) {
        return $(By.xpath(".//button[contains(text(), '" + button + "')]")).waitUntil(visible, 1000);
    }

    public String setRandomValueFromCountryDropDown(String field) {
        val country = String.valueOf(getRandomElementInList(getListValuesFromCountryDropDown(field)).getValue());
        getSignUpField(field).waitUntil(visible, 12000)
                .selectOptionByValue(country);
        return country;
    }

    public SignUpPage fillCompanyFieldsWithoutAddress(Company company) {
        getSignUpField(companyNameField).waitUntil(visible, 12000).val(company.getCompanyName());
        company.setCountry(setRandomValueFromCountryDropDown(countryField));
        getSignUpDropDown(companyProfileField).waitUntil(visible, 12000).selectOptionByValue(company.getCompanyProfile());
        getSignUpDropDown(numberOfEmployeesField).waitUntil(visible, 12000).selectOptionByValue(company.getNumberOfEmployees());
        getSignUpDropDown(describeYourselfField).waitUntil(visible, 12000).selectOptionByValue(company.getDescribeYourself());
        return page(SignUpPage.class);
    }

    public Company getEnteredCompanyData() {
        return Company.builder()
                .companyName(getSignUpField(companyNameField).getValue())
                .country(getSignUpField(countryField).getSelectedValue())
                .inslyAddress(getSignUpField(inslyAddressField).getValue())
                .companyProfile(getSignUpDropDown(companyProfileField).getSelectedValue())
                .numberOfEmployees(getSignUpDropDown(numberOfEmployeesField).getSelectedValue())
                .describeYourself(getSignUpDropDown(describeYourselfField).getSelectedValue())
                .build();
    }

    public AdminAccountDetails getEnteredAdminAccountDetailsData() {
        return AdminAccountDetails.builder()
                .webEmail(getSignUpField(emailField).getValue())
                .accountManagerName(getSignUpField(accountAdminNameField).getValue())
                .password(getSignUpField(passwordField).getValue())
                .passwordRepeat(getSignUpField(passwordRepeatField).getValue())
                .phone(getSignUpField(phoneField).getValue())
                .build();
    }

    public SignUpPage fillAdminAccountDetailsFieldsWithoutPassword(AdminAccountDetails adminAccountDetails) {
        getSignUpField(emailField).waitUntil(visible, 12000).val(adminAccountDetails.getWebEmail());
        getSignUpField(accountAdminNameField).waitUntil(visible, 12000).val(adminAccountDetails.getAccountManagerName());
        getSignUpField(phoneField).waitUntil(visible, 12000).val(adminAccountDetails.getPhone());
        return page(SignUpPage.class);
    }

    public String fillPasswordWithSuggested() {
        suggestSecurePasswordLink.waitUntil(visible, 4000).click();
        getSuggestSecurePasswordDialog().waitUntil(appears, 4000).waitUntil(visible, 4000).shouldHave(text("Your password is:"));
        val pass = suggestSecurePassword.getText();
        getButton("OK").click();
        return pass;
    }

    public SignUpPage checkIconStatus(String field, String iconStatus) {
        $(By.xpath(".//*[@id='status_broker_" + field + "']/span")).has(attribute("class", "icon-" + iconStatus));
        return page(SignUpPage.class);
    }

    public SignUpPage checkCompanyIconStatuses(String iconStatus) {
        checkIconStatus(companyNameField, iconStatus);
        checkIconStatus(countryField, iconStatus);
        checkIconStatus(inslyAddressField, iconStatus);
        return page(SignUpPage.class);

    }

    public SelenideElement getSignUpDropDown(String field) {
        return $(By.name("prop_" + field)).waitUntil(visible, 1000);
    }

    public SelenideElement getTermsAndConditionsCheckBox(String checkbox) {
        return $(By.xpath(".//*[@id='agree_" + checkbox + "']/following-sibling::span")).waitUntil(visible, 1000);
    }

    public SignUpPage checkOutTermsAndConditions(TermsAndConditions termsAndConditions) {
        if (termsAndConditions.isTermsAndCondition() == true)
            getTermsAndConditionsCheckBox(termsAndConditionsCheckbox).waitUntil(visible, 12000).click();
        if (termsAndConditions.isPrivacyPolicy() == true)
            getTermsAndConditionsCheckBox(privacyPolicyCheckbox).waitUntil(visible, 12000).click();
        if (termsAndConditions.isStorePersonalData() == true)
            getTermsAndConditionsCheckBox(storePersonalDataCheckbox).waitUntil(visible, 12000).click();
        return page(SignUpPage.class);
    }

    public SelenideElement termsAndConditionsLink(String checkbox) {
        return $(By.xpath(".//*[@id='agree_" + checkbox + "']/following-sibling::a")).waitUntil(visible, 1000);
    }

    public SignUpPage clickOnTermsAndConditionLink(String checkbox) {
        termsAndConditionsLink(checkbox).click();
        return page(SignUpPage.class);
    }

    public SelenideElement getDialogWindowHeader(String headerTitle) {
        return $(By.xpath(".//div[contains(@class, 'ui-dialog-titlebar ui-widget-header')]/child::span[contains(text(), '" + headerTitle + "' )]")).waitUntil(visible, 8000);
    }

    public SelenideElement getCloseDialogWindow(String headerTitle) {
        return $(By.xpath(".//div[contains(@class, 'ui-dialog-titlebar ui-widget-header')]/child::span[contains(text(), '"+headerTitle+"' )]" +
                "/following-sibling::a/child::span[@class='icon-close']")).waitUntil(visible, 8000);
    }

    public SelenideElement getFooterOnPrivacyPolicyDialogWindow() {
        return $(By.xpath(".//div[contains(text(), 'Revision')]")).waitUntil(visible, 8000);
    }

    public SignUpPage agreeWithTermsAndConditions(String headerTitle) {
        clickOnTermsAndConditionLink(termsAndConditionsCheckbox);
        getDialogWindowHeader(headerTitle);
        getButton("I agree").click();
        return page(SignUpPage.class);
    }

    public SignUpPage scrollAndClosePrivacyPolicy(String headerTitle) {
        clickOnTermsAndConditionLink(privacyPolicyCheckbox);
        getDialogWindowHeader(headerTitle);
        //scroll
        Selenide.executeJavaScript("arguments[0].scrollIntoView();", getFooterOnPrivacyPolicyDialogWindow());
        getCloseDialogWindow(headerTitle).click();
        return page(SignUpPage.class);
    }

    public SignUpPage clickSignUpButton(){
        getSignUpButton()
                .shouldNotHave(Condition.attribute("disabled", "disabled"))
                .click();
        return page(SignUpPage.class);
    }

    public SignUpPage getFinishSettingUpNotificationMessage(){
        $(By.className("loading")).waitUntil(appears, 80000).waitUntil(visible, 8000).waitUntil(disappears, 60000);
        return page(SignUpPage.class);
    }


}
