package test;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class PricesDrop extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("prices drop");


    @Test
    void name() {

        FooterPage footerPage = new FooterPage(driver);
        footerPage.getPricedDropBtn().click();
        onSalePageShouldLoad();
        eachProductShouldHaveDiscountTag();
        eachProductShouldHaveStandardAndDiscountPrice();
        discountedPricesShouldBe20percentLower();


    }

    private void discountedPricesShouldBe20percentLower() {

        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);

        List <ProductMiniaturePage> list = productsOnSale.getProductsList();

        for (ProductMiniaturePage productMiniaturePage : list) {
            float finalPrice = productMiniaturePage.getProductFinalPrice();
            float regularPrice = productMiniaturePage.getProductRegularPrice();
            float priceAfter20off = regularPrice - (regularPrice * 20)/100;

            System.out.println("final price: " + finalPrice);
            System.out.println("regular price: " + regularPrice);

            System.out.println("regular price lowered by 20% =  " + priceAfter20off);

            System.out.println("name is: " + productMiniaturePage.getName());
//            private static final DecimalFormat df = new DecimalFormat("0.00");
//            System.out.println("salary : " + df.format(input));      //1205.64

//            assertThat("Product do not have price or disounted price",);
        }
    }

    private void eachProductShouldHaveStandardAndDiscountPrice() {

        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);

        List <ProductMiniaturePage> list = productsOnSale.getProductsList();

        for (ProductMiniaturePage productMiniaturePage : list) {
            System.out.println("final price: " + productMiniaturePage.getProductFinalPrice());
            System.out.println("regular price: " + productMiniaturePage.getProductRegularPrice());
            System.out.println("name is: " + productMiniaturePage.getName());

//            assertThat("Product do not have price or disounted price",);
        }

    }

    private void eachProductShouldHaveDiscountTag() {

        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);

       int productsFound = productsOnSale.getProductsOnPage().size();
       int discountTagsFound = productsOnSale.getDiscountTagsByProducts().size();

       log.info("Found " + productsFound + " products on page and " + discountTagsFound + " of them on discount");
        assertThat("Different number of products and disounttags", productsFound == discountTagsFound);

    }

    private void onSalePageShouldLoad() {

        System.out.println(checkIfDesiredPageLoaded("On sale"));
    }
}
