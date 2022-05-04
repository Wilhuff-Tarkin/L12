package configuration.model;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

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

//    public List <OrderProductModel> converseToOrderProductModel(List<ProductMiniatureModel> source) {
//
//        List <OrderProductModel> list = new ArrayList<>();
//
//        for (int i = 0; i < source.size(); i++) {
//
//        String name = source.get(i).getName();
//                Float price = source.get(i).getProductFinalPrice();
//                        int quantity = source.get(i).ge
//
//
//        }
//
//        return list;
//
//    }

//    public float getProductFinalPrice() {
//        return parsePrice(productFinalPrice);
//    }
//
//    public float getProductRegularPrice() {
//
//        return parsePrice(productRegularPrice);
//    }



}
