package test;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.HeaderPage;

public class Filter extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("filter");


    @ParameterizedTest
    @CsvSource({
            "9, 15",
            "12, 29"
    })
    void priceFiltersShouldWork(int from, int to){

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.getArt().click();
        CategoryPage artPage = new CategoryPage(driver);

        artPage.getFilterPage().setPriceFilter(from, to);

//        System.out.println("from " + artPage.getFilterPage().getPriceScope(0));
//        System.out.println("to " + artPage.getFilterPage().getPriceScope(1));





    }
}
