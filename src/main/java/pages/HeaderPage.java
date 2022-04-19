package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends BasePage {

    @FindBy(css = ".ui-autocomplete-input")
    private WebElement searchCatalogInput;

    @FindBy(css = "#header .material-icons.search")
    private WebElement loupeButton;

    public HeaderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public HeaderPage searchForProduct(String target ) {
        searchCatalogInput.click();
        searchCatalogInput.clear();
        searchCatalogInput.sendKeys(target);
        loupeButton.click();
        return this;
    }

    public void clickSearchBtn() {
        loupeButton.click();
    }
}
