package pages;

import configuration.model.ProductModel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class HeaderPage extends BasePage {

    @FindBy(css = ".ui-autocomplete-input")
    private WebElement searchCatalogInput;

    @FindBy(css = "#header .material-icons.search")
    private WebElement loupeButton;

    @Getter
    @FindBy(css = ".ui-corner-all .product")
    private List<WebElement> dropDown = new ArrayList<>(); ;

    private List<String> itemsOnDropDown = new ArrayList<>();

    @FindBy(css = "#ui-id-1")
    private WebElement dropDownHints;


    public List<String> getItemsFromDropdown(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(dropDownHints));
        dropDown = driver.findElements(By.cssSelector(".ui-corner-all .product"));
        setAllProducts();
        return itemsOnDropDown;
    }

    private HeaderPage setAllProducts() {
        for (WebElement product : dropDown) {
            itemsOnDropDown.add(product.getText());
            System.out.println("dodaje taki produkt " + product.getText() );
        }
        return this;
    }

    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public HeaderPage sendKeysToSearch(String target ) {
        searchCatalogInput.click();
        searchCatalogInput.clear();
        searchCatalogInput.sendKeys(target);
        return this;
    }

    public void clickSearchBtn() {
        loupeButton.click();
    }

    public void readDropDown() {
    }
}
