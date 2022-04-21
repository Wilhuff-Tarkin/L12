package test;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.FooterPage;
import pages.HeaderPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class PricesDrop extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("prices drop");


    @Test
    void name() {

        FooterPage footerPage = new FooterPage(driver);
        footerPage.getPricedDropBtn().click();
        onSalePageShouldLoad();



    }

    private void onSalePageShouldLoad() {

//        checkIfDesiredPageLoaded();
// assert
    }
}
