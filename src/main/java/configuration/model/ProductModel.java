package configuration.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductModel {

    private String name;

    public ProductModel(WebElement product) {
        name = product.findElement(By.cssSelector(".product-description .h3.product-title")).getText();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product named as " + name;
    }
}
