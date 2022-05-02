package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CategoryPage extends BasePage{

    @Getter
    private FilterPage filterPage;

    @Getter
    private ProductsSectionPage productsSectionPage;

    @FindBy(css = "#js-product-list-header .h1")
    private WebElement categoryTitle;

    @FindBy(css = "#search_filters")
    private WebElement searchFilters;

    @Getter
    private java.lang.String name;

    @Getter
    @FindBy(css = ".h1")
    private WebElement header;

    @Getter
    @FindBy(css = "#js-product-list-top p")
    private WebElement ThereAreXProducts;

    @Getter
    @FindBy(css = ".category-sub-menu a")
    private List <WebElement> subCategories;


    public CategoryPage(WebDriver driver) {
        super(driver);
        filterPage = new FilterPage(driver);
        productsSectionPage = new ProductsSectionPage(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public java.lang.String toString() {
        return "CategoryPage{" +
                "categoryTitle=" + categoryTitle.getText() +
                ", searchFilters=" + searchFilters +
                ", name='" + name + '\'' +
                ", header=" + header.getText() +
                '}';
    }

//    public CategoryPage setAllProducts() {
//
//        for (WebElement product : productsOnCategoryPage) {
//            String name = product.findElement(By.cssSelector(".product .product-title")).getText();
//            productsList.add(new ProductMiniaturePage(driver, product));
//        }
//        return this;
//    }

    public boolean checkIfCategoryContainsSubCategories() {
                return subCategories.size()>0;
    }


}
