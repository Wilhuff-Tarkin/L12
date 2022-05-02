package test;

import base.TestBase;
import configuration.model.ProductMiniatureModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HeaderPage;
import pages.HomePage;
import pages.SearchResultPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class Search extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(Search.class);

    @Test
    void shouldSearchForRandomProduct() {
        ProductMiniatureModel randomProduct = getRandomProductFromHome(driver);
        searchForProduct(randomProduct.getName());
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        List<ProductMiniatureModel> productsFound = searchResultPage.getProductsSectionPage().getProductsList();
        log.info("Found " + productsFound.size() + " product(s) on search results list.");
        assertThat("Product not found on the list", productsFound.stream().anyMatch((product) -> product.getName().contains(randomProduct.getName())));
    }

    @Test
    void shouldFindRandomProductInDropdown() {
        ProductMiniatureModel randomProduct = getRandomProductFromHome(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.sendKeysToSearch(randomProduct.getName());
        List<String> hints = headerPage.getItemsFromDropdown(driver);
        log.info("Found " + hints.size() + " product(s) on dropdown.");
        assertThat("Product not found on the list", hints.stream().anyMatch((product) -> product.contains(randomProduct.getName())));
    }


    private void searchForProduct(String name) {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.sendKeysToSearch(name);
        headerPage.clickSearchBtn();
    }

    private ProductMiniatureModel getRandomProductFromHome(WebDriver driver) {
        HomePage homePage = new HomePage(driver);
        ProductMiniatureModel randomProduct = homePage.getProductsSectionPage().getRandomProduct();
        log.info("Randomly picked product: " + randomProduct.getName());
        return randomProduct;
    }
}
