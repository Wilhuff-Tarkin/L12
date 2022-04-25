package configuration.model;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class ProductModel {

    @Getter
    private String name;

    @Getter
    private float price;





    @Override
    public String toString() {
        return "Product named as " + name + "price is " + price;
    }
}
