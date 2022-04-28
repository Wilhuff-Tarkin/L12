package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductMiniaturePage extends BasePage {

    @Getter
    private String name;

    @Getter
    private WebElement thumbnail;

    @Getter
    private WebElement productFinalPrice;

    public ProductMiniaturePage(WebDriver driver, String name, WebElement thumbnail, WebElement finalPrice) {
        super(driver);
        this.name = name;
        this.thumbnail = thumbnail;
        this.productFinalPrice = finalPrice;
    }

    public float getProductFinalPrice() {

        return parsePrice(productFinalPrice);
//        return Float.parseFloat(driver.findElement(By.cssSelector(".product-description .product-price-and-shipping >.price")).getText().substring(1));
    }


}
