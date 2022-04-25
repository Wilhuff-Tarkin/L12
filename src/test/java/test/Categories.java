package test;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.HeaderPage;

import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Categories extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("categories");

    @Test
    void categoriesTest() {

        HeaderPage headerPage = new HeaderPage(driver);
        List<WebElement> categories = headerPage.getCategoriesLabels();

        for (int i = 0; i < categories.size(); i++) {
            verifyCategory(categories.get(i));
        }
    }

    private void verifyCategory(WebElement categoryLink) {
        String categoryLabel = categoryLink.getText();
        log.info(">>>> Testing: " + categoryLabel.toUpperCase(Locale.ROOT));

        categoryLink.click();
        CategoryPage page = new CategoryPage(driver);

        nameOfCategoryShouldMatchHeader(categoryLabel, page);
        filtersSideMenuShouldBeDisplayed(categoryLabel, page);
        numberOfProductsShouldBeDisplayed(categoryLabel, page);
        driver.navigate().back();

    }

    @Test
    void subCategoriesTest() {
        HeaderPage headerPage = new HeaderPage(driver);
        List<WebElement> categories = headerPage.getCategoriesLabels();
        log.info("Found " + categories.size() + " main categories");

        for (int i = 0; i < categories.size(); i++) {

            categories.get(i).click();
            CategoryPage page = new CategoryPage(driver);
            log.info(">>>> Looking for subcategories in " + page.getHeader().getText());

            if (page.checkIfCategoryContainsSubCategories()) {
                List<WebElement> subCategories = page.getSubCategories();
                int subCatSize = subCategories.size();
                log.info(">>>> Found: " + subCatSize + " subcategories");

                for (int j = 0; j < subCatSize; j++) {
                    verifyCategory(subCategories.get(j));
                }
            } else {
                log.info(">>>> No subcategories found in " + page.getHeader().getText());
            }
        }
    }

    private void numberOfProductsShouldBeDisplayed(String categoryLabel, CategoryPage page) {
        log.info("Checking number of products for category " + categoryLabel.toUpperCase(Locale.ROOT));
        int numberOfProductsDisplayed = page.getProductsSectionPage().getProductsList().size();
        String indicatedNumberOfProducts = page.getThereAreXProducts().getText().replaceAll("[^0-9]", "");
        assertThat("not as expected", numberOfProductsDisplayed == Integer.parseInt(indicatedNumberOfProducts));
    }

    void filtersSideMenuShouldBeDisplayed(String categoryLabel, CategoryPage page) {
        log.info("Checking if filter box is displayed for category " + categoryLabel.toUpperCase(Locale.ROOT));
        assertThat("not as expected", page.getFilterPage().getFirstFilterOnPage().isDisplayed());
    }

    void nameOfCategoryShouldMatchHeader(String categoryLabel, CategoryPage page) {
        log.info("Checking if header match for category " + categoryLabel.toUpperCase(Locale.ROOT));
        assertThat("not as expected", page.getHeader().getText().equalsIgnoreCase(categoryLabel));
    }


//    @ParameterizedTest
//    @MethodSource("addParameters")
//    void parametrizedCategoriesTest(List <WebElement> categories) {
//
//
//        for (int i = 0; i < categories.size(); i++) {
//
//            String categoryLabel = categories.get(i).getText();
//            categories.get(i).click();
//            CategoryPage page = new CategoryPage(driver);
//
//            log.info("Checking if header match for category " + categoryLabel);
//            nameOfCategoryShouldMatchHeader(categoryLabel, page);
//
//            log.info("Checking if filter box is displayed for category " + categoryLabel);
//            filtersSideMenuShouldBeDisplayed(page);
//
//            log.info("Checking number of products for category " + categoryLabel);
//            numberOfProductsShouldBeDisplayed(page);
//        }
//    }
//
//    private Stream<Arguments> addParameters() {
//        HeaderPage headerPage = new HeaderPage(driver);
//
//        return Stream.of(
//                Arguments.of(headerPage.getSubCategoriesLabels()),
//                Arguments.of(headerPage.getCategoriesLabels())
//        );
//    }


}