package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage extends BasePage{


    @FindBy(css = "#js-product-list-header .h1")
    private WebElement categoryTitle;

    @FindBy(css = "#search_filters")
    private WebElement searchFilters;

    @Getter
    private String name;

    @FindBy(css = ".h1")
    private WebElement header;


    public CategoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.name = name;

    }

    @Override
    public String toString() {
        return "CategoryPage{" +
                "categoryTitle=" + categoryTitle +
                ", searchFilters=" + searchFilters +
                ", name='" + name + '\'' +
                ", header=" + header +
                '}';
    }
}
