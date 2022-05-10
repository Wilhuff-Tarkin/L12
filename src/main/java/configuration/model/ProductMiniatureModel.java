package configuration.model;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class ProductMiniatureModel extends BasePage {

    @Getter
    private String name;

    @Getter
    private WebElement thumbnail;

    @Getter
    private float productFinalPrice;

    @Getter
    private float productRegularPrice;

    public ProductMiniatureModel(WebDriver driver, String name, WebElement thumbnail, WebElement finalPrice) {
        super(driver);
        this.name = name;
        this.thumbnail = thumbnail;
        this.productFinalPrice = parsePrice(finalPrice);
    }

    public ProductMiniatureModel(WebDriver driver, String name, WebElement thumbnail, WebElement finalPrice, WebElement regularPrice) {
        super(driver);
        this.name = name;
        this.thumbnail = thumbnail;
        this.productFinalPrice = parsePrice(finalPrice);
        this.productRegularPrice = parsePrice(regularPrice);
    }
}
