package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class ProductsSectionPage extends BasePage {


    @Getter
    @FindBy(css = ".product")
    private List<WebElement> productsOnPage = new ArrayList<>();

    private List<ProductMiniaturePage> productsList = new ArrayList<>();


    public ProductsSectionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }



    public List<ProductMiniaturePage> getProductsList() {
        setAllProducts();
        return productsList;
    }

    public ProductsSectionPage setAllProducts() {


//        productsOnPage = driver.findElements(By.cssSelector(".product"));

        for (int i = 0; i < productsOnPage.size(); i++) {

//            driver.findElements(By.cssSelector(".product")).get(i);
//            String selector = ".products.row :nth-child(" + i + ") article";
//            System.out.println(driver.findElements(By.cssSelector(".product-title")).get(i).getText());
//            String name = driver.findElements(By.cssSelector(".product-title")).get(i).getText();
//            String name = productsOnPage.get(i).findElement(By.cssSelector(selector)).getText();
            productsList.add(new ProductMiniaturePage(driver, driver.findElements(By.cssSelector(".product-title")).get(i).getText()));

        }

//        for (WebElement product : productsOnPage) {
//            String name = product.findElement(By.cssSelector(".product .product-title")).getText();
//            productsList.add(new ProductMiniaturePage(driver, name));
//        }
        return this;
    }

    public ProductMiniaturePage getRandomProduct() {
        setAllProducts();
        return productsList.get(random.nextInt(productsList.size()));
    }

    public List<WebElement> getProductsOnPage() {
        setAllProducts();
        return productsOnPage;
    }

}
