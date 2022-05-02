package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger("Shopping cart");

    @Getter
    @FindBy(css = ".category-sub-menu a")
    private List<WebElement> subCategories;


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


}
