package pages;

import configuration.model.ProductModel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {

    @Getter
    @FindBy(css = "#js-product-list .product")
    private List<WebElement> searchResult = new ArrayList<>();

    @Getter
    @FindBy(css = ".thumbnail-container.reviews-loaded")
    private WebElement productLoaded;

    private List<ProductModel> foundProducts = new ArrayList<>();


    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public List<ProductModel> getFoundProducts() {
        setAllProducts();
        return foundProducts;
    }

    public SearchResultPage setAllProducts() {

        wait.until(ExpectedConditions.visibilityOf(productLoaded));

        for (WebElement product : searchResult) {
            ProductModel newProduct = new ProductModel(product);
            foundProducts.add(newProduct);
            System.out.println("dodaje taki produkt " + newProduct.getName());
        }
        return this;
    }


}
