package pages;

import configuration.model.ProductModel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {

    @Getter
    @FindBy(css = ".product")
    private List<WebElement> searchResult = new ArrayList<>();

    private List<ProductModel> foundProducts = new ArrayList<>();


    public SearchResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public List<ProductModel> getFoundProducts() {
        setAllProducts();
        return foundProducts;
    }

    public SearchResultPage setAllProducts() {
        //todo tu mozna dodac jakiegos waita na element
//        productsOnHome.get(0);

        for (WebElement product : searchResult) {
            foundProducts.add(new ProductModel(product));
            System.out.println("dodaje taki produkt " + product.getText() );
        }
//        log.info("All products on page set");
        return this;
    }


}
