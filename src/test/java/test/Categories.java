package test;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.HeaderPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class Categories extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("categories");


    @Test
    void categoriesTest() {

        HeaderPage headerPage = new HeaderPage(driver);
        List<WebElement> categories = headerPage.getCategoriesLabels();
        List<WebElement> subCategories = headerPage.getSubCategoriesLabels();


        for (int i = 0; i < headerPage.getCategoriesLabels().size(); i++) {

            String categoryLabel = headerPage.getCategoriesLabels().get(i).getText();
            headerPage.getCategoriesLabels().get(i).click();
            CategoryPage page = new CategoryPage(driver);

            log.info("Checking if header match for category " + categoryLabel);
            nameOfCategoryShouldMatchHeader(categoryLabel, page);

            log.info("Checking if filter box is displayed for category " + categoryLabel);
            filtersSideMenuShouldBeDisplayed(page);

            log.info("Checking number of products for category " + categoryLabel);
            numberOfProductsShouldBeDisplayed(page);
        }
    }


    private void numberOfProductsShouldBeDisplayed(CategoryPage page) {
        int numberOfProductsDisplayed = page.getProductsOnCategoryPage().size();
        String indicatedNumberOfProducts = page.getThereAreXProducts().getText().replaceAll("[^0-9]", "");
        assertThat("not as expected", numberOfProductsDisplayed == Integer.parseInt(indicatedNumberOfProducts));
    }


    void filtersSideMenuShouldBeDisplayed(CategoryPage page) {
        assertThat("not as expected", page.getFilterPage().getFirstFilterOnPage().isDisplayed());
    }

    void nameOfCategoryShouldMatchHeader(String categoryLabel, CategoryPage page) {
        assertThat("not as expected", page.getHeader().getText().equals(categoryLabel));
    }

}