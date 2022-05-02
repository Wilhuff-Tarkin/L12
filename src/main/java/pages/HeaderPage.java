package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HeaderPage extends BasePage {

    private static Logger log = LoggerFactory.getLogger(HeaderPage.class);

    @FindBy(css = ".ui-autocomplete-input")
    private WebElement searchCatalogInput;

    @FindBy(css = "#header .material-icons.search")
    private WebElement loupeButton;

    @Getter
    @FindBy(css = "#category-3")
    private WebElement clothes;

    @Getter
    @FindBy(css = "#category-6")
    private WebElement accessories;

    @Getter
    @FindBy(css = "#category-9")
    private WebElement art;

    @Getter
    @FindBy(css = ".cart-products-count")
    private WebElement cartProductsCount;

    @Getter
    @FindBy(css = ".header a")
    private WebElement openCartBtn;



    @Getter
    @FindBy(css = ".ui-corner-all .product")
    private List<WebElement> dropDown = new ArrayList<>();
    private List<String> itemsOnDropDown = new ArrayList<>();

    @Getter
    @FindBy(css = ".category [data-depth='0']")
    private List<WebElement> categoriesLabels = new ArrayList<>();


    @FindBy(css = "#ui-id-1")
    private WebElement dropDownHints;


    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getItemsFromDropdown(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(dropDownHints));
        dropDown = driver.findElements(By.cssSelector(".ui-corner-all .product"));
        setAllProductsFromDropdown();
        return itemsOnDropDown;
    }


    private HeaderPage setAllProductsFromDropdown() {
        for (WebElement product : dropDown) {
            itemsOnDropDown.add(product.getText());
            log.info("Adding item " + product.getText() + " to the list from dropdown");
        }
        return this;
    }

    public HeaderPage sendKeysToSearch(String target) {
        searchCatalogInput.click();
        searchCatalogInput.clear();
        searchCatalogInput.sendKeys(target);
        return this;
    }

    public void clickSearchBtn() {
        loupeButton.click();
    }

    public void enterRandomCategory() {

        WebElement randomCategory = getCategoriesLabels().get(random.nextInt(getCategoriesLabels().size()));
        randomCategory.click();

    }

    }
