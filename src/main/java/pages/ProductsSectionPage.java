package pages;

import configuration.model.ProductMiniatureModel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ProductsSectionPage extends BasePage {
    private static Logger logger = LoggerFactory.getLogger(ProductsSectionPage.class);


    @Getter
    @FindBy(css = ".product")
    private List<WebElement> productsOnPage = new ArrayList<>();

    @Getter
    @FindBy(css = ".product-flag.discount")
    private List<WebElement> discountTagsByProducts = new ArrayList<>();

    @Getter
    @FindBy(css = ".product-price-and-shipping")
    private List<WebElement> finalPrices = new ArrayList<>();

    private List<ProductMiniatureModel> productsList = new ArrayList<>();

    private List<Float> pricesInSection = new ArrayList<>();


    public ProductsSectionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<ProductMiniatureModel> getProductsList() {
        setAllProducts();
        return productsList;
    }

    public ProductsSectionPage setAllProducts() {

        for (int i = 0; i < productsOnPage.size(); i++) {
            String name = productsOnPage.get(i).findElement(By.cssSelector(".h3.product-title")).getText();
            WebElement thumbnail = productsOnPage.get(i).findElement(By.cssSelector(".thumbnail.product-thumbnail"));
            WebElement price = productsOnPage.get(i).findElement(By.cssSelector(".product-price-and-shipping .price"));
            if (checkIfProductHaveRegularPrice(productsOnPage.get(i))) {
                WebElement regularPrice = productsOnPage.get(i).findElement(By.cssSelector(".product-price-and-shipping .regular-price"));
                productsList.add(new ProductMiniatureModel(driver, name, thumbnail, price, regularPrice));
            } else {
                productsList.add(new ProductMiniatureModel(driver, name, thumbnail, price));
            }
        }
        logger.info("Added " + productsList.size() + " product(s) to the list");
        return this;
    }

    private boolean checkIfProductHaveRegularPrice(WebElement webElement) {
        return !webElement.findElements(By.cssSelector(".product-price-and-shipping .regular-price")).isEmpty();
    }

    public ProductMiniatureModel getRandomProduct() {
        setAllProducts();
        return productsList.get(random.nextInt(productsList.size()));
    }

    public List<WebElement> getProductsOnPage() {
        setAllProducts();
        return productsOnPage;
    }

    public List<Float> getFinalPrices() {
        for (int i = 0; i < finalPrices.size(); i++) {
            pricesInSection.add(parsePrice(finalPrices.get(i).findElement(By.cssSelector(" .price"))));
        }
        return pricesInSection;
    }
}
