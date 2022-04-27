package test;

import base.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;
import pages.*;

import static org.hamcrest.MatcherAssert.assertThat;


public class ShoppingCart extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("categories");


    @Test
    void name() {

      HeaderPage headerPage = new HeaderPage(driver);
      headerPage.enterRandomCategory();
        ProductsSectionPage productsSectionPage = new ProductsSectionPage(driver);
        ProductMiniaturePage random = productsSectionPage.getRandomProduct();
        String testproductNAme = random.getName();
        random.getThumbnail().click();

        ProductFullPage productFullPage = new ProductFullPage(driver, testproductNAme);
        productFullPage.setRandomQuantity();
        productFullPage.getAddToCart().click();
        AddedToCartModalPage modal = new AddedToCartModalPage(driver);
        driverWait.until(ExpectedConditions.visibilityOf(modal.getProductAddedToCartLabel()));
        assertThat("Label not as expected",modal.getProductAddedToCartLabel().getText().equals("\uE876Product successfully added to your shopping cart"));

        log.info(testproductNAme + " added to the cart");
    }
}
