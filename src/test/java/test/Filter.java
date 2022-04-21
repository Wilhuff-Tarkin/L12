package test;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.HeaderPage;

public class Filter extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("filter");


    @Test
    void priceFiltersShouldWork() throws InterruptedException {

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.getArt().click();

        CategoryPage categoryPage = new CategoryPage(driver);

        categoryPage.getFilterPage().getFirstFilterOnPage().click();
        Thread.sleep(1211);
        categoryPage.getFilterPage().closeFilter();
        Thread.sleep(1211);

    }
}
