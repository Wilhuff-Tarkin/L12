package configuration.handler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.HeaderPage;

import java.util.ArrayList;
import java.util.List;

public class CategoriesHandler {

    private List<CategoryPage> mainCategories = new ArrayList<>();
    private List<CategoryPage> subCategories = new ArrayList<>();
    private WebDriver driver;
    private List <WebElement> categoriesLabels;
    private static Logger logger = LoggerFactory.getLogger("Categories handler");


    public CategoriesHandler(WebDriver driver, List<WebElement> categories) {
        this.driver = driver;
        categoriesLabels = categories;
    }


    public List <CategoryPage> getMainCategories () {


        for (WebElement category : categoriesLabels) {
            CategoryPage page = new CategoryPage(driver);
            category.click();
            mainCategories.add(page);
            logger.info("Adding category " + page.getName() + " to the list");
            driver.navigate().back();
        }
        return mainCategories;
    }

    public List<CategoryPage> getSubCategories() {
        return null;
    }
}
