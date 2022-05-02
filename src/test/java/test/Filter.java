package test;

import base.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.HeaderPage;
import pages.ProductsSectionPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class Filter extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("filter");

    @ParameterizedTest
    @CsvSource({
            "9, 10",
            "24, 29",
            "9, 29"
    })
    void priceFiltersShouldWork(int from, int to) throws InterruptedException {

        CategoryPage artPage = goToArtPage();
        artPage.getFilterPage().setPriceFilter(from, to);
        checkIfProductPricesFitsInScope(from, to);
    }

    private void checkIfProductPricesFitsInScope(int from, int to) {
        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);
        List<Float> prices = productsOnSale.getFinalPrices();

        log.info("Price filter is set to prices between " + from + " to " + to);

        for (int i = 0; i < prices.size(); i++) {
            log.info("Checking price " + prices.get(i));
            assertThat("Product price is below FROM value", !(prices.get(i) < from));
            assertThat("Product price is above TO value", !(prices.get(i) > to));
            log.info("Product price fit within desired scope");
        }
    }

    private CategoryPage goToArtPage() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.getArt().click();
        CategoryPage artPage = new CategoryPage(driver);
        return artPage;
    }
}
