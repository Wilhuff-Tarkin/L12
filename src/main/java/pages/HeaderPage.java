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

    private static Logger logger = LoggerFactory.getLogger(HeaderPage.class);

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
    @FindBy(css = ".ui-corner-all .product")
    private List<WebElement> dropDown = new ArrayList<>();
    private List<String> itemsOnDropDown = new ArrayList<>();

    @Getter
    @FindBy(css = ".category [data-depth='0']")
    private List<WebElement> categoriesLabels = new ArrayList<>();


//
//    private List <String> mainCategories = new ArrayList<>();
//
//    private List<String> subCategories = new ArrayList<>();


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


//    public List<String> setMainCategories() {
//
//        for (WebElement categoryName : categoriesLabels) {
//            mainCategories.add(categoryName.getText());
//            logger.info("Adding item " + categoryName.getText() + " to the list");
//        }
//        return mainCategories;
//    }

    private HeaderPage setAllProductsFromDropdown() {
        for (WebElement product : dropDown) {
            itemsOnDropDown.add(product.getText());
            logger.info("Adding item " + product.getText() + " to the list");
        }
        return this;
    }

    public HeaderPage sendKeysToSearch(java.lang.String target) {
        searchCatalogInput.click();
        searchCatalogInput.clear();
        searchCatalogInput.sendKeys(target);
        return this;
    }

    public void clickSearchBtn() {
        loupeButton.click();
    }

    //    public void readDropDown() {
//    }
//
//    public List<WebElement> getSubCategoriesLabels() {
//
//
//
//        for (int i = 0; i < categoriesLabels.size(); i++) {
//
//            categoriesLabels.get(i).click();
//            CategoryPage page = new CategoryPage(driver);
//            List <WebElement> subcats = page.getSubCategories();
//
//            f
//
//
//        }
//        return categoriesLabels;
    }
