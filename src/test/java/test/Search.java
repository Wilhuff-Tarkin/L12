package test;

import base.TestBase;
import configuration.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HeaderPage;
import pages.HomePage;
import pages.ProductMiniaturePage;
import pages.SearchResultPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class Search extends TestBase {


    private static final Logger log = LoggerFactory.getLogger(Search.class);


    @Test
    void shouldSearchForRandomProduct() {

        HomePage homePage = new HomePage(driver);
        ProductMiniaturePage randomProduct = homePage.getProductsSectionPage().getRandomProduct();
        log.info("Randomly picked product: " + randomProduct.getName());

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.sendKeysToSearch(randomProduct.getName());
        headerPage.clickSearchBtn();

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        List <ProductMiniaturePage> productsFound = searchResultPage.getProductsSectionPage().getProductsList();
        log.info("Found " + productsFound.size() + " product(s).");

        assertThat("Product not found on the list", productsFound.stream().anyMatch((product) -> product.getName().contains(randomProduct.getName())));
    }

    @Test
    void shouldFindRandomProductInDropdown() {

        HomePage homePage = new HomePage(driver);
        ProductMiniaturePage randomProduct = homePage.getProductsSectionPage().getRandomProduct();
        log.info("Randomly picked product: " + randomProduct.getName());

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.sendKeysToSearch(randomProduct.getName());
        List <String> hints = headerPage.getItemsFromDropdown(driver);

        log.info("Found " + hints.size() + " product(s).");

        assertThat("Product not found on the list", hints.stream().anyMatch((product) -> product.contains(randomProduct.getName())));
    }

}
