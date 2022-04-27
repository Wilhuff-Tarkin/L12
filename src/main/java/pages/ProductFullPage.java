package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ProductFullPage extends BasePage{

    private static Logger log = LoggerFactory.getLogger(ProductFullPage.class);


    public ProductFullPage(WebDriver driver, String name) {

        super(driver);
        this.name = name;
        PageFactory.initElements(driver, this);

    }

    @Getter
    private String name;


    @Getter
    @FindBy(css = ".current-price :first-child")
    private WebElement productFinalPrice;



    @Getter
    @FindBy(css = ".btn.btn-primary.add-to-cart")
    private WebElement addToCart;

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
    @FindBy(css = ".product-discount")
    private WebElement productRegularPrice;

    @Getter
    @FindBy(css = ".discount.discount-percentage")
    private WebElement discountTag;



    public float getProductFinalPrice() {
        return Float.parseFloat(productFinalPrice.getAttribute("content"));
    }

    public float getProductRegularPrice() {
        return Float.parseFloat(driver.findElement(By.cssSelector(".regular-price")).getText().substring(1));
    }


    public void setRandomQuantity() {
        int quantity = random.nextInt(5) + 1;
        log.info("Desired quantity: " + quantity);
        for (int i = 1; i < quantity; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(quantityUp));
            quantityUp.click();
        }
    }
}


