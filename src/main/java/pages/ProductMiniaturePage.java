package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductMiniaturePage extends BasePage {

    @Getter
    private String name;

    @Getter
    @FindBy(css = ".thumbnail.product-thumbnail")
    private WebElement thumbnail;

    @Getter
    @FindBy(css = ".product-description .product-price-and-shipping >.price")
    private WebElement productFinalPrice;



    @Getter
    @FindBy(css = ".product-description .product-price-and-shipping >.regular-price")
    private WebElement productRegularPrice;

    public ProductMiniaturePage(WebDriver driver, String name) {
        super(driver);
        this.name = name;
        PageFactory.initElements(driver, this);
    }

    public float getProductFinalPrice() {


        return Float.parseFloat(driver.findElement(By.cssSelector(".product-description .product-price-and-shipping >.price")).getText().substring(1));
    }

    public float getProductRegularPrice() {
        return Float.parseFloat(driver.findElement(By.cssSelector(".product-description .product-price-and-shipping >.regular-price")).getText().substring(1));
    }

}
