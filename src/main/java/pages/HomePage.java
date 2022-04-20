package pages;

import configuration.model.ProductModel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    @Getter
    @FindBy(css = ".product")
    private List<WebElement> productsOnHome = new ArrayList<>();


    private List<ProductModel> productsList = new ArrayList<>();




    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public ProductModel getRandomProduct() {
        setAllProducts();
        return productsList.get(random.nextInt(productsList.size()));
    }

    public HomePage setAllProducts() {
        //todo tu mozna dodac jakiegos waita na element
//        productsOnHome.get(0);

        for (WebElement product : productsOnHome) {
            productsList.add(new ProductModel(product));
        }
//        log.info("All products on page set");
        return this;
    }



}
