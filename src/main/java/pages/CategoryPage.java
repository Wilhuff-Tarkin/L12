package pages;

import configuration.model.ProductModel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends BasePage{

    @Getter
    private FilterPage filterPage;

    @Getter
    @FindBy(css = ".product")
    private List<WebElement> productsOnCategoryPage = new ArrayList<>();


    private List<ProductModel> productsList = new ArrayList<>();


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

    public CategoryPage setAllProducts() {
        //todo tu mozna dodac jakiegos waita na element
//        productsOnHome.get(0);

        for (WebElement product : productsOnCategoryPage) {
            productsList.add(new ProductModel(product));
        }
//        log.info("All products on page set");
        return this;
    }

    public boolean checkIfCategoryContainsSubCategories() {
                return subCategories.size()>0;
    }
}
