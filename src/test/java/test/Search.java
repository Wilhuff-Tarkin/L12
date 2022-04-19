package test;

import base.TestBase;
import configuration.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HeaderPage;
import pages.HomePage;
import pages.SearchResultPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class Search extends TestBase {

//    product name = getRandomItemFrom(homepage);
//    searchfield Enter (product name)
//    searchbutton clic
//    assertthat searchresult contains product name

    private static final Logger log = LoggerFactory.getLogger(Search.class);


    @Test
    void shouldSearchForRandomProduct() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        ProductModel randomProduct = homePage.getRandomProduct();
        log.info("Randomly picked product: " + randomProduct.getName());

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.searchForProduct(randomProduct.getName());

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        log.info("Found " + searchResultPage.getFoundProducts().size() + " product(s).");

        assertThat("Product not found on list", searchResultPage.getFoundProducts().stream().anyMatch((product) -> product.getName().contains(randomProduct.getName())));


    }
}
