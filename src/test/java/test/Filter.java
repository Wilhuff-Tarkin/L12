package test;

import base.TestBase;
import configuration.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.HeaderPage;
import pages.ProductMiniaturePage;

import java.util.List;

public class Filter extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("filter");

    @ParameterizedTest
    @CsvSource({
            "9, 10",
            "24, 29",
            "9, 29"
    })

    void priceFiltersShouldWork(int from, int to) throws InterruptedException {

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.getArt().click();
        CategoryPage artPage = new CategoryPage(driver);

        artPage.getFilterPage().setPriceFilter(from, to);

        List <ProductMiniaturePage> products = artPage.getProductsSectionPage().getProductsList();

        for (ProductMiniaturePage product : products) {

            System.out.println(product.getProductFinalPrice());
        }

        //listy produktow
        // dla kazdego zrobic asercje czy cena jest mniejsza od to i wieksza rowna od od


//        System.out.println("from " + artPage.getFilterPage().getPriceScope(0));
//        System.out.println("to " + artPage.getFilterPage().getPriceScope(1));

    }
}
