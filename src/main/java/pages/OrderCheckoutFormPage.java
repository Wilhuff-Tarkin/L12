package pages;

import configuration.model.SocialTitle;
import configuration.model.UserModel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Date;

public class OrderCheckoutFormPage extends BasePage {

    @Getter
    @FindBy(css = ".custom-radio [value=\"1\"]")
    private WebElement mrSocialTitle;

    @Getter
    @FindBy(css = ".custom-radio [value=\"2\"]")
    private WebElement mrsSocialTitle;

    @Getter
    @FindBy(css = ".form-control[name=\"firstname\"]")
    private WebElement firstNameField;

    @Getter
    @FindBy(css = ".form-control[name=\"lastname\"]")
    private WebElement lastNameField;

    @Getter
    @FindBy(css = ".form-control[name=\"email\"]")
    private WebElement emailField;

    @Getter
    @FindBy(css = ".input-group.js-parent-focus [name=\"password\"]")
    private WebElement passwordField;

    @Getter
    @FindBy(css = ".form-control[name=\"birthday\"]")
    private WebElement birthdayField;

    @Getter
    @FindBy(css = ".custom-checkbox input[name=\"optin\"]")
    private WebElement receiveOffersFromPartners;

    @Getter
    @FindBy(css = ".custom-checkbox input[name=\"customer_privacy\"]")
    private WebElement customerDataPrivacy;

    @Getter
    @FindBy(css = ".custom-checkbox input[name=\"newsletter\"]")
    private WebElement signUpForNewsletter;

    @Getter
    @FindBy(css = ".custom-checkbox input[name=\"custom-checkbox\"]")
    private WebElement generalConditionsAndPrivacyPolicy;


    public OrderCheckoutFormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void fillPersonalInformation(UserModel user) throws InterruptedException {
        setSocialTitle(user.getSocialTitle());
        firstNameField.sendKeys(user.getFirstName());
        lastNameField.sendKeys(user.getLastName());
        emailField.sendKeys(user.getEmail());
        passwordField.click();
        passwordField.sendKeys(user.getPassword());
        birthdayField.sendKeys(formatBirthday(user.getBirthDate()));
        setCheckboxes(user);
        Thread.sleep(1334);

    }

    private void setCheckboxes(UserModel user) {

        if (user.isDataPrivacyConsent()){
            receiveOffersFromPartners.click();}
        if (user.isDataPrivacyConsent()){
            customerDataPrivacy.click();}
        if (user.isNewsletterConsent()){
            signUpForNewsletter.click();}
        if (user.isGeneralConditionsConsent()){
            generalConditionsAndPrivacyPolicy.click();}
    }

    private String formatBirthday(Date birthDate) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(birthDate.getMonth()).append("/").append(birthDate.getDay()).append("/").append(birthDate.getYear());
        return String.valueOf(stringBuilder);
    }

    private void setSocialTitle(Enum socialTitle) {
        if (socialTitle.equals(SocialTitle.MR)) {
            mrSocialTitle.click();
        } else {
            mrsSocialTitle.click();
        }
    }
}
