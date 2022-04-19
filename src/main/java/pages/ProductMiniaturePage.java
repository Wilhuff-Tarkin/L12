package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductMiniaturePage extends BasePage {

    @Getter
    @FindBy(css = ".h3.product-title a")
    private WebElement productName;


    public ProductMiniaturePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductMiniaturePage() {
    }


}
