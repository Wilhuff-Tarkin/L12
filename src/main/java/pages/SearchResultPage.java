package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage {

    @Getter
    @FindBy(css = ".thumbnail-container.reviews-loaded")
    private WebElement productLoaded;

    @Getter
    private ProductsSectionPage productsSectionPage;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        productsSectionPage = new ProductsSectionPage(driver);
        PageFactory.initElements(driver, this);
    }
}
