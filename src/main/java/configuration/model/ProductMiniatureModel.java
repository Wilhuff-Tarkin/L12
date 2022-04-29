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
    private WebElement productFinalPrice;

    @Getter
    private WebElement productRegularPrice;

    public ProductMiniatureModel(WebDriver driver, String name, WebElement thumbnail, WebElement finalPrice) {
        super(driver);
        this.name = name;
        this.thumbnail = thumbnail;
        this.productFinalPrice = finalPrice;
    }

    public ProductMiniatureModel(WebDriver driver, String name, WebElement thumbnail, WebElement finalPrice, WebElement regularPrice) {
        super(driver);
        this.name = name;
        this.thumbnail = thumbnail;
        this.productFinalPrice = finalPrice;
        this.productRegularPrice = regularPrice;
    }


    public float getProductFinalPrice() {

        return parsePrice(productFinalPrice);
    }

    public float getProductRegularPrice() {

        return parsePrice(productRegularPrice);
    }



}
