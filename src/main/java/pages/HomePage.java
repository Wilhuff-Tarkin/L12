package pages;

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

    @Getter
    private ProductsSectionPage productsSectionPage;

    public HomePage(WebDriver driver) {
        super(driver);
        productsSectionPage = new ProductsSectionPage(driver);
        PageFactory.initElements(driver, this);
    }

}



