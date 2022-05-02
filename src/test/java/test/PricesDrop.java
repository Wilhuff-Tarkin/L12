package test;

import base.TestBase;
import configuration.model.ProductMiniatureModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.FooterPage;
import pages.ProductFullPage;
import pages.ProductsSectionPage;

import java.text.DecimalFormat;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class PricesDrop extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("prices drop");
    private static final DecimalFormat df = new DecimalFormat("####.##");


    @Test
    void verifyPricesDropCategory() {

        getPricesDropCategory();
        onSalePageShouldLoad();
        eachProductShouldHaveDiscountTag();
        eachProductShouldHaveStandardAndDiscountPrice();
        discountedPricesShouldBe20percentLower();
    }

    @Test
    void verifyPricesDropSingleProduct() {
        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);
        ProductMiniatureModel miniatureProduct = productsOnSale.getRandomProduct();
        String productName = miniatureProduct.getName();
        log.info("Random discounted product selected: " + productName);
        miniatureProduct.getThumbnail().click();
        ProductFullPage productPage = new ProductFullPage(driver, productName);
        discountTagShouldBePresent(productPage);
        shouldHaveStandardAndDiscountPrice(productPage);
        discountedPricesShouldBe20percentLower2(productPage);
    }

    private void getPricesDropCategory() {
        FooterPage footerPage = new FooterPage(driver);
        footerPage.getPricedDropBtn().click();
    }

    private void discountedPricesShouldBe20percentLower2(ProductFullPage productPage) {
        float finalPrice = productPage.getProductFinalPrice();
        float regularPrice = productPage.getProductRegularPrice();
        calculateDiscount(finalPrice, regularPrice);
    }

    private void calculateDiscount(float finalPrice, float regularPrice) {
        float priceAfter20off = regularPrice - (regularPrice * 20) / 100;
        priceAfter20off = Float.parseFloat(df.format(priceAfter20off).replaceAll(",", "."));
        log.info("Calculated price after 20% discount: " + priceAfter20off);
        assertThat("Final price not as expected", finalPrice == priceAfter20off);
    }

    private void shouldHaveStandardAndDiscountPrice(ProductFullPage productPage) {
        String name = productPage.getName();
        assertThat("Product do not have regular price", productPage.getProductRegularPrice() > 0);
        assertThat("Product do not have discount price", productPage.getProductFinalPrice() > 0);
        log.info("Product " + name + " have standard and discount price");
    }

    private void discountTagShouldBePresent(ProductFullPage productPage) {
        assertThat("Tag isnt there", productPage.getDiscountTag().isDisplayed());
    }

    private void discountedPricesShouldBe20percentLower() {
        List<ProductMiniatureModel> list = getProductsOnThePage();

        for (ProductMiniatureModel productMiniaturePage : list) {
            float finalPrice = productMiniaturePage.getProductFinalPrice();
            float regularPrice = productMiniaturePage.getProductRegularPrice();
            calculateDiscount(finalPrice, regularPrice);
        }
    }

    private List<ProductMiniatureModel> getProductsOnThePage() {
        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);
        return productsOnSale.getProductsList();
    }

    private void eachProductShouldHaveStandardAndDiscountPrice() {
        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);
        List<ProductMiniatureModel> list = productsOnSale.getProductsList();

        for (ProductMiniatureModel productMiniaturePage : list) {
            String name = productMiniaturePage.getName();
            assertThat("Product do not have regular price", productMiniaturePage.getProductRegularPrice() > 0);
            assertThat("Product do not have discount price", productMiniaturePage.getProductFinalPrice() > 0);
            log.info("Product " + name + " have standard and discount price");
        }
    }

    private void eachProductShouldHaveDiscountTag() {
        ProductsSectionPage productsOnSale = new ProductsSectionPage(driver);
        int productsFound = productsOnSale.getProductsOnPage().size();
        int discountTagsFound = productsOnSale.getDiscountTagsByProducts().size();
        log.info("Found " + productsFound + " products on page and " + discountTagsFound + " of them on discount");
        assertThat("Different number of products and discount tags", productsFound == discountTagsFound);
    }

    private void onSalePageShouldLoad() {
        String expectedTitle = testEnvironment.returnValueAsString("droppedPricesCatName");
        checkIfDesiredPageLoaded(expectedTitle);
    }
}
