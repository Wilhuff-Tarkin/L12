package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage extends BasePage{






    public CategoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
