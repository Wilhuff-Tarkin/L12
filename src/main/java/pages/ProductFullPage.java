package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductFullPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger(ProductFullPage.class);
    @Getter
    private String name;
    @Getter
    @FindBy(css = ".current-price :first-child")
    private WebElement productFinalPrice;
    @Getter
    @FindBy(css = ".btn.btn-primary.add-to-cart")
    private WebElement addToCartBtn;
    @Getter
    @FindBy(css = "#quantity_wanted")
    private WebElement quantityWanted;
    @Getter
    @FindBy(css = ".material-icons.touchspin-up")
    private WebElement quantityUp;
    @Getter
    @FindBy(css = ".material-icons.touchspin-down")
    private WebElement quantityDown;
    @Getter
    @FindBy(css = ".product-message")
    private WebElement customizableText;
    @Getter
    @FindBy(css = ".product-discount")
    private WebElement productRegularPrice;
    @Getter
    @FindBy(css = ".discount.discount-percentage")
    private WebElement discountTag;
    @Getter
    @FindBy(css = ".clearfix .btn.btn-primary.float-xs-right")
    private WebElement saveCustomizationBtn;

    public ProductFullPage(WebDriver driver, String name) {

        super(driver);
        this.name = name;
        PageFactory.initElements(driver, this);
    }

    public float getProductFinalPrice() {
        return parsePrice(productFinalPrice);
    }

    public float getProductRegularPrice() {
        return parsePrice(productRegularPrice);
    }


    public int setRandomQuantity() {
        int quantity = random.nextInt(5) + 1;
        log.info("Desired quantity: " + quantity);
        for (int i = 1; i < quantity; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(quantityUp));
            quantityUp.click();
        }
        return quantity;
    }

    public void addToCart(String customizableText) {
        if (checkIfProductIsCustomizable()) {
            getCustomizableText().sendKeys(customizableText);
            getSaveCustomizationBtn().click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        getAddToCartBtn().click();
    }


    private boolean checkIfProductIsCustomizable() {
        return !driver.findElements(By.cssSelector(".product-message")).isEmpty();
    }
}


