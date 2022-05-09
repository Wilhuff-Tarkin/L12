package pages;

import configuration.model.EnvironmentModel;
import configuration.model.SocialTitle;
import configuration.model.UserModel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class OrderCheckoutFormPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger("Checkout page");


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
    @FindBy(css = ".custom-checkbox input[name=\"psgdpr\"]")
    private WebElement generalConditionsAndPrivacyPolicy;

    @Getter
    @FindBy(css = ".continue.btn.btn-primary.float-xs-right[data-link-action=\"register-new-customer\"]")
    private WebElement continueToAddressBtn;

    @Getter
    @FindBy(css = ".continue.btn.btn-primary.float-xs-right[name=\"confirm-addresses\"]\n")
    private WebElement confirmAddressBtn;

    @Getter
    @FindBy(css = ".continue.btn.btn-primary.float-xs-right[name=\"confirmDeliveryOption\"]\n")
    private WebElement confirmDeliveryOptionBtn;

    @Getter
    @FindBy(css = ".form-control[name=\"address1\"]")
    private WebElement addressField;

    @Getter
    @FindBy(css = ".form-control[name=\"city\"]")
    private WebElement cityField;

    @Getter
    @FindBy(css = ".form-control[name=\"postcode\"]")
    private WebElement postCodeField;

    @Getter
    @FindBy(css = ".form-control.form-control-select[name=\"id_state\"]")
    private WebElement selectState;

    @Getter
    @FindBy(css = ".form-control.form-control-select[name=\"id_country\"]")
    private WebElement selectCountry;

    @Getter
    @FindBy(css = ".row.delivery-option")
    private List<WebElement> radioDeliveries;

    @Getter
    @FindBy(css = ".custom-radio.float-xs-left #payment-option-2")
    private WebElement payByBankWireRadioBtn;

    @Getter
    @FindBy(css = ".custom-checkbox [name=\"conditions_to_approve[terms-and-conditions]\"]")
    private WebElement termsAndServiceConditionsCheck;

    @Getter
    @FindBy(css = ".btn.btn-primary.center-block")
    private WebElement placeOrderButton;

    @Getter
    @FindBy(css = ".payment-options")
    private WebElement paymentOptions;

    @Getter
    @FindBy(css = "#cta-terms-and-conditions-0")
    private WebElement termsAndServiceConditions;

    @Getter
    @FindBy(css = ".modal-dialog .js-modal-content")
    private WebElement termsAndServiceModal;

    @Getter
    @FindBy(css = "#modal button.close")
    private WebElement modalClose;

    @Getter
    String chosenDelivery;



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
        birthdayField.sendKeys(user.getBirthDate());
        setCheckboxes(user);
        //todo betterwait
        Thread.sleep(1334);
        continueToAddressBtn.click();

    }

    public void fillAddress(UserModel user, EnvironmentModel testEnvironment) throws InterruptedException {
        Thread.sleep(1231);

        addressField.sendKeys(user.getAddress());
        cityField.sendKeys(user.getCity());
        setRandomState();
        postCodeField.sendKeys(user.getPostalCode());
        setCountry(testEnvironment.returnValueAsString("country"));
        //todo betterwait
        Thread.sleep(1334);
        confirmAddressBtn.click();
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


    private void setSocialTitle(Enum socialTitle) {
        if (socialTitle.equals(SocialTitle.MR)) {
            mrSocialTitle.click();
        } else {
            mrsSocialTitle.click();
        }
    }

    public void setRandomState() {
        Select drpState = new Select(selectState);
        drpState.selectByIndex(random.nextInt(drpState.getOptions().size()));
    }

    public void setCountry(String usersCountry) {
        Select drpCountry = new Select(selectCountry);
        drpCountry.selectByValue(usersCountry);
    }

    public void chooseDelivery() {
        wait.until(ExpectedConditions.visibilityOf(confirmDeliveryOptionBtn));
        WebElement delivery = radioDeliveries.get(random.nextInt(radioDeliveries.size()));
        chosenDelivery = delivery.findElement(By.cssSelector(".carrier-name")).getText();
        clickOnElement(delivery.findElement(By.cssSelector(".custom-radio")));
        confirmDeliveryOptionBtn.click();
    }

    public void choosePayment() {
        wait.until(ExpectedConditions.visibilityOf(paymentOptions));
        payByBankWireRadioBtn.click();
    }

    public boolean checkTermsAndServiceConditions() {
        termsAndServiceConditions.click();
        wait.until(ExpectedConditions.visibilityOf(termsAndServiceModal));

        boolean conditionsNotEmpty = !termsAndServiceModal.getText().isEmpty();

        if (termsAndServiceModal.getText().isEmpty()){
            log.error("Empty terms and conditions");
        } else {
            log.info("Terms and conditions read");
        }
        modalClose.click();
        return conditionsNotEmpty;
    }

    public void finalizeOrder() {
        termsAndServiceConditionsCheck.click();
        placeOrderButton.click();
    }



}
