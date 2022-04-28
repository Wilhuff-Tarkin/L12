package test;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

import java.text.DecimalFormat;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class PricesDrop extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("prices drop");
    private static final DecimalFormat df = new DecimalFormat("####.##");


    @Test
    void verifyPricesDropCategory() {

        FooterPage footerPage = new FooterPage(driver);
        footerPage.getPricedDropBtn().click();
        onSalePageShouldLoad();
        eachProductShouldHaveDiscountTag();
        eachProductShouldHaveStandardAndDiscountPrice();
        discountedPricesShouldBe20percentLower();
        verifyPricesDropSingleProduct();


    }

    private void verifyPricesDropSingleProduct() {

        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);
        ProductMiniaturePage miniaturePage = productsOnSale.getRandomProduct();
        String productName = miniaturePage.getName();
        log.info("Random discounted product selected: " + productName);
        miniaturePage.getThumbnail().click();
        ProductFullPage productPage = new ProductFullPage(driver, productName);
        discountTagShouldBePresent(productPage);
        shouldHaveStandardAndDiscountPrice(productPage);
        discountedPricesShouldBe20percentLower2(productPage);


    }

    private void discountedPricesShouldBe20percentLower2(ProductFullPage productPage) {

        float finalPrice = productPage.getProductFinalPrice();
        float regularPrice = productPage.getProductRegularPrice();
        calculateDisount (finalPrice, regularPrice);


    }

    private void calculateDisount(float finalPrice, float regularPrice) {
        float priceAfter20off = regularPrice - (regularPrice * 20) / 100;
        priceAfter20off = Float.parseFloat(df.format(priceAfter20off).replaceAll(",","."));
        log.info("Calculated price after 20% discount: " + priceAfter20off);
        assertThat("Final price not as expected", finalPrice == priceAfter20off);
    }

    private void shouldHaveStandardAndDiscountPrice(ProductFullPage productPage) {

        System.out.println("regular " + productPage.getProductRegularPrice());
        System.out.println( "discounted " + productPage.getProductFinalPrice());


    }

    private void discountTagShouldBePresent(ProductFullPage productPage) {
        assertThat("Tag isnt there", productPage.getDiscountTag().isDisplayed());
    }

    private void discountedPricesShouldBe20percentLower() {

        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);

        List<ProductMiniaturePage> list = productsOnSale.getProductsList();

        for (ProductMiniaturePage productMiniaturePage : list) {
            float finalPrice = productMiniaturePage.getProductFinalPrice();
            float regularPrice = productMiniaturePage.getProductRegularPrice();
            //tu jest problem bo nie wszystkie produkty mają discount pytanie czy konstruktor dodac drugi na taki wyapdek czy co
            calculateDisount(finalPrice, regularPrice);

        }
    }

    private void eachProductShouldHaveStandardAndDiscountPrice() {

        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);

        List<ProductMiniaturePage> list = productsOnSale.getProductsList();

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
