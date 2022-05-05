package test;

import base.TestBase;
import configuration.model.CartModel;
import configuration.model.OrderProductModel;
import configuration.model.ProductMiniatureModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AddedToCartModalPage;
import pages.HeaderPage;
import pages.ProductFullPage;
import pages.ProductsSectionPage;

import static org.hamcrest.MatcherAssert.assertThat;


public class ShoppingCart extends TestBase {

    private static final Logger log = LoggerFactory.getLogger("Shopping cart");
    int testIterations = Integer.parseInt(testEnvironment.returnValueAsString("iterations"));
    String customizableText = testEnvironment.returnValueAsString("customTxt");


    @Test
    void shouldAddProductToCart() {

        CartModel cartModel = new CartModel();
        HeaderPage headerPage = new HeaderPage(driver);

        while (testIterations > 0) {
            headerPage.enterRandomCategory();
            String productName = enterRandomProduct();

            ProductFullPage productFullPage = new ProductFullPage(driver, productName);
            OrderProductModel orderedProduct = modelOrderedProduct(productFullPage);
            cartModel.addAnother(orderedProduct);

            productFullPage.addToCart(customizableText);

            AddedToCartModalPage modal = new AddedToCartModalPage(driver);

            checkIfPopupDisplayCorrectValues(modal, orderedProduct, cartModel);
            log.info(productName + " successfully added to the cart");

            modal.getContinueShopping().click();
            checkIfCartDisplaysCorrectNumberOfItems(headerPage, cartModel);
            testIterations--;
        }
    }

    protected OrderProductModel modelOrderedProduct(ProductFullPage productFullPage) {
        String name = productFullPage.getName();
        float price = productFullPage.getProductFinalPrice();
        int quantity = productFullPage.setRandomQuantity();
        return new OrderProductModel(name, price, quantity);
    }

    protected String enterRandomProduct() {
        ProductsSectionPage productsSectionPage = new ProductsSectionPage(driver);
        ProductMiniatureModel randomProduct = productsSectionPage.getRandomProduct();
        String productName = randomProduct.getName();
        randomProduct.getThumbnail().click();
        return productName;
    }

    protected void checkIfCartDisplaysCorrectNumberOfItems(HeaderPage headerPage, CartModel cartModel) {

        driverWait.until(ExpectedConditions.visibilityOf(headerPage.getCartProductsCount()));
        String cartCounter = headerPage.getCartProductsCount().getText();
        cartCounter = cartCounter.replaceAll( "[^\\d]", "" );
        int productCountFromHeader = Integer.parseInt(cartCounter);
        assertThat("Product count in cart incorrect", productCountFromHeader == cartModel.getNumberOfItems());

    }

    protected void checkIfPopupDisplayCorrectValues(AddedToCartModalPage modal, OrderProductModel productOrderModel, CartModel cart) {

        driverWait.until(ExpectedConditions.visibilityOf(modal.getProductAddedToCartLabel()));
        assertThat("Label not as expected", modal.getProductAddedToCartLabel().getText().equals("\uE876Product successfully added to your shopping cart"));

        log.info("Actual last added product name " + modal.getLastAddedProductName() + " " + " expected product name: " + productOrderModel.getName());
        assertThat("Product name incorrect", modal.getLastAddedProductName().equals(productOrderModel.getName()));

        log.info("Actual last added product price " + modal.getLastAddedProductPrice() + " " + " expected product price: " + productOrderModel.getPrice());
        assertThat("Product price incorrect", modal.getLastAddedProductPrice() == productOrderModel.getPrice());

        log.info("Actual last added product quantity " + modal.getLastAddedProductQuantity() + " " + " expected product quantity " + productOrderModel.getQuantity());
        assertThat("Quantity incorrect", modal.getLastAddedProductQuantity() == cart.getQuantity(productOrderModel.getName()));

        log.info("Actual number of different items in cart " + modal.getThereAreXelementsInCart() + " " + " expected number of items in cart " + cart.getNumberOfItems());
        assertThat("X items on your cart incorrect", modal.getThereAreXelementsInCart() == cart.getNumberOfItems());

        log.info("Actual value of products in cart " + modal.getSubTotalValue() + " expected value of cart " + cart.getTotalOrderCost());
        assertThat("Total products value incorrect", modal.getSubTotalValue() == cart.getTotalOrderCost());
    }
}
