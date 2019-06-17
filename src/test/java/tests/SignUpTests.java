package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import lombok.val;
import models.AdminAccountDetails;
import models.Company;
import models.TermsAndConditions;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.SignUpPage;
import utilities.RandomUtils;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertAll;
import static test_data.InslyTestData.*;
import static utilities.RandomUtils.getRandomValueFromMap;

public class SignUpTests extends TestRunner {
    public static final String SIGN_UP_PAGE_URL = INSLY_URL + "/signup";

    public String iconStatusOk = "ok";
    public String iconStatusError = "error";
    public String termsAndConditionsHeader = "Terms and conditions";
    public String privacyPolicyHeader = "Privacy Policy";
    public static String companyName = RandomUtils.getRandomAlphaNumeric(25);


    public static Company COMPANY_INFO = Company.builder()
            .companyName(companyName)
            .companyProfile(getRandomValueFromMap(companyProfile).getValue())
            .inslyAddress(companyName.toLowerCase())
            .numberOfEmployees(getRandomValueFromMap(numberOfEmployees).getValue())
            .describeYourself(getRandomValueFromMap(describeYourSelf).getValue())
            .build();

    public static AdminAccountDetails ADMIN_ACCOUNT_DETAILS = AdminAccountDetails.builder()
            .webEmail("maryanadmytriv+" + RandomUtils.getRandomNumeric(10) + "@gmail.com")
            .accountManagerName("Marianna Dmytriv" + RandomUtils.getRandomAlphaNumeric(5))
            .phone(RandomUtils.getRandomNumeric(10))
            .build();

    public static TermsAndConditions TERMS_AND_CONDITIONS = TermsAndConditions.builder()
            .termsAndCondition(true)
            .privacyPolicy(true)
            .storePersonalData(true)
            .build();

    @Test
    public void testValidCompanyInfoOnSignUpPage() {
        SignUpPage signUpPage = page(SignUpPage.class);

        open(SIGN_UP_PAGE_URL, SignUpPage.class)
                .fillCompanyFieldsWithoutAddress(COMPANY_INFO);
        assertThat(signUpPage.getSignUpField(signUpPage.inslyAddressField).getValue()).isEqualTo(COMPANY_INFO.getCompanyName().toLowerCase());

        signUpPage.checkCompanyIconStatuses(iconStatusOk);

        val enteredCompanyData = signUpPage.getEnteredCompanyData();
        assertThat(COMPANY_INFO.equals(enteredCompanyData)).isTrue();
    }

    @Test
    public void testValidAdminAccountDetailsOnSignUpPage() {
        SignUpPage signUpPage = page(SignUpPage.class);

        open(SIGN_UP_PAGE_URL, SignUpPage.class)
                .fillCompanyFieldsWithoutAddress(COMPANY_INFO)
                .fillAdminAccountDetailsFieldsWithoutPassword(ADMIN_ACCOUNT_DETAILS);

        val pass = signUpPage.fillPasswordWithSuggested();
        assertAll(
                () -> assertThat(signUpPage.getSignUpField(signUpPage.passwordField).getValue()).isEqualTo(pass),
                () -> assertThat(signUpPage.getSignUpField(signUpPage.passwordRepeatField).getValue()).isEqualTo(pass)
        );

        val enteredAdminAccountDetailsData = signUpPage.getEnteredAdminAccountDetailsData();
        assertThat(ADMIN_ACCOUNT_DETAILS.setPassword(pass).setPasswordRepeat(pass).equals(enteredAdminAccountDetailsData)).isTrue();
    }

    @Test
    public void testValidTermsAndConditionsOnSignUpPage() {
        SignUpPage signUpPage = page(SignUpPage.class);

        open(SIGN_UP_PAGE_URL, SignUpPage.class)
                .fillCompanyFieldsWithoutAddress(COMPANY_INFO)
                .fillAdminAccountDetailsFieldsWithoutPassword(ADMIN_ACCOUNT_DETAILS)
                .fillPasswordWithSuggested();

        signUpPage.checkOutTermsAndConditions(TERMS_AND_CONDITIONS)
                .agreeWithTermsAndConditions(termsAndConditionsHeader)
                .scrollAndClosePrivacyPolicy(privacyPolicyHeader)
                .getSignUpButton()
                .shouldNotHave(Condition.attribute("disabled", "disabled"));
    }

    @Test
    public void testValidInstanceCreationFinishOnSignUpPage() {
        SignUpPage signUpPage = page(SignUpPage.class);
        LoginPage loginPage = page(LoginPage.class);

        open(SIGN_UP_PAGE_URL, SignUpPage.class)
                .fillCompanyFieldsWithoutAddress(COMPANY_INFO)
                .fillAdminAccountDetailsFieldsWithoutPassword(ADMIN_ACCOUNT_DETAILS);
        val pass = signUpPage.fillPasswordWithSuggested();
        ADMIN_ACCOUNT_DETAILS.setPassword(pass).setPasswordRepeat(pass);

        signUpPage.checkOutTermsAndConditions(TERMS_AND_CONDITIONS)
                .agreeWithTermsAndConditions(termsAndConditionsHeader)
                .scrollAndClosePrivacyPolicy(privacyPolicyHeader)
                .clickSignUpButton()
                .getFinishSettingUpNotificationMessage();
        loginPage.verifyLogoHeaderLoginPage();

        //verify url with InslyAddress
        String url = WebDriverRunner.url();
        assertThat("https://" + COMPANY_INFO.getInslyAddress() + ".int.staging.insly.training/login")
                .isEqualTo(url);

        //login and verify dashboard
        loginPage
                .fillLoginFields(ADMIN_ACCOUNT_DETAILS.getWebEmail(), ADMIN_ACCOUNT_DETAILS.getPassword())
                .clickLoginButton()
                .verifyDashboardPage();
    }
}
